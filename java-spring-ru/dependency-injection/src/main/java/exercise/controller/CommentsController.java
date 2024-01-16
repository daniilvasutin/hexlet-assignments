package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping(path = "")
    public List<Comment> showAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Comment showIdComment(@PathVariable long id) {
        var comment =  commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        return comment;
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@RequestBody Comment date) {
        return commentRepository.save(date);
    }

    @PutMapping(path = "/{id}")
    public Comment updateComment(@PathVariable long id, @RequestBody Comment date) {
//        var product =  commentRepository.findBy
//                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        var comm = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
        comm.setPostId(date.getPostId());
        comm.setBody(date.getBody());

        return commentRepository.save(comm);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteComm(@PathVariable long id) {
        commentRepository.deleteById(id);
    }



}
// END
