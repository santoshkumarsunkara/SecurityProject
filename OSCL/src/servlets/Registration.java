package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CutomerBean;
import databaseconnection.DataBaseConnection;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int x=0;
		PrintWriter out = response.getWriter();
		String email, fname, lname, gender, city, mobileno, password, repassword, dob, pincode, houseno;
		email = request.getParameter("email");
		fname = request.getParameter("fname");
		lname = request.getParameter("lname");
		mobileno = request.getParameter("mobileno");
		gender = request.getParameter("gender");
		city = request.getParameter("City");
		dob = request.getParameter("dob");
		pincode = request.getParameter("pincode");
		houseno = request.getParameter("houseno");
		password = request.getParameter("password");
		repassword = request.getParameter("repassword");

		if (password.equals(repassword)) {
			DataBaseConnection db = new DataBaseConnection();
			CutomerBean obj = new CutomerBean(email, fname, lname, mobileno, gender, city, dob, pincode, houseno,
					password);
			try{
				
				 x = db.customerRegistration(obj);
				} 
			catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
				}
			if(x>0)
			{
				response.sendRedirect("customerloginsuccess.html");
			}
			else
			{
				out.print("<h2>Registration has not happened successfully</h2>");
			}
			
		} 
		else {
			out.print("<h2>password does not match</h2>");
		}

	}

}
