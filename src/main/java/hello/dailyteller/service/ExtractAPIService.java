package hello.dailyteller.service;

import hello.dailyteller.api.common.ApiResponse;
import hello.dailyteller.dto.ExtractRequestDto;
import hello.dailyteller.dto.ExtractResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class ExtractAPIService {

    private final WebClient extractAPIService;

    public ExtractResponseDto getArticleSummary(String url) {

        return extractAPIService.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/article/proxy/parse") // 또는 /summarize 등 실제 API 경로
                        .queryParam("url", url)
                        .queryParam("word_per_minute", 300)
                        .queryParam("desc_truncate_len", 210)
                        .queryParam("desc_len_min", 180)
                        .queryParam("content_len_min", 200)
                        .build())
                .retrieve()
                .bodyToMono(ExtractResponseDto.class)
                .block();


    }

}
