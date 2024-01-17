package exercise.dto;

import java.util.List;
import java.util.Map;

import exercise.model.Comment;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

// BEGIN
@Getter
@Setter
public class PostDTO {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String title;
    private String body;
    private List<CommentDTO> comments;

//    public void setComments(List<Comment> comments) {
//        for (var comm : comments) {
//            comments.;
//        }
//    }
}
// END
