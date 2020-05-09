package es.cifpcm.techforum.payload.request.comments;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AskRequest {
    @Id
    private ObjectId parent_id;

    @Id
    private ObjectId discussion_id;

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

    public ObjectId getParent_id() {
        return parent_id;
    }

    public void setParent_id(ObjectId parent_id) {
        this.parent_id = parent_id;
    }

    public ObjectId getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(ObjectId discussion_id) {
        this.discussion_id = discussion_id;
    }
}
