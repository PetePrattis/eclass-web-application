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
@WebServlet("/Secretary")
public class Secretary extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private DataSource datasource = null;
	sql query = new sql();
	String html = "";
	String page = "secretaryprofile";
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
		PreparedStatement stmt = con.prepareStatement(query.Secretarylogin(uname,AM));
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
				response.sendRedirect("secretaryprofile.jsp");
				
				con.close();
				rs.close();
			}
		}
		else {
			page="index";
			request.setAttribute("page",page);
			request.getRequestDispatcher("error.jsp").forward(request, response);
			page = "secretaryprofile";
		}
		} catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void viewCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			//String uname = session.getAttribute("uname").toString();
			//String AM = session.getAttribute("AM").toString(); 		
			PreparedStatement prestmt = con.prepareStatement(query.viewCourse());
			ResultSet rs = prestmt.executeQuery();
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					String course_code = rs.getString("course_code");
					String fname = rs.getString("fname");
					String sname = rs.getString("sname");
					String department = rs.getString("department");
					String semester = rs.getString("semester");
					String row1 = "<tr><td>"+fname.toString()+"</td><td>"+sname.toString()+"</td><td>"+course_code.toString()+"</td><td>"+department.toString()+"</td><td>"+semester.toString()+"</td></tr>";
					html += row1.toString();
				}
				rs.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
			PreparedStatement prestmt1 = con.prepareStatement(query.viewCoursenone());
			ResultSet rs1 = prestmt1.executeQuery();
			if (rs1.isBeforeFirst()) {
				while (rs1.next()) {
					String course_code = rs1.getString("course_code");
					String department = rs1.getString("department");
					String semester = rs1.getString("semester");
					String names = "none";
					String row1 = "<tr><td>"+names+"</td><td>"+names+"</td><td>"+course_code.toString()+"</td><td>"+department.toString()+"</td><td>"+semester.toString()+"</td></tr>";
					html += row1.toString();
				}
				rs1.close();
				
				}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			
		request.setAttribute("html",html);
		request.getRequestDispatcher("secretarypage.jsp").forward(request, response); 
		con.close();	
		
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<form method=\"post\" action=\"Secretary\"><input type=\"hidden\" name=\"method\" value=\"assignCourse\">"
					+ "<tr><td><input type=\"text\" name=\"fname\"></td><td><input type=\"text\" name=\"sname\"></td><td><input type=\"text\" name=\"course_code\"></td><td><input type=\"text\" name=\"department\"></td><td><input type=\"text\" name=\"semester\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"insert\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("secretarypage.jsp").forward(request, response); 
			con.close();

		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void assignCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String fname = request.getParameter("fname").toString();
			String sname = request.getParameter("sname").toString(); 	
			String course_code = request.getParameter("course_code").toString(); 	
			String department = request.getParameter("department").toString(); 	
			String semester = request.getParameter("semester").toString(); 	
			PreparedStatement prestmt1 = con.prepareStatement(query.checkAssignmentp(fname, sname));
			PreparedStatement prestmt2 = con.prepareStatement(query.checkAssignmentc(course_code));
			ResultSet rs1 = prestmt1.executeQuery();
			ResultSet rs2 = prestmt2.executeQuery();
			if (rs1.isBeforeFirst() && rs2.isBeforeFirst() && tryParseInt(semester) && (department.equals("informatics") || department.equals("economics") || department.equals("shipping"))) {
				while (rs1.next()) {
					String AM = rs1.getString("AM");
					PreparedStatement prestmt = con.prepareStatement(query.assignCourse(course_code, AM));
					prestmt.executeUpdate();
				}
				
				insertGradeindex(request,response, course_code, department, semester);
				response.sendRedirect("secretaryprofile.jsp");
				rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertGradeindex(HttpServletRequest request, HttpServletResponse response, String course_code, String department, String semester) {
		try {
			Connection con = datasource.getConnection();				
			PreparedStatement prestmt1 = con.prepareStatement(query.findStudent(course_code, department));
			ResultSet rs1 = prestmt1.executeQuery();
			if (rs1.isBeforeFirst()) {
				while (rs1.next()) {
					String uname = rs1.getString("uname");
					String AM = rs1.getString("AM");
					PreparedStatement prestmt2 = con.prepareStatement(query.createGrade(uname,AM,department,course_code));
					prestmt2.executeUpdate();
				}				
				rs1.close();
				con.close();				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}		
	}
	
	public void createStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>User Name</th>\r\n" + 
					"			<th>First Name</th>\r\n" + 
					"			<th>Surname</th>\r\n" + 
					"			<th>Department</th>\r\n" + 
					"			<th>AM</th>\r\n" + 
					"		</tr><form method=\"post\" action=\"Secretary\"><input type=\"hidden\" name=\"method\" value=\"insertStudent\">"
					+ "<tr><td><input type=\"text\" name=\"uname\"></td><td><input type=\"text\" name=\"fname\"></td><td><input type=\"text\" name=\"sname\"></td><td><input type=\"text\" name=\"department\"></td><td><input type=\"text\" name=\"AM\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"insert\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("secretarycreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String uname = request.getParameter("uname").toString();
			String fname = request.getParameter("fname").toString();
			String sname = request.getParameter("sname").toString(); 	 	
			String department = request.getParameter("department").toString(); 	
			String AM = request.getParameter("AM").toString();
			
			boolean unique = true;
			PreparedStatement prestmt1 = con.prepareStatement(query.uniqueStudentAM(getHash(AM.getBytes(),"MD5")));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}
			if (unique && tryParseInt(AM) && (department.equals("informatics") || department.equals("economics") || department.equals("shipping"))) {
				AM = getHash(AM.getBytes(),"MD5");
				PreparedStatement prestmt2 = con.prepareStatement(query.insertStudent(uname,fname,sname,department,AM));
				prestmt2.executeUpdate();
				PreparedStatement prestmt3 = con.prepareStatement(query.findCourses(department));
				ResultSet rs3 = prestmt3.executeQuery();
				if (rs3.isBeforeFirst()) {
					while(rs3.next()) {
						if(!rs3.getString("AM").equals("none")) {
							insertGradeindex(request,response, rs3.getString("course_code"), department, rs3.getString("semester"));
						}
					}
				}
						
				
				response.sendRedirect("secretaryprofile.jsp");
				rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void createProfessor(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>User Name</th>\r\n" + 
					"			<th>First Name</th>\r\n" + 
					"			<th>Surname</th>\r\n" +  
					"			<th>AM</th>\r\n" + 
					"		</tr><form method=\"post\" action=\"Secretary\"><input type=\"hidden\" name=\"method\" value=\"insertProfessor\">"
					+ "<tr><td><input type=\"text\" name=\"uname\"></td><td><input type=\"text\" name=\"fname\"></td><td><input type=\"text\" name=\"sname\"></td><td><input type=\"text\" name=\"AM\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"insert\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("secretarycreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertProfessor(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String uname = request.getParameter("uname").toString();
			String fname = request.getParameter("fname").toString();
			String sname = request.getParameter("sname").toString(); 	 		
			String AM = request.getParameter("AM").toString();
			
			boolean unique = true;
			PreparedStatement prestmt1 = con.prepareStatement(query.uniqueProfessorAM(getHash(AM.getBytes(),"MD5")));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}
			if (unique && tryParseInt(AM)) {
				AM = getHash(AM.getBytes(),"MD5");
				PreparedStatement prestmt2 = con.prepareStatement(query.insertProfessor(uname,fname,sname,AM));
				prestmt2.executeUpdate();
				
				response.sendRedirect("secretaryprofile.jsp");
				rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void createCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			html="";
			
			String row = "<tr>\r\n" + 
					"			<th>Course Code</th>\r\n" +  
					"			<th>Department</th>\r\n" + 
					"			<th>Semester</th>\r\n" + 
					"		</tr><form method=\"post\" action=\"Secretary\"><input type=\"hidden\" name=\"method\" value=\"insertCourse\">"
					+ "<tr><td><input type=\"text\" name=\"course_code\"></td><td><input type=\"text\" name=\"department\"></td><td><input type=\"text\" name=\"semester\"></td></tr>";
			html += row.toString();
			
			String row1 ="<br><br>\r\n" + 
					"  <input type=\"submit\" value=\"insert\"></form> ";
			html =html + row1.toString();				
			request.setAttribute("html",html);
			request.getRequestDispatcher("secretarycreate.jsp").forward(request, response); 
			con.close();
		}
		catch (SQLException | IOException |  ServletException e) {
			e.printStackTrace();
		}
	}
	
	public void insertCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			Connection con = datasource.getConnection();
			String course_code = request.getParameter("course_code").toString();
			String semester = request.getParameter("semester").toString(); 	 	
			String department = request.getParameter("department").toString(); 	
			boolean unique = true;
			PreparedStatement prestmt1 = con.prepareStatement(query.uniqueCourseCode(course_code));
			ResultSet rs1 = prestmt1.executeQuery();
			if(rs1.isBeforeFirst()) {
				while (rs1.next()) {
					if (rs1.getString("case").equals("YES")) {
						unique = false;
					}
				}
			}
			if (unique && tryParseInt(semester) && (department.equals("informatics") || department.equals("economics") || department.equals("shipping"))) {
				
				PreparedStatement prestmt2 = con.prepareStatement(query.insertCourse(course_code,department,semester));
				prestmt2.executeUpdate();
				
				response.sendRedirect("secretaryprofile.jsp");
				rs1.close();
				con.close();
				
			}		
			else {
				request.setAttribute("page",page);
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
		catch (SQLException | IOException | ServletException e) {
			e.printStackTrace();
		}
	}
	
	boolean tryParseInt(String value) {  
	     try {  
	         Integer.parseInt(value);  
	         return true;  
	      } catch (NumberFormatException e) {  
	         return false;  
	      }  
	}
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Secretary() {
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
		else if (method.equals("viewCourse"))
			viewCourse(request,response);
		else if (method.equals("getCourse"))
			getCourse(request,response);
		else if (method.equals("assignCourse"))
			assignCourse(request,response);
		else if (method.equals("logout")){
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		else if (method.equals("createStudent"))
			createStudent(request,response);
		else if (method.equals("createProfessor"))
			createProfessor(request,response);
		else if (method.equals("createCourse"))
			createCourse(request,response);
		else if (method.equals("insertStudent"))
			insertStudent(request,response);
		else if (method.equals("insertProfessor"))
			insertProfessor(request,response);
		else if (method.equals("insertCourse"))
			insertCourse(request,response);


	}
	
	
}
