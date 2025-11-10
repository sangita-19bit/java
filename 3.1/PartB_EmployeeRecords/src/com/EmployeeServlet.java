import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empid = request.getParameter("empid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/yourDBname", "root", "yourPassword");

            Statement stmt = con.createStatement();
            ResultSet rs;

            if (empid != null && !empid.isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empid);
            } else {
                rs = stmt.executeQuery("SELECT * FROM Employee");
            }

            out.println("<h2>Employee Details</h2>");
            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");

            boolean found = false;
            while (rs.next()) {
                found = true;
                out.println("<tr><td>" + rs.getInt(1) + "</td><td>" +
                        rs.getString(2) + "</td><td>" + rs.getDouble(3) + "</td></tr>");
            }

            out.println("</table>");
            if (!found) out.println("<p>No employee found with ID: " + empid + "</p>");

            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
