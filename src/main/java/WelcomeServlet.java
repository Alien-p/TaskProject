import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.myproj.DAO;


@WebServlet(name = "WelcomeServlet",urlPatterns = "/welcome")
public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("username") == null){
            response.sendRedirect("/sign-in");
        }else {
            String message = DAO.checkTime() + request.getSession().getAttribute("username");
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
        }
    }
}
