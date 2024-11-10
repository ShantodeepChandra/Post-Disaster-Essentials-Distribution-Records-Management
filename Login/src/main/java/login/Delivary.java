package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Delivary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/donation";
    public static String PASSWORD="root";
    public static String USERNAME = "root";
    Connection connection;
    public Delivary() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect to an HTML page
        response.sendRedirect("delivary.html");  // Replace "yourpage.html" with the path to your HTML file
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stateString = request.getParameter("state");
		String districtString = request.getParameter("district");
//		int population = Integer.parseInt(request.getParameter("headcount"));
		String popilationString = request.getParameter("headcount");
		String messageString = request.getParameter("message");
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<html><body><center>");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO donate VALUES (?,?,?,?)");
			preparedStatement.setString(1, stateString);
			preparedStatement.setString(2, districtString);
			preparedStatement.setString(3, popilationString);
			preparedStatement.setString(4, messageString);
            preparedStatement.executeUpdate();
			
			pWriter.println("<h1>Regitration Successfully done</h1>");
			pWriter.println("<a href=index.html>Login</a>");
		} catch (Exception e) {
			e.printStackTrace();
			pWriter.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
		pWriter.println("</center></body></html>");
	}

}
