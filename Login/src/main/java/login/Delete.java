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

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/volunteerdb";
    public static String PASSWORD="root";
    public static String USERNAME = "root";
    Connection connection;
    
    public Delete() {
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
		// TODO Auto-generated method stub
		response.sendRedirect("ConfirmDelete.html");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNameString = request.getParameter("username");
		String passwordString = request.getParameter("password");
		PrintWriter out = response.getWriter();
//		out.println("<html><body>");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from volunteerinfo where username=?");
			preparedStatement.setString(1, userNameString);
			ResultSet rSet = preparedStatement.executeQuery();
			if(rSet.next()) {
				if(rSet.getString("password").equals(passwordString)) {
					PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM volunteerinfo WHERE username=?");
					preparedStatement1.setString(1, userNameString);
					preparedStatement1.executeUpdate();
					out.println("<!DOCTYPE html>\r\n"
							+ "<html lang=\"en\">\r\n"
							+ "\r\n"
							+ "<head>\r\n"
							+ "    <meta charset=\"UTF-8\">\r\n"
							+ "    <meta http-equiv=\"refresh\" content=\"2; URL=/Login\">\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
							+ "    <title>Account Deleted</title>\r\n"
							+ "    <style>\r\n"
							+ "        * {\r\n"
							+ "            margin: 0;\r\n"
							+ "            padding: 0;\r\n"
							+ "            box-sizing: border-box;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        body {\r\n"
							+ "            display: flex;\r\n"
							+ "            justify-content: center;\r\n"
							+ "            align-items: center;\r\n"
							+ "            height: 100vh;\r\n"
							+ "            font-family: Arial, sans-serif;\r\n"
							+ "            background-color: #f0f2f5;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .container {\r\n"
							+ "            text-align: center;\r\n"
							+ "            padding: 40px;\r\n"
							+ "            background-color: #ffffff;\r\n"
							+ "            border-radius: 8px;\r\n"
							+ "            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);\r\n"
							+ "            width: 80%;\r\n"
							+ "            max-width: 400px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        h1 {\r\n"
							+ "            font-size: 24px;\r\n"
							+ "            color: #28a745;\r\n"
							+ "            margin-bottom: 20px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        p {\r\n"
							+ "            font-size: 16px;\r\n"
							+ "            color: #555555;\r\n"
							+ "            margin-bottom: 20px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .login-link {\r\n"
							+ "            display: inline-block;\r\n"
							+ "            padding: 10px 20px;\r\n"
							+ "            color: #ffffff;\r\n"
							+ "            background-color: #007bff;\r\n"
							+ "            border-radius: 4px;\r\n"
							+ "            text-decoration: none;\r\n"
							+ "            font-size: 16px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .login-link:hover {\r\n"
							+ "            background-color: #0056b3;\r\n"
							+ "        }\r\n"
							+ "    </style>\r\n"
							+ "</head>\r\n"
							+ "\r\n"
							+ "<body>\r\n"
							+ "    <div class=\"container\">\r\n"
							+ "        <h1>Account Deleted Successfully</h1>\r\n"
							+ "        <p>Your account has been removed from our system.</p>\r\n"
							+ "        <a href=\"index.html\" class=\"login-link\">Login</a>\r\n"
							+ "    </div>\r\n"
							+ "</body>\r\n"
							+ "\r\n"
							+ "</html>");
				}else {
					out.println("<html><body><center><h3>Incorrect Password</h3></center></body></html>");
				}
			}else {
				out.println("<html><body><center><h1>User not Registered</h1></center></body></html>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
