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
 * Servlet implementation class Student
 */
@WebServlet("/Student")
public class Student extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private DataSource datasource = null;
	sql query = new sql();
	String html = "";
	String page = "studentprofile";
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
		PreparedStatement stmt = con.prepareStatement(query.Studentlogin(uname,AM));
		ResultSet rs = stmt.executeQuery();		
		if (rs.isBeforeFirst()) {
			while (rs.next()) {
				String r_fname = rs.getString("fname");
				String r_sname = rs.getString("sname");
				String r_department = rs.getString("department");
				
				session=request.getSession();  
		        session.setAttribute("uname",uname);  
		        session.setAttribute("AM",AM);
		        session.setAttribute("department", r_department);
		        session.setAttribute("sname", r_sname);
		        session.setAttribute("fname", r_fname);
				
				System.out.println("Welcome " + session.getAttribute("sname") + " " +session.getAttribute("fname"));

				response.sendRedirect("studentprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "studentprofile";
		}
		} catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	
	public void printGradepc(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString(); 
			String course_code = request.getParameter("course_code");			
			PreparedStatement prestmt = con.prepareStatement(query.printGradepc(uname,AM,course_code));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String grade = rs.getString("grade");
					String row = "<tr><td>"+course_code.toString()+"</td><td>"+grade.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("studentgrades.jsp").forward(request, response); 
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
	
	public void printGradeps(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString(); 
			String semester = request.getParameter("semester");		
			PreparedStatement prestmt = con.prepareStatement(query.printGradeps(uname,AM,semester));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String grade = rs.getString("grade");
					String course_code = rs.getString("course_code");
					String row = "<tr><td>"+course_code.toString()+"</td><td>"+grade.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("studentgrades.jsp").forward(request, response); 
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
	
	public void printGrade(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html = "";	
			String uname = session.getAttribute("uname").toString();
			String AM = session.getAttribute("AM").toString(); 
			String semester = request.getParameter("semester");		
			PreparedStatement prestmt = con.prepareStatement(query.printGrade(uname,AM,semester));
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String grade = rs.getString("grade");
					String course_code = rs.getString("course_code");
					String row = "<tr><td>"+course_code.toString()+"</td><td>"+grade.toString()+"</td></tr>";
					html += row.toString();
				}
				request.setAttribute("html",html);
				request.getRequestDispatcher("studentgrades.jsp").forward(request, response); 
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
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Student() {
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
		else if (method.equals("printGradepc"))
			printGradepc(request,response);
		else if (method.equals("printGradeps"))
			printGradeps(request,response);
		else if (method.equals("printGrade"))
			printGrade(request,response);
		else if (method.equals("logout")){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		else if(method.equals("exit")) {
			session.invalidate();
			System.exit(0);

		}

				

	}
	
	
}

