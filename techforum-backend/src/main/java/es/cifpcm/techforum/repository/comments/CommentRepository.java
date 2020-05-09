package es.cifpcm.techforum.repository.comments;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import es.cifpcm.techforum.models.comments.Comment;
import org.springframework.data.mongodb.repository.Query;

public interface CommentRepository extends MongoRepository<Comment, String>  {

    @Query(value="{ parent_id : { $exists : false } }")
    List<Comment> findAllQuestions();

    @Query(value = "{'parent_id' : ?0}")
    List<Comment> findThread(ObjectId parent_id);

    @Query(value="{'parent_id' : ?0}", delete = true)
    void deleteThread(ObjectId parent_id);


}
