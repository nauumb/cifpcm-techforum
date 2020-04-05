package es.cifpcm.techforum.security.services.comments;

import es.cifpcm.techforum.models.comments.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
}
