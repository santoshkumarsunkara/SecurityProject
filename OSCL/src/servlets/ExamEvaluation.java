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


@WebServlet("/ExamEvaluation")
public class ExamEvaluation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		
   		PrintWriter out=response.getWriter();
   		String[] values=new String[20];
   		int count=0;
   		
   		String[] answers={"a","a","a","a","a","a","a","a","a","a","a"};
   		
   		for(int i=1;i<11;i++)
   		{
   			values[i]=request.getParameter("q"+i);
   			
   		}
   		
		for(int i=1;i<=10;i++)
		{	
			if(values[i]!=null)
			{
				if(values[i].equals(answers[i]))
				{
				count+=1;
				}
			}
		}
		
		if(count>=6)
		{
			HttpSession session=request.getSession();
			String email=(String)session.getAttribute("customerID");
			DataBaseConnection db=new DataBaseConnection();
			try {
				
				int status=db.updateMarks(email,count*10);
				if(status>0)
				{
					response.sendRedirect("customersuccess.html");
				}
			} 
			catch (ClassNotFoundException | SQLException e) 
			{
				
				e.printStackTrace();
			}
		}
		else
		{	
			response.sendRedirect("customerfailure.html");
		}
		
		
	}

}
