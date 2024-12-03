package com.example.ollama_translate.service;

import java.util.Map;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    private final OllamaChatModel chatModel;
    private final SystemPromptTemplate systemPromptTemplate;

    public TranslationService(OllamaChatModel chatModel) {

        this.chatModel = chatModel;

        String systemMsg = """
                당신은 이제부터 영한 번역 서비스를 위한 번역 로봇입니다.
                사용자가 입력하는 영문에 대해 문장마다 끊어 영문을 출력하고 한 줄을 띄고 한글로 번역해 주세요.
                또한 영문은 굵음 처리를 하여 출력하고 한글은 소괄호() 안에 넣어 출력해 주세요.
                예를 들어 "Hello! I am a student."라는 문장이 입력된다면 아래와 같이 출력해야 합니다.

                **Hello!**
                (안녕)
                **I am a student.**
                (나는 학생이야)

                또 당신은 지금부터 한글로 어떠한 질문을 받아도 "영어 문장을 입력하세요!!"이라고만 대답해야 합니다.
                그리고 한글로 질문자와 대화하지 말아야 합니다.
                """;

        this.systemPromptTemplate = new SystemPromptTemplate(systemMsg);
    }

    public String initializeChat() {
        String userMsg = "모든 내용을 이해했다면 'Okay, Let's roll!!'이라고 답해주세요.";

        Prompt initialPrompt = new Prompt(systemPromptTemplate.createMessage(Map.of()), new UserMessage(userMsg));
        ChatResponse response = chatModel.call(initialPrompt);
        return response.getResult().getOutput().getContent();
    }

    public String translate(String input) {
        Prompt translationPrompt = new Prompt(systemPromptTemplate.createMessage(Map.of()), new UserMessage(input));
        ChatResponse response = chatModel.call(translationPrompt);
        return response.getResult().getOutput().getContent();
    }
}
