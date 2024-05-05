package ru.nsu.ccfit.student_compass.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.ccfit.student_compass.model.dto.ChatParamDto;
import ru.nsu.ccfit.student_compass.model.dto.CreateChatDto;
import ru.nsu.ccfit.student_compass.model.dto.MessageDto;
import ru.nsu.ccfit.student_compass.model.dto.MessageResponseDto;
import ru.nsu.ccfit.student_compass.service.ChatService;

import javax.validation.Valid;
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

    @PutMapping
    public void addMessage(@RequestBody MessageDto messageDto) {
        chatService.addMessage(messageDto);
    }

    @GetMapping
    public List<MessageResponseDto> getMessage(@Valid ChatParamDto chatParamDto) {
        return chatService.getMessage(chatParamDto);
    }
}
