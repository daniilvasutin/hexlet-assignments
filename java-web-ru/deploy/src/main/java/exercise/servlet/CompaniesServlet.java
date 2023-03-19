package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        PrintWriter out = response.getWriter();
        if (request.getQueryString() == null) {
            for (var item : getCompanies()) {
                out.println(item);
            }
        } else {
            var str = request.getParameter("search");
            var result = getCompanies().stream().filter(x -> x.contains(str)).collect(Collectors.toList());

            if (result.size() != 0) {
                for (var item : result) {
                    out.println(item);
                }
            }else {
                out.println("Companies not found");
            }
        }
        // END
    }
}
