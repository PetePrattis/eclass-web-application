package teliki_september;

public class sql {
	
	public String Studentlogin(String uname, String AM) {
		String s = "SELECT * FROM students WHERE AM='" + AM + "'AND uname='" + uname + "'";
		return(s);
	}
	
	public String printGradepc(String uname, String AM, String course_code) {
		String s = "SELECT * FROM grades WHERE AM='" + AM + "'AND uname='" + uname + "' AND course_code='" + course_code+ "'";
		return(s);
	}
	
	public String printGradeps(String uname, String AM, String semester) {
		String s = "SELECT g.course_code, g.grade FROM grades g, courses c WHERE c.semester='" + semester + "'AND g.AM='" + AM + "' AND c.course_code=g.course_code";
		return(s);
	}
	
	public String printGrade(String uname, String AM, String semester) {
		String s = "SELECT g.course_code, g.grade FROM grades g, courses c WHERE g.AM='" + AM + "' AND c.course_code=g.course_code";
		return(s);
	}
	
	public String Professorlogin(String uname, String AM) {
		String s = "SELECT * FROM professors WHERE AM='" + AM + "'AND uname='" + uname + "'";
		return(s);
	}
	
	public String viewGrade(String uname, String AM) {
		String s = "SELECT s.fname, s.sname, g.course_code, g.grade FROM grades g, courses c, professors p, students s WHERE p.AM='" + AM + "' AND p.AM=c.AM AND c.course_code=g.course_code AND g.uname=s.uname";
		return(s);
	}
	
	
	public String findGrade(String uname, String AM, String course_code) {
		String s = "SELECT s.fname, s.sname, g.course_code, g.grade, s.uname, s.AM FROM grades g, courses c, professors p, students s WHERE p.AM='" + AM + "' AND p.AM=c.AM AND c.course_code=g.course_code AND g.uname=s.uname AND g.grade='none' AND g.course_code='"+course_code+"'";
		return(s);
	}
	
	public String setGrade(String uname, String AM, String course_code, String grade) {
		String s = "UPDATE grades SET grade='"+grade+"' WHERE uname='"+uname+"' AND AM='"+AM+"' AND course_code='"+course_code+"'";
		return(s);
	}
	
	public String Secretarylogin(String uname, String AM) {
		String s = "SELECT * FROM secretaries WHERE AM='" + AM + "'AND uname='" + uname + "'";
		return(s);
	}
	
	public String viewCourse() {
		String s = "SELECT c.course_code, p.fname, p.sname, c.department, c.semester FROM courses c, professors p WHERE c.AM=p.AM";
		return(s);
	}
	
	public String viewCoursenone() {
		String s = "SELECT course_code, department, semester FROM courses WHERE AM='none'";
		return(s);
	}
	
	public String checkAssignmentp(String fname, String sname) {
		String s = "SELECT AM FROM professors WHERE fname='"+fname+"' AND sname='"+sname+"'";
		return(s);
	}
	
	public String checkAssignmentc(String course_code) {
		String s = "SELECT AM FROM courses WHERE course_code='"+course_code+"' and AM='none'";
		return(s);
	}
	
	public String assignCourse(String course_code, String AM) {
		String s = "UPDATE courses SET AM='"+AM+"' WHERE course_code='"+course_code+"'";
		return(s);
	}
	
	public String findStudent(String course_code, String department) {
		String s = "SELECT s.uname, s.AM FROM students s, courses c WHERE c.course_code='"+course_code+"' AND c.department='"+department+"' AND c.department=s.department";
		return(s);
	}
	
	public String createGrade(String uname,String AM,String department,String course_code) {
		String s = "INSERT INTO grades(uname,AM,department,course_code,grade) VALUES ('"+uname+"','"+AM+"','"+department+"','"+course_code+"','none');";
		return(s);
	}
	
	public String uniqueStudentAM(String AM) {
		String s = "select case when AM='"+AM+"'then 'YES' else 'NO' end from students;";
		return(s);
	}
	
	public String insertStudent(String uname,String fname,String sname,String department,String AM) {
		String s = "INSERT INTO students(uname,fname,sname,department,AM) VALUES ('"+uname+"','"+fname+"','"+sname+"','"+department+"','"+AM+"');";
		return(s);
	}
	
	public String uniqueProfessorAM(String AM) {
		String s = "select case when AM='"+AM+"'then 'YES' else 'NO' end from professors;";
		return(s);
	}
	
	public String insertProfessor(String uname,String fname,String sname,String AM) {
		String s = "INSERT INTO professors(uname,fname,sname,AM) VALUES ('"+uname+"','"+fname+"','"+sname+"','"+AM+"');";
		return(s);
	}
	
	public String uniqueCourseCode(String course_code) {
		String s = "select case when course_code='"+course_code+"'then 'YES' else 'NO' end from courses;";
		return(s);
	}
	
	public String insertCourse(String course_code,String department,String semester) {
		String s = "INSERT INTO courses(course_code,AM,department,semester) VALUES ('"+course_code+"','none','"+department+"','"+semester+"');";
		return(s);
	}
	
	public String findCourses(String department) {
		String s = "SELECT * FROM courses WHERE department='"+department+"'";
		return(s);
	}

}
