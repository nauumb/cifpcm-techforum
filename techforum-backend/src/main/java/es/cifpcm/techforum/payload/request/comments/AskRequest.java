package es.cifpcm.techforum.payload.request.comments;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AskRequest {
    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
