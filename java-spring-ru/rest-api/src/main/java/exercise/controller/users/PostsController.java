package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public ResponseEntity<List<Post>> showPostsById(@PathVariable String id) {
        var postsWithId = posts.stream().filter(p -> p.getUserId() == Integer.parseInt(id)).toList();
        return ResponseEntity.ok().body(postsWithId);
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> createNewPost(@PathVariable String id, @RequestBody Post post) {
        Post newPost = new Post();
        newPost.setUserId(Integer.parseInt(id));
        newPost.setSlug(post.getSlug());
        newPost.setTitle(post.getTitle());
        newPost.setBody(post.getBody());

        posts.add(newPost);

        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);

    }
}
// END
