package exercise;

import com.google.gson.Gson;
import io.javalin.Javalin;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        var app = Javalin.create(javalinConfig -> {
            javalinConfig.plugins.enableDevLogging();
        });

        String gsonPhone = new Gson().toJson(Data.getPhones());
        String gsondomains = new Gson().toJson(Data.getDomains());

        app.get("/phones", ctx -> ctx.result(gsonPhone));
        app.get("/domains", ctx -> ctx.result(gsondomains));

        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
