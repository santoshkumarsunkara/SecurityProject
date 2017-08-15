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
 * Servlet implementation class PaymentDetails
 */
@WebServlet("/PaymentDetails")
public class PaymentDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cname,cnumber,cvvnumber,mobnumber,city,pincode,hno;
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("customerID");
		PrintWriter out=response.getWriter();
		cname=request.getParameter("cname");
		cnumber=request.getParameter("cardnumber");
		cvvnumber=request.getParameter("cvvnumber");
		mobnumber=request.getParameter("mobileno");
		city=request.getParameter("City");
		pincode=request.getParameter("pincode");
		hno=request.getParameter("houseno");
		DataBaseConnection db=new DataBaseConnection();
		
			int x=0;
			try {
					x = db.paymentDetails(cname,cnumber,cvvnumber,mobnumber,city,pincode,hno,email);
				} 
				catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
		if(x>0)
		{
			out.println("You have successfully applied for Licence, You will receive Licence in 10 Days");
		}
				
			
		
	
	}

}
