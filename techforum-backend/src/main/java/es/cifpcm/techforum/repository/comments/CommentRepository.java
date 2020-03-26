package es.cifpcm.techforum.repository.comments;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.cifpcm.techforum.models.comments.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>  {

    Optional<Comment> findByTitle(String title);


}
