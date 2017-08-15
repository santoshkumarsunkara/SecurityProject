package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseconnection.DataBaseConnection;

/**
 * Servlet implementation class CheckingResult
 */
@WebServlet("/CheckingResult")
public class CheckingResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean status=false;
		PrintWriter out=response.getWriter();
		DataBaseConnection db=new DataBaseConnection();
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("customerID");
		try {
			 status = db.checkPercentage(email);
		} 
		catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		}
		
		if(status)
		{
			try {
				if(!db.checkAppliedOrNot(email))
				{	
				response.sendRedirect("applylicence.html");
				}
				else
					out.println("You have already applied for licence");
			} 
			catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
			}
		}
		else
		{
			out.println("<h2>Not elgible to apply for licence</h2>");
		}
	}
	
	

}
