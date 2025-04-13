package hello.dailyteller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class ExtractResponseDataDto {

    private String url;
    private String title;
    private String description;
    private List<String> links;
    private String image;
    private String content;
    private String author;
    private String favicon;
    private String source;
    private String published;
    private int ttr;
    private String type;
}
