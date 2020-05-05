package es.cifpcm.techforum.models.comments;

import com.mongodb.BasicDBObject;
import es.cifpcm.techforum.models.authorization.User;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;
@Document(collection = "comments")
public class Comment {
    @Id
    private String id;

    private ObjectId discussion_id;
    private ObjectId parent_id;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    private String text;
    private String slug;
    private String full_slug;
    private Instant posted;
    private User author;

    public Comment() {

    }

    public Comment(ObjectId discussion_id,ObjectId parent_id
            ,String title, String text, String slug,String full_slug,Instant posted, User author) {
        this.discussion_id =  discussion_id;
        this.parent_id = parent_id;
        this.title = title;
        this.text = text;
        this.slug = slug;
        this.full_slug = full_slug;
        this.posted = posted;
        this.author = author;
    }

    public Comment(ObjectId discussion_id,String title, String text,
                   String slug,String full_slug,Instant posted, User author) {
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

    public ObjectId getParent_id() {
        return parent_id;
    }

    public void setParent_id(ObjectId parent_id) {
        this.parent_id = parent_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ObjectId getDiscussion_id() {
        return discussion_id;
    }

    public void setDiscussion_id(ObjectId discussion_id) {
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

    public Instant getPosted(){
        return posted;
    }

    public void setPosted(Instant posted) {
        this.posted = posted;
    }

    public User getAuthor() {  return author; }

    public void setAuthor(String User) { this.author = author; }
}
