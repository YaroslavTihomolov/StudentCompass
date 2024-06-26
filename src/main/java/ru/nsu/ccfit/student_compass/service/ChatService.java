package ru.nsu.ccfit.student_compass.service;

import jakarta.annotation.Nonnull;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.student_compass.model.dto.*;
import ru.nsu.ccfit.student_compass.model.entity.Chat;
import ru.nsu.ccfit.student_compass.model.entity.Message;
import ru.nsu.ccfit.student_compass.model.entity.User;
import ru.nsu.ccfit.student_compass.model.mapper.ChatMapper;
import ru.nsu.ccfit.student_compass.model.mapper.MessageMapper;
import ru.nsu.ccfit.student_compass.repository.ChatRepository;
import ru.nsu.ccfit.student_compass.repository.MessageRepository;
import ru.nsu.ccfit.student_compass.repository.UserRepository;
import ru.nsu.ccfit.student_compass.utils.JpaUtils;
import ru.nsu.ccfit.student_compass.utils.JwtUtils;

import java.util.HashSet;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public List<ChatDto> getChats(String jwt) {
        String email = JwtUtils.decodeJWT(jwt);
        return userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new)
            .getChats().stream()
            .map(ChatMapper.INSTANCE::toDto)
            .toList();
    }

    @Nonnull
    public Long createChat(CreateChatDto createChatDto, String jwt) {
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
            .orElseThrow(EntityNotFoundException::new);
        createChatDto.userIds().add(user.getId());

        if (chatRepository.existsByName(createChatDto.name())) {
            throw new EntityExistsException();
        }

        log.info("createChat:: chat name " + createChatDto.name());
        return chatRepository.save(new Chat()
            .setName(createChatDto.name())
            .setUser(
                new HashSet<>(userRepository.findAllById(createChatDto.userIds()))
            )).getId();
    }

    public void addMessage(MessageDto messageDto, String jwt) {
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
            .orElseThrow(EntityNotFoundException::new);

        String userName = "%s %s".formatted(user.getFirstName(), user.getLastName());
        log.info("addMessage:: user add userMessage: " + userName);


        Message userMessage = new Message()
            .setUserName(userName)
            .setText(messageDto.text());
        chatRepository.save(JpaUtils.findByIdOrThrow(chatRepository, messageDto.chatId()).addMessage(userMessage));
    }

    @Nonnull
    public List<MessageResponseDto> getMessage(ChatParamDto chatParamDto, String jwt) {
        User user = userRepository.findByEmail(JwtUtils.decodeJWT(jwt))
            .orElseThrow(EntityNotFoundException::new);

        List<Message> userMessages = messageRepository.findAllByChatId(
            chatParamDto.chatId(),
            PageRequest.of(chatParamDto.numberPage(), chatParamDto.sizePage(), Sort.by(Sort.Direction.DESC, "created"))
        );

        System.out.println(userMessages);
        return userMessages.stream()
            .map(MessageMapper.INSTANCE::toDto)
            .toList();
    }
}
