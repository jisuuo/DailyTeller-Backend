package hello.dailyteller.controller;

import hello.dailyteller.api.common.ApiResponse;
import hello.dailyteller.dto.ExtractResponseDto;
import hello.dailyteller.service.ExtractAPIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/extractAPI")
public class ExtractAPIController {

    private final ExtractAPIService extractAPIService;

    public ExtractAPIController(ExtractAPIService extractAPIService) {
        this.extractAPIService = extractAPIService;
    }

    @GetMapping("/article")
    public ResponseEntity<ApiResponse<ExtractResponseDto>> getArticleSummary(@RequestParam String url) {
        ExtractResponseDto response = extractAPIService.getArticleSummary(url);

        if (response == null) {
            return ResponseEntity.badRequest().body((ApiResponse.failure("뉴스 추출 실패", response)));
        }
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
