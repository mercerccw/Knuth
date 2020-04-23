package servlets;

import daos.BerthDAO;
import daos.UserDAO;
import daos.VesselDAO;
import models.Berth;
import models.User;
import models.Vessel;

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
            VesselDAO vesselDAO = new VesselDAO();
            BerthDAO berthDAO = new BerthDAO();
            try {
                ResultSet resultSet = berthDAO.getAllBerthingData();
                ResultSet vesselSet = vesselDAO.getAllVessels();
                Berth berth;
                Vessel vessel;
                List<Berth> allBerths = new ArrayList<>();
                List<Vessel> allVessels = new ArrayList<>();
                while (vesselSet.next()){
                    vessel = new Vessel();
                    vessel.setImo(vesselSet.getInt("IMO"));
                    vessel.setFlag(vesselSet.getString("Flag"));
                    vessel.setName(vesselSet.getString("Name"));
                    vessel.setBuilt(vesselSet.getInt("Built"));
                    vessel.setCallSign(vesselSet.getString("CallSign"));
                    vessel.setLength(vesselSet.getInt("Length"));
                    vessel.setBreadth(vesselSet.getInt("Breadth"));
                    vessel.setTonnage(vesselSet.getInt("Tonnage"));
                    vessel.setMmsi(vesselSet.getInt("MMSI"));
                    vessel.setType(vesselSet.getString("Type"));
                    vessel.setOwnerCode(vesselSet.getInt("Owner_Code"));
                    allVessels.add(vessel);
                }
                while (resultSet.next()) {
                    berth = new Berth();
                    berth.setPort(resultSet.getString("ports.name"));
                    berth.setQuay(resultSet.getString("quays.name"));
                    berth.setType(resultSet.getString("type"));
                    berth.setNumber(resultSet.getString("number"));
                    berth.setShip_imo(resultSet.getInt("ship_imo"));
                    allBerths.add(berth);
                }
                request.setAttribute("allBerths", allBerths);
                request.setAttribute("allVessels", allVessels);
//                allVessels.forEach((berth_data) -> {
//                    System.out.println("PP---------------------------------");
//                    System.out.println("PP-IMO: " + berth_data.getImo());
//                    System.out.println("PP-Flag: " + berth_data.getFlag());
//                    System.out.println("PP-Name: " + berth_data.getName());
//                    System.out.println("PP-Built: " + berth_data.getBuilt());
//                    System.out.println("PP-Call Sign: " + berth_data.getCallSign());
//                    System.out.println("PP-Length: " + berth_data.getLength());
//                    System.out.println("PP-Breadth: " + berth_data.getBreadth());
//                    System.out.println("PP-Tonnage: " + berth_data.getTonnage());
//                    System.out.println("PP-MMSI: " + berth_data.getMmsi());
//                    System.out.println("PP-Type: " + berth_data.getType());
//                    System.out.println("PP-Owner Code: " + berth_data.getOwnerCode());
//                    System.out.println("PP----------------------------------");
//                });
                request.getRequestDispatcher("agent.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
