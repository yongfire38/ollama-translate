package com.example.ollama_translate;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.ollama_translate.service.TranslationService;

@SpringBootApplication
public class OllamaTranslateApplication {

	public static void main(String[] args) {
		SpringApplication.run(OllamaTranslateApplication.class, args);
	}

	@Bean
    public CommandLineRunner run(ApplicationContext ctx, TranslationService translationService) {
        return args -> {
            // 초기화 메시지 출력
            System.out.println("초기화 메시지: ");
            System.out.println(translationService.initializeChat());

            // 사용자 입력 처리
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    System.out.print("영어 문장을 입력하세요 (종료하려면 'quit' 입력): ");
                    String userInput = scanner.nextLine();

                    // 종료 조건
                    if ("quit".equalsIgnoreCase(userInput)) {
                        System.out.println("번역 서비스를 종료합니다.");
                        break;
                    }

                    try {
                        // 번역 결과 출력
                        String translation = translationService.translate(userInput);
                        System.out.println("번역 결과:");
                        System.out.println(translation);
                    } catch (Exception e) {
                        System.err.println("번역 중 오류가 발생했습니다: " + e.getMessage());
                    }
                }
            }
        };
    }

}
