package es.cifpcm.techforum.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private String discussion_id;
    private String parent_id;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String text;
    private String slug;
    private String full_slug;
    private Date posted;
    private User author;

    public Comment() {

    }

    public Comment(String discussion_id,String parent_id
            ,String title, String text, String slug,String full_slug,Date posted, User author) {
        this.discussion_id =  discussion_id;
        this.parent_id = parent_id;
        this.title = title;
        this.text = text;
        this.slug = slug;
        this.full_slug = full_slug;
        this.posted = posted;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(String discussion_id) {
        this.discussion_id = discussion_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getFull_slug() {
        return full_slug;
    }

    public void setFull_slug(String full_slug) {
        this.full_slug = full_slug;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public User getAuthor() {  return author; }

    public void setAuthor(String User) { this.author = author; }
}
