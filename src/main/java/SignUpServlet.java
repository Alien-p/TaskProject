import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.myproj.DAO;


/**
 * Created by Ildar on 21.12.2016.
 */
@WebServlet(name = "SignUpServlet", urlPatterns = "/sign-up")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").toString();
        name = name.trim();     //удаляем пробелы в начале и конце
        String pass1 = request.getParameter("pass").toString();
        String pass2 = request.getParameter("pass2").toString();
        if(DAO.checkUserExist(name)){
            String message = "Пользовватель с таким именем уже зарегистрирован";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,response);
        }else if(!DAO.checkName(name) || name.length()  < 5){
            String message = "Имя пользователя должно быть длиннее 4 символов, состоять из цифр и букв английского алфавита";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,response);
        }else if(!pass1.equals(pass2)){
            String message = "Пароль не совпадает";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,response);
        }else if(pass1.length() < 8 || !DAO.checkPass(pass1)){
            String message = "Пароль недостаточно сложен: должны быть цифры, заглавные и строчные буквы и длина минимум 8 символов";
            request.setAttribute("message", message);
            request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,response);
        } else {
            DAO.addUser(name, pass1);
            request.getSession().setAttribute("username",name);
            response.sendRedirect("/welcome");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("username") != null){
            response.sendRedirect("/welcome");
        }else {
            request.getRequestDispatcher("WEB-INF/SignUp.jsp").forward(request,response);
        }
    }
}
