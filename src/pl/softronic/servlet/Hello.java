package pl.softronic.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.softronic.servlet.czat.model.Message;

/**
 * Servlet implementation class Hello
 */
@WebServlet({ "/hello.do", "/h" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public void init(ServletConfig config) throws ServletException {
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Wykonano POST");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		if(name != null){
			request.getSession().setAttribute("user", name);
		}
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("Witaj œwiecie! <br/>¥Ê¯Æ¹ê<br/>");
		
		String name = request.getParameter("name");
		if(name != null){
			request.getSession().setAttribute("user", name);
		}
		name = (String) request.getSession().getAttribute("user");
		if(name != null){
			out.println("name: "+name+"<br/>");
		}
		
		String form = "<form action='/WebApp/hello.do' method='POST'>"
				+ "<input type='text' name='name' />"
				+ "<input type='submit'/>"
				+ "</form>";
		
		Date date = new Date();
		out.println("czas: "+df.format(date));
		
		out.println("<p>"+form+"</p>");
		
		Message msg = new Message("Ala", "Brak mi kota!");
		out.println("<table>"+msg.getHTML()+"</table>"); 
		
		out.println("</HTML>");
		
	}

}
