package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var idFromParam = ctx.pathParam("id");
            Map<String, String> resultMap = new HashMap<>();
            for (var item : COMPANIES) {
                if (item.get("id").equals(idFromParam)) {
                    resultMap.put("id", item.get("id"));
                    resultMap.put("name", item.get("name"));
                    resultMap.put("phone", item.get("phone"));
                    break;
                }
            }
            if (!resultMap.isEmpty()) {
                ctx.json(resultMap);
            }

            if (resultMap.isEmpty()) {
                throw new NotFoundResponse("Company not found");
            }
            //COMPANIES.get();
            //ctx.json();
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
