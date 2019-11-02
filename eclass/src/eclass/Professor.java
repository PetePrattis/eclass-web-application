package eclass;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.*;  
import javax.servlet.http.*;  
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

import eclass.sql;

/**
 * Servlet implementation class Professor
 */
@WebServlet("/Professor")
public class Professor extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	private DataSource datasource = null;
	sql query = new sql();
	String html = "";
	String info = "";
	String page = "professorprofile";
	Integer gradecount=0;
	public static HttpSession session;
	 
	
	public void init() throws ServletException {
		try {

			InitialContext ctx = new InitialContext();
			datasource = (DataSource) ctx.lookup("java:comp/env/jdbc/LiveDataSource");
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}

	}
	
	public static String getHash(byte[] inputBytes, String algorithm){
    	String hashValue="";
    	try{
    		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
    		messageDigest.update(inputBytes);
    		byte[] digestedBytes = messageDigest.digest();
    		hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
    		
    	}
    	catch(Exception e){}
    	return hashValue;
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) {
		Connection con;
		try {
			con = datasource.getConnection();

		String uname = request.getParameter("uname");
		String AM = request.getParameter("AM");
		AM = getHash(AM.getBytes(),"MD5");
		PreparedStatement stmt = con.prepareStatement(query.Professorlogin(uname,AM));
		ResultSet rs = stmt.executeQuery();
		
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String r_fname = rs.getString("fname");
				String r_sname = rs.getString("sname");
				
				session=request.getSession();  
		        session.setAttribute("uname",uname);  
		        session.setAttribute("AM",AM);
		        session.setAttribute("sname", r_sname);
		        session.setAttribute("fname", r_fname);
				
				System.out.println("Welcome " + session.getAttribute("sname") + " " +session.getAttribute("fname"));
				response.sendRedirect("professorprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "professorprofile";
		}
		} catch (SQLException | IOException  |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void viewGrade(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString(); 					
			PreparedStatement prestmt = con.prepareStatement(query.viewGrade(uname,AM));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String grade = rs.getString("grade");
					String course_code = rs.getString("course_code");
					String fname = rs.getString("fname");
					String sname = rs.getString("sname");
					String row = "<tr><td>"+fname.toString()+"</td><td>"+sname.toString()+"</td><td>"+course_code.toString()+"</td><td>"+grade.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("professorpage.jsp").forward(request, response); 
				rs.close();
				con.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertGrade(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString();
			String course_code = request.getParameter("course_code");
			info = course_code;
			PreparedStatement prestmt = con.prepareStatement(query.findGrade(uname,AM,course_code));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				gradecount=0;
				while (rs.next()) {
					String unames = rs.getString("uname");
					String fname = rs.getString("fname");
					String sname = rs.getString("sname");
					String row = "<form method=\"post\" action=\"Professor\">\r\n" + 
							"  <input type=\"hidden\" name=\"method\" value=\"insert\"><tr><td>"+fname.toString()+"</td><td>"+sname.toString()+"</td><td>"+course_code.toString()+"</td><td><input type=\"text\" name=\"grade"+gradecount.toString()+"\"></td></tr>";
					html += row.toString();
					gradecount++;
				}
				String row1 ="<br><br>\r\n" + 
						"  <input type=\"submit\" value=\"insert\"></form> ";
				html =html + row1.toString();
				request.setAttribute("html",html);
				request.getRequestDispatcher("professorpage.jsp").forward(request, response); 
				rs.close();
				con.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
		
	}
	
	public void changeGrade(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString();
			String course_code = info;
			PreparedStatement prestmt = con.prepareStatement(query.findGrade(uname,AM,course_code));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				Integer count=0;
				while (rs.next()) {
					if (!request.getParameter("grade"+count.toString()+"").equals("")) {
						String unames = rs.getString("uname");
						String AMs = rs.getString("AM");
						String grade = request.getParameter("grade"+count.toString()+"");
						PreparedStatement prestmt1 = con.prepareStatement(query.setGrade(unames,AMs,course_code,grade));
						prestmt1.executeUpdate();
						count++;
					}
					
				}
				
				response.sendRedirect("professorprofile.jsp");
				rs.close();
				con.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}}
		catch (SQLException | IOException  |  ServletException e) {
			e.printStackTrace();
		}

	}
	
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Professor() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		
		if (method.equals("login"))
			login(request,response);
		else if (method.equals("viewGrade"))
			viewGrade(request,response);
		else if (method.equals("insertGrade"))
			insertGrade(request,response);
		else if (method.equals("insert"))
			changeGrade(request,response);
		else if (method.equals("logout")){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}


	}

}
