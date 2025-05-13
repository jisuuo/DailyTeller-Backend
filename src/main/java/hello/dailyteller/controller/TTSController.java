package hello.dailyteller.controller;

import hello.dailyteller.service.TTSService;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/tts")
@RequiredArgsConstructor
public class TTSController {

    private final TTSService ttsService;

    @GetMapping("/speak")
    public ResponseEntity<ByteArrayResource> synthesize(
        @RequestParam String text,
        @RequestParam(required = false) String gender
    ) {
        try {
            byte[] mp3Data = ttsService.synthesizeSpeech(text, gender);

            ByteArrayResource resource = new ByteArrayResource(mp3Data);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"speech.mp3\"")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(mp3Data.length)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
