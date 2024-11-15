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
import java.sql.ResultSet;
import java.sql.SQLException;

public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/volunteerdb";
    public static String PASSWORD="root";
    public static String USERNAME = "root";
    Connection connection;
	private String userNameString;
    public Edit() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
		response.sendRedirect("Edit.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println();
		String userNameString = request.getParameter("username");
		String passwordString = request.getParameter("password");
		String nameString = request.getParameter("name");
		String age = request.getParameter("age");
		String addressString = request.getParameter("address");
		String aadhaarString = request.getParameter("aadhaar");
		String emailIDString = request.getParameter("email");
		String phoneString = request.getParameter("phone");
		
		PrintWriter pWriter = response.getWriter();
		pWriter.println("<html><body><center>");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE volunteerinfo SET password=?,name=?,age=?,address=?,addhaar=?,email=?,phone=? WHERE username=?");
			preparedStatement.setString(8, userNameString);
			preparedStatement.setString(1, passwordString);
			preparedStatement.setString(2, nameString);
			preparedStatement.setString(3, age);
			preparedStatement.setString(4, addressString);
			preparedStatement.setString(5, aadhaarString);
			preparedStatement.setString(6, emailIDString);
			preparedStatement.setString(7, phoneString);
			preparedStatement.executeUpdate();
			
			pWriter.println("<h1>Update Successfully</h1>");
			pWriter.println("<a href=index.html>Login</a>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pWriter.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
		pWriter.println("</center></body></html>");  
	}

}
