package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.student_compass.model.dto.*;
import ru.nsu.ccfit.student_compass.service.ChatService;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public Long createChat(@RequestBody CreateChatDto createChatDto) {
        return chatService.createChat(createChatDto);
    }

    @PostMapping("/new_message")
    public void addMessage(@RequestBody MessageDto messageDto,
                           @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        chatService.addMessage(messageDto, jwt);
    }

    @GetMapping
    public List<MessageResponseDto> getMessage(@RequestParam Long chatId,
                                               @RequestParam int numberPage,
                                               @RequestParam int sizePage,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        System.out.println(chatId);
        ChatParamDto chatParams = new ChatParamDto(chatId, numberPage, sizePage);
        String jwt = authorizationHeader.substring(7);
        return chatService.getMessage(chatParams, jwt);
    }

    @GetMapping("/all")
    public List<ChatDto> getChats(@RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        return chatService.getChats(jwt);
    }
}
