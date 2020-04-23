package servlets;

import daos.BerthDAO;
import daos.UserDAO;
import models.Berth;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/agent")
public class AgentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AgentServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        UserDAO userDao = new UserDAO();
        User revised_user = null;
        try {
            revised_user = userDao.checkPosition(user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert revised_user != null;
        user.setPosition(revised_user.getPosition());
        session.setAttribute("user", user);
        if (!user.getPosition().equals("agent")) {
            response.sendRedirect(request.getContextPath() + "/" + user.getPosition());
        } else {
            BerthDAO berthDAO = new BerthDAO();
            try {
                ResultSet resultSet = berthDAO.getAllBerthingData();
                Berth berth;
                List<Berth> berthList = new ArrayList<>();
                while (resultSet.next()) {
                    berth = new Berth();
                    berth.setPort(resultSet.getString("ports.name"));
                    berth.setQuay(resultSet.getString("quays.name"));
                    berth.setType(resultSet.getString("type"));
                    berth.setNumber(resultSet.getString("number"));
                    berth.setShip_imo(resultSet.getInt("ship_imo"));
                    berthList.add(berth);
                }
                request.setAttribute("berthList", berthList);
                request.getRequestDispatcher("agent.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
