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
import java.sql.*;
import com.mysql.cj.protocol.Resultset;


public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String  LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/volunteerdb";
    public static String PASSWORD="root";
    public static String USERNAME = "root";
    Connection connection;
    public loginServlet() {
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userNameString = request.getParameter("username");
		String passwordString = request.getParameter("password");
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from volunteerinfo where username=?");
			preparedStatement.setString(1, userNameString);
			ResultSet rSet = preparedStatement.executeQuery();
			PrintWriter out = response.getWriter();
//			pWriter.println("<html><body><center>");
			if(rSet.next()) {
				if(rSet.getString("password").equals(passwordString)) {
					String name = rSet.getString("name");
	                String age = rSet.getString("age");
	                String address = rSet.getString("address");
	                String aadhaar = rSet.getString("addhaar");
	                String email = rSet.getString("email");
	                String phone = rSet.getString("phone");

	                out.println("<html><head>");
	                out.println("<title>Welcome Page</title>");
	                out.println("<style>");
	                out.println("@import url('https://fonts.googleapis.com/css?family=Josefin+Sans&display=swap');\r\n"
	                		+ "\r\n"
	                		+ "*{\r\n"
	                		+ "    margin: 0;\r\n"
	                		+ "    padding: 0;\r\n"
	                		+ "    box-sizing: border-box;\r\n"
	                		+ "    list-style: none;\r\n"
	                		+ "    font-family: 'Josefin Sans', sans-serif;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ "body{\r\n"
	                		+ "    background-color: #fff1e1;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper{\r\n"
	                		+ "    position: absolute;\r\n"
	                		+ "    top: 50%;\r\n"
	                		+ "    left: 50%;\r\n"
	                		+ "    transform: translate(-50%,-50%);\r\n"
	                		+ "    width: 600px;\r\n"
	                		+ "    display: flex;\r\n"
	                		+ "    box-shadow: 0 1px 20px 0 rgba(69,90,100,.08);\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .left{\r\n"
	                		+ "    width: 35%;\r\n"
	                		+ "    background: linear-gradient(to right,#01a9ac,#01dbdf);\r\n"
	                		+ "    padding: 30px 25px;\r\n"
	                		+ "    border-top-left-radius: 5px;\r\n"
	                		+ "    border-bottom-left-radius: 5px;\r\n"
	                		+ "    text-align: center;\r\n"
	                		+ "    color: #fff;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .left img{\r\n"
	                		+ "    border-radius: 5px;\r\n"
	                		+ "    margin-bottom: 10px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .left h4{\r\n"
	                		+ "    margin-bottom: 10px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .left p{\r\n"
	                		+ "    font-size: 12px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right{\r\n"
	                		+ "    width: 65%;\r\n"
	                		+ "    background: #fff;\r\n"
	                		+ "    padding: 30px 25px;\r\n"
	                		+ "    border-top-right-radius: 5px;\r\n"
	                		+ "    border-bottom-right-radius: 5px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info,\r\n"
	                		+ ".wrapper .right .projects{\r\n"
	                		+ "    margin-bottom: 25px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info h3,\r\n"
	                		+ ".wrapper .right .projects h3{\r\n"
	                		+ "    margin-bottom: 15px;\r\n"
	                		+ "    padding-bottom: 5px;\r\n"
	                		+ "    border-bottom: 1px solid #e0e0e0;\r\n"
	                		+ "    color: #353c4e;\r\n"
	                		+ "    text-transform: uppercase;\r\n"
	                		+ "    letter-spacing: 5px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info_data,\r\n"
	                		+ ".wrapper .right .projects_data{\r\n"
	                		+ "    display: flex;\r\n"
	                		+ "    justify-content: space-between;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info_data .data,\r\n"
	                		+ ".wrapper .right .projects_data .data{\r\n"
	                		+ "    width: 45%;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info_data .data h4,\r\n"
	                		+ ".wrapper .right .projects_data .data h4{\r\n"
	                		+ "    color: #353c4e;\r\n"
	                		+ "    margin-bottom: 5px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .right .info_data .data p,\r\n"
	                		+ ".wrapper .right .projects_data .data p{\r\n"
	                		+ "    font-size: 13px;\r\n"
	                		+ "    margin-bottom: 10px;\r\n"
	                		+ "    color: #919aa3;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .social_media ul{\r\n"
	                		+ "    display: flex;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .social_media ul li{\r\n"
	                		+ "    width: 45px;\r\n"
	                		+ "    height: 45px;\r\n"
	                		+ "    background: linear-gradient(to right,#01a9ac,#01dbdf);\r\n"
	                		+ "    margin-right: 10px;\r\n"
	                		+ "    border-radius: 5px;\r\n"
	                		+ "    text-align: center;\r\n"
	                		+ "    line-height: 45px;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".wrapper .social_media ul li a{\r\n"
	                		+ "    color :#fff;\r\n"
	                		+ "    display: block;\r\n"
	                		+ "    font-size: 18px;\r\n"
	                		+ "}"
	                		+ ".procced {\r\n"
	                		+ "    margin-right: 15px; background-color: #FFE66D;  /* Adds spacing between forms */\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ ".edit-details {\r\n"
	                		+ "    margin-right: 15px; background-color: #1e847f;  /* Adds spacing between forms */\r\n"
	                		+ "}\r\n"
	                		+".delete {\r\n"
	                		+ "    margin-right: 15px; background-color: #FF6B6B;  /* Adds spacing between forms */\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ "button {\r\n"
	                		+ "    padding: 10px 20px;\r\n"
	                		+ "    font-size: 16px;\r\n"
	                		+ "    border: none;\r\n"
	                		+ "    border-radius: 5px;\r\n"
	                		+ "    color: white;\r\n"
	                		+ "    cursor: pointer;\r\n"
	                		+ "}\r\n"
	                		+ "\r\n"
	                		+ "button:hover {\r\n"
	                		+ "    background-color: #0056b3;\r\n"
	                		+ "}\r\n"
	                		+ "");
	                out.println("</style>");
	                out.println("</head><body>");
	                
	               out.println("<div class=\"wrapper\">\r\n"
	               		+ "    <div class=\"left\">\r\n"
	               		+ "        <img src=\"Me.jpg\"\r\n"
	               		+ "             alt=\"user\" width=\"100\">");
	               out.println(" <h4>"+name+"</h4>");
	               out.println("<p>"+age+"</p></div>");
	               out.println("<div class=\"right\">\r\n"
	               		+ "        <div class=\"info\">\r\n"
	               		+ "            <h3>Information</h3>\r\n"
	               		+ "            <div class=\"info_data\">\r\n"
	               		+ "                <div class=\"data\">\r\n"
	               		+ "                    <h4>Email</h4>");
	               out.println("<p>"+email+"</p></div>");
	               out.println("<div class=\"data\">\r\n"
	               		+ "                    <h4>Phone</h4>");
	               out.println("<p>"+phone+"</p>");
	               out.println("</div>\r\n"
	               		+ "            </div>\r\n"
	               		+ "        </div>");
	                out.println("<div class=\"projects\">\r\n"
	                		+ "            <div class=\"projects_data\">\r\n"
	                		+ "                <div class=\"data\">");
	                out.println("<p>"+aadhaar+"</p>");
	                out.println("</div>\r\n"
	                		+ "                <div class=\"data\">");
	                out.println("<p>"+address+"</p>");
	                out.println("</div>\r\n"
	                		+ "            </div>\r\n"
	                		+ "        </div>\r\n"
	                		+ "\r\n"
	                		+ "        <div class=\"social_media\">\r\n"
	                		+ "            <ul>\r\n"
	                		+ "                <form method=\"get\" action=\"Delivary\"><button class=\"procced\" type=\"submit\">procced</button></form>\r\n"
	                		+ "                <form method=\"get\" action=\"Edit\"><button class=\"edit-details\" type=\"submit\">Edit Details</button></form>\r\n"
	                		+  "               <form method=\"get\" action=\"Delete\"><button class=\"delete\" type=\"submit\">Delete</button></form>\r\n"
	                		+ "            </ul>\r\n"
	                		+ "        </div>\r\n"
	                		+ "    </div>\r\n"
	                		+ "</div>");
	                out.println("</div></body></html>");
				}else {
					out.println("<html><body><center><h3>Incorrect Password</h3></center></body></html>");
				}
				
			}else {
				out.println("<html><body><center><h1>User not Registered</h1></center></body></html>");
			}
//			out.println("</center></body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			out.println("<h3>Error: " + e.getMessage() + "</h3>");
		}
	}

}
