package com.seed;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServJDBC2
 */
public class DemoServJDBC2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServJDBC2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();


		try 
		{
			String nm   = request.getParameter("uname");
			String pass = request.getParameter("pwd");
			
			/*ServletContext sc = getServletContext();
			String dbdriver= sc.getInitParameter("driver"); */
			
			String dbdriver= getServletContext().getInitParameter("driver");
    		String dburl   = getServletContext().getInitParameter("url");

			Class.forName(dbdriver);

						
			String dbuid = getServletConfig().getInitParameter("dbuser");
			String dbpass= getServletConfig().getInitParameter("dbpwd");

			Connection con = DriverManager.getConnection(dburl,  dbuid ,dbpass);

			System.out.println("connected successfully .... ");

			PreparedStatement pst=con.prepareStatement("select * from login where userid=? and upwd=?");
			pst.setString(1, nm);
			pst.setString(2, pass);
			
			ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				out.println("<i>Login successful </i>");				
			}
			else
			{
				out.println("Login failed ");
			}
			



		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}































//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class DemoServJDBC2
// */
//@WebServlet("/DemoServJDBC2")
//public class DemoServJDBC2 extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    /**
//     * Default constructor. 
//     */
//    public DemoServJDBC2() {
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
