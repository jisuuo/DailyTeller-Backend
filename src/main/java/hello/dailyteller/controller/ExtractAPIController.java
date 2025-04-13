package hello.dailyteller.controller;

import hello.dailyteller.dto.ExtractResponseDto;
import hello.dailyteller.service.ExtractAPIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExtractAPIController {

    private final ExtractAPIService extractAPIService;

    public ExtractAPIController(ExtractAPIService extractAPIService) {
        this.extractAPIService = extractAPIService;
    }

    @GetMapping("/extractArticle")
    public ExtractResponseDto getArticleSummary(@RequestParam String url) {
        return extractAPIService.getArticleSummary(url);
    }
}
