package pl.softronic.servlet.czat.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.softronic.servlet.czat.model.Message;

@WebServlet("/czat")
public class CzatMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final LinkedList<Message> czatList = new LinkedList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String user = (String) request.getSession().getAttribute("userWSesji");
		
		String form = "<p><form action='/WebApp/czat' method='POST'>"
				
				+ "<input type='text' name='user' "
				+ (user!=null ? "value='"+ user +"'" : "autofocus='autofocus'")
				+ "/>"
				
				+ "<input type='text' name='msg' "
				+ (user==null ? "" : "autofocus='autofocus'")
				+ "/>"
				
				+ "<input type='submit'/>"
				
				+ "</form></p>";
		
		String czatTabela = "<p><table>";
		for(Message msg : czatList){
			czatTabela += msg.getHTML(); // wiersz tabeli
		}
		czatTabela += "</table></p>";
		
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("      <meta charset='utf-8'>");
		out.println("</HEAD>");
		out.println("<p>Czat</p>");
		out.println(form);
		out.println(czatTabela);
		out.println("</HTML>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("user");
		String userMessage = request.getParameter("msg");
		
		if(userName!=null && userName.length()>0
				&& userMessage!=null && userMessage.length()>0){
			
			czatList.addFirst(new Message(userName, userMessage));
			System.out.println(userName+":"+userMessage);
			
			request.getSession().setAttribute("userWSesji", userName);
			
			response.sendRedirect("/WebApp/czat");
			
		}
		
		doGet(request, response);
	}

}
