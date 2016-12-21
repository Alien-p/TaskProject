import java.io.IOException;
import com.myproj.DAO;
import javax.servlet.annotation.WebServlet;


@WebServlet(name = "SignInServlet", urlPatterns = "/sign-in")
public class SignInServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try{
            if(request.getParameter("exit") != null){
                request.getSession().invalidate();
                String exitMessage = "Вы вышли из приложения";
                request.setAttribute("exitMessage",exitMessage);
                request.getRequestDispatcher("WEB-INF/SignIn.jsp").forward(request,response);
            }
        }
        catch (NullPointerException e){}
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        if(DAO.checkUser(name,pass)){
            request.getSession().setAttribute("username", name);
            response.sendRedirect("/welcome");
        } else if(name == "" && pass == ""){
            String message = "Необходимо ввести учетные данные";
            request.setAttribute("emptyMessage", message);
            request.getRequestDispatcher("WEB-INF/SignIn.jsp").forward(request,response);
        } else{
            String message = "Имя пользователя и пароль не подходят";
            request.setAttribute("errorMessage", message);
            request.getRequestDispatcher("WEB-INF/SignIn.jsp").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if(request.getSession().getAttribute("username") != null){
            response.sendRedirect("/welcome");
        }else {
            request.getRequestDispatcher("WEB-INF/SignIn.jsp").forward(request,response);
        }
    }
}
