package hello.dailyteller.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExtractAPIWebClient {

    @Bean
    public WebClient ExtractAPIService(@Value("${external.api.base-url}") String baseUrl, @Value("${external.api.key}") String apiKey, @Value("${external.api.host}") String apiHost) {

        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("X-RapidAPI-Key", apiKey)
                .defaultHeader("X-RapidAPI-Host", apiHost)
                .build();
    }
}
