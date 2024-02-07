package com.purna.hospital;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Patients extends HttpServlet {
   
	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		 String id = request.getParameter("id");
		 String name = request.getParameter("name");
		 String gender = request.getParameter("gender");
		 String disease = request.getParameter("disease");
		 String history = request.getParameter("history");
		  
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			pstmt = conn.prepareStatement("insert into patient values(?,?,?,?,?)");
			 pstmt.setString(1,id);
			 pstmt.setString(2,name);
			 pstmt.setString(3,gender);
			 pstmt.setString(4,disease);
			 pstmt.setString(5,history);
			 int n = pstmt.executeUpdate();
			
			 if(n>0) {
				 out.print("record updated successfully");
			 }else {
				 out.print("failed to update your record");
			 }
			 
			 
		}catch(ClassNotFoundException | SQLException e) {
			response.sendError(411, e.toString());
		}
		finally {
			try {
				
				if(pstmt!=null) {
					pstmt.close();
				}
				if(conn!=null)
				{
					conn.close();
				}
				
			}catch(SQLException e) {}
		}
		
	}
}
