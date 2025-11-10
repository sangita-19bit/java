import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/yourDBname", "root", "yourPassword");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Attendance(StudentID, Date, Status) VALUES (?, ?, ?)");
            ps.setInt(1, studentId);
            ps.setString(2, date);
            ps.setString(3, status);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h2>Attendance marked successfully!</h2>");
            } else {
                out.println("<h2>Failed to record attendance.</h2>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
