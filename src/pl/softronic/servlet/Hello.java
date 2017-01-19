package pl.softronic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
@WebServlet({ "/hello.do", "/h" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("Witaj œwiecie! <br/>¥Ê¯Æ¹ê<br/>");
		Date date = new Date();
		
		out.println("czas: "+df.format(date));
		out.println("</HTML>");
	}

}
