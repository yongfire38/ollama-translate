package com.example.ollama_translate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ollama_translate.service.TranslationService;

@Controller
public class TranslationController {

    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("initMessage", translationService.initializeChat());
        return "index";
    }

    @PostMapping("/translate")
    public String translate(@RequestParam(value = "text") String text, Model model) {
        try {
            String translatedText = translationService.translate(text);
            model.addAttribute("originalText", text);
            model.addAttribute("translatedText", translatedText);
        } catch (Exception e) {
            model.addAttribute("originalText", text);
            model.addAttribute("translatedText", "번역 중 오류가 발생했습니다: " + e.getMessage());
        }
        return "index";
    }
}
