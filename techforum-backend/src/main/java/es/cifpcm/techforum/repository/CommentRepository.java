package es.cifpcm.techforum.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import es.cifpcm.techforum.models.Comment;

public interface CommentRepository extends MongoRepository<Comment, String>  {



}
