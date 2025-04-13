package hello.dailyteller.service;

import hello.dailyteller.dto.ExtractRequestDto;
import hello.dailyteller.dto.ExtractResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExtractAPIService {

    private final WebClient webClient;

    public ExtractAPIService(@Value("${external.api.base-url}") String baseUrl, @Value("${external.api.key}") String apiKey, @Value("${external.api.host}") String apiHost) {
        this.webClient = WebClient.builder().baseUrl(baseUrl)
                        .defaultHeader("X-RapidAPI-Key", apiKey)
                        .defaultHeader("X-RapidAPI-Host", apiHost)
                        .build();
    }

    public ExtractResponseDto getArticleSummary(String url) {
        ExtractRequestDto extractRequestDto = new ExtractRequestDto(url);

        return webClient.get()
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
                .block(); // 동기 호출

    }

}
