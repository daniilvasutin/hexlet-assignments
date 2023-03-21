package exercise.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.User;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {
    private List<User> users = new ArrayList<>();



    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<User> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();
        users = mapper.readValue(new File("src/main/resources/users.json"), new TypeReference<>() { });
        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        users = getUsers();
        StringBuilder body = new StringBuilder();


        // Выводим список всех компаний
        // Создаем html разметку при помощи String builder
        // Подключаем стили
//        <link rel="stylesheet" href="mysite.css">
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        
                    </head>
                    <body>
                """);
        body.append("""
                <table>
                """);

        for (User user : users) {
            body.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>")
                            .append("<a href=\"/users/").append(user.getId()).append("\">")
                            .append(user.getFirstName()).append(" ").append(user.getLastName()).append("</a>")
                    .append("</td>")
                .append("</tr>");
        }

        body.append("""
                </table>
                """);
        body.append("""
                    </body>
                </html>
                """);


        PrintWriter pr = response.getWriter();
        pr.println(body);
        response.setContentType("text/html;charset=UTF-8");
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        users = getUsers();
        User user = new User();
        boolean finded = false;
        for(User account : users) {
            if(account.getId().equals(id)) {
                user = account;
                finded = true;
                break;
            }
        }

        if (finded) {
            StringBuilder body = new StringBuilder();
            body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                        <title>Example application | Users</title>
                        
                    </head>
                    <body>
                """);
            body.append("""
                <table>
                """);

            body.append("<tr>")
                    .append("<td>").append(user.getId()).append("</td>")
                    .append("<td>").append(user.getFirstName()).append("</td>")
                    .append("<td>").append(user.getLastName()).append("</td>")
                    .append("<td>").append(user.getEmail()).append("</td>")
                .append("</tr>");

            body.append("""
                </table>
                """);
            body.append("""
                    </body>
                </html>
                """);

            PrintWriter pr = response.getWriter();
            pr.println(body);
            response.setContentType("text/html;charset=UTF-8");
        }else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        // END
    }


}
