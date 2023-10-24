package exercise;

import io.javalin.Javalin;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;
import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
//            var userNumber = ctx.pathParamAsClass("id", Integer.class);
            var term = ctx.queryParam("term");
            List<User> filteredUsers;

            if (term == null) {
                filteredUsers = USERS;
            } else {
//                StringUtils.startsWith()
//                StringUtils.startsWith(u.getFirstName().toLowerCase(), )
                filteredUsers = USERS
                        .stream()
                        .filter(u -> {
                            return StringUtils.startsWith(u.getFirstName().toLowerCase(), term.toLowerCase());
                        }
                            //return StringUtils.startsWith((u.getFirstName().toLowerCase().toCharArray(), term.toLowerCase().toCharArray());
                        )
                        .toList();
            }

            var page = new UsersPage(filteredUsers, term);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
