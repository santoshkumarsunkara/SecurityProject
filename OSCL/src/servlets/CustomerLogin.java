package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseconnection.DataBaseConnection;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username, password;
		boolean status=false;
		DataBaseConnection db=new DataBaseConnection();
		username = request.getParameter("email");
		password = request.getParameter("password");
		try {
			status=db.customerLogin(username, password);
		} 
		catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
		if(status)
		{
			session.setAttribute("customerID", username);
			response.sendRedirect("customer.html");
		}
		else
		{
			response.sendRedirect("invalidcredentials.html");
		}
	}

}
