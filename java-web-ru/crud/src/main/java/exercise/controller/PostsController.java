package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {

        var numpage = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var per = 5;
        var offset = (numpage - 1) * per;

        var posts = PostRepository.getEntities().subList(offset, offset + per);
        var page = new PostsPage(numpage, posts);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
//        try {
            var post = PostRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " Page not found"));
            var page = new PostPage(post);
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
//        } catch (NotFoundResponse e) {
//            ctx.status(404);
//            ctx.result("Page not found");
//        }

    }


    // END
}
