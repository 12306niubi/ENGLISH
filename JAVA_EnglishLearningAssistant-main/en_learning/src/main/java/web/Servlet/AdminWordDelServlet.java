package web.Servlet;

import domain.words;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AdminWordDelServlet")
public class AdminWordDelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> map = request.getParameterMap();

        words word=new words();
        try {
            BeanUtils.populate(word,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        AdminService service=new AdminService();
        service.delWord(word);

        request.setAttribute("profile_msg","Success");
        request.getRequestDispatcher("/admin_word_update.jsp").forward(request,response);
    }
}
