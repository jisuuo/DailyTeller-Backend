package hello.dailyteller.service;

import com.google.cloud.texttospeech.v1.*;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TTSService {
    
    private final TextToSpeechClient textToSpeechClient;

    public byte[] synthesizeSpeech(String text, String genderInput) throws Exception {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

        SsmlVoiceGender gender = parseGender(genderInput);

        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode("ko-KR")
                .setSsmlGender(gender)
                .build();
        AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.MP3)
                .build();

        SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);
        return response.getAudioContent().toByteArray();
    }

    private SsmlVoiceGender parseGender(String input) {
        if (input == null) return SsmlVoiceGender.FEMALE;
    
        switch (input.trim().toLowerCase()) {
            case "male":
            case "남":
            case "남자":
                return SsmlVoiceGender.MALE;
    
            case "neutral":
            case "중립":
                return SsmlVoiceGender.NEUTRAL;
    
            case "female":
            case "여":
            case "여자":
            default:
                return SsmlVoiceGender.FEMALE;
        }
    }
}
