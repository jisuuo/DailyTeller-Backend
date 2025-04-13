package hello.dailyteller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExtractResponseDto {
    private int error;
    private String message;
    private ExtractResponseDataDto data;

}
