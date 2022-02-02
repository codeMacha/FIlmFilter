package com.cognixia.jump.film;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FilmServlet")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection conn;
	
	private PreparedStatement pstmt;
  

	public void init(ServletConfig config) throws ServletException {
		
		try {			
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement("select * from film where rating = ? and rental_rate = ? limit ?");		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter pw = response.getWriter();
		
		String rating = request.getParameter("rating");
		double rentalCost = Double.parseDouble(request.getParameter("rental_rate"));
		int limit = Integer.parseInt(request.getParameter("result-size"));
		
		String title = null;
		String description = null;
		boolean retrieved = false;
		Map<String, String> films = new HashMap<>();
		
		try {
			
			pstmt.setString(1, rating);
			pstmt.setDouble(2, rentalCost);
			pstmt.setInt(3, limit);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				title = rs.getString("title");
				description = rs.getString("description");
				films.put(title, description);
				retrieved = true;
			}

			rs.close();
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		if(retrieved) {
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>Movie Servlet</title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h2>List of "+ rating + " and rental cost " + rentalCost+" films and their Description</h2>");
			pw.println("<ol>");
			for(Map.Entry<String, String>entry: films.entrySet()) {
				pw.println("<li>"+ entry.getKey() + " " + entry.getValue() + "</li>");
			}
			pw.println("</ol>");
			pw.println("</body>");
			pw.println("</html>");
		} else {
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>ActorServlet </title>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h1> Error: Actor-ID provided doesnt match</h1>");
			pw.println("</body>");
			pw.println("</html>");
		}
		
	}

}
