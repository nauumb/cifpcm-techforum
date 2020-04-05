package es.cifpcm.techforum.security.services.comments;

import es.cifpcm.techforum.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
}
