package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    // BEGIN
    @AfterAll
    public static void afterAll() {
        // Останавливаем приложение
        app.stop();
    }

    @Test
    void testSomething() {

        // Выполняем POST запрос при помощи агента Unirest
        HttpResponse<String> responsePost = Unirest
                // POST запрос на URL
                .post(baseUrl + "/users")
                // Устанавливаем значения полей
                .field("firstName", "Daniil")
                .field("lastName", "Vasiutin")
                .field("email", "daniilvasu@gmail.com")
                .field("password", "12345")
                // Выполняем запрос и получаем тело ответ с телом в виде строки
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(302);
        User user = new QUser().firstName.eq("Daniil").findOne();

        assertThat(user).isNotNull();
        assertThat(user.getFirstName()).isEqualTo("Daniil");
        assertThat(user.getLastName()).isEqualTo("Vasiutin");
    }

    @Test
    void testNotGood() {

        // Выполняем POST запрос при помощи агента Unirest
        HttpResponse<String> responsePost = Unirest
                // POST запрос на URL
                .post(baseUrl + "/users")
                // Устанавливаем значения полей
                .field("firstName", "Daniil")
                .field("lastName", "Vasiutin")
                .field("email", "daniilvasu@gmail.com")
                .field("password", "123")
                // Выполняем запрос и получаем тело ответ с телом в виде строки
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);
        User user = new QUser().firstName.eq("Daniil").findOne();
        assertThat(user).isNull();
        assertThat(responsePost.getBody()).contains("Пароль должен содержать не менее 4 символов");
    }
    // END
}
