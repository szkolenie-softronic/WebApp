package pl.softronic.servlet.wiki;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WikiMain
 */
@WebServlet("/wiki")
public class WikiMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** klucz to nazwa strony<br>
	 * wartoœæ to treœæ strony */
	private static final Map<String, String> strony = new TreeMap<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String nazwaStrony = request.getParameter("strona");
		String czyEdytowac = request.getParameter("edit");
		
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("      <meta charset='utf-8'>");
		out.println("</HEAD>");
		out.println("<BODY>");
		
		if(nazwaStrony == null){
			listujStrony(out);
		} else { // nazwaStrony != null
			if(czyEdytowac != null && "true".equals(czyEdytowac)){
				wyœwietlFormularzEdycji(nazwaStrony, out);
			} else { // mam strone i nie edytowaæ jej
				if(strony.containsKey(nazwaStrony)){
					wyswietlStrone(nazwaStrony, out);
				} else {
					response.sendRedirect("/WebApp/wiki?strona="
										+nazwaStrony+"&edit=true");
				}
			}
		}
		
		out.println("</BODY>");
		out.println("</HTML>");
	}

	private void wyœwietlFormularzEdycji(String nazwaStrony, PrintWriter out) {
		String trescStrony = strony.get(nazwaStrony);
		out.println("<p><a href='"
				+ "/WebApp/wiki?strona="
				+nazwaStrony+"'>Anuluj</a></p>");
		out.println("<p>");
		out.println("<form action='/WebApp/wiki' method='POST'>");
		out.println("<textarea name='tresc'>"
				+ (trescStrony==null ? "" : trescStrony)
				+ "</textarea>");
		out.println("<input type='hidden' name='strona' value='"
				+ nazwaStrony
				+ "'/>");
		out.println("<input type='submit'/>");
		out.println("</form>");
		out.println("</p>");
	}

	private void wyswietlStrone(String nazwaStrony, PrintWriter out) {
		String trescStrony = strony.get(nazwaStrony);
		out.println("<p><a href='"
				+ "/WebApp/wiki?strona="
				+nazwaStrony+"&edit=true'>Edytuj</a></p>");
		out.println("<p>");
		out.println(trescStrony.replaceAll("\n", "<br/>"));
		out.println("</p>");
	}

	private void listujStrony(PrintWriter out) {
		out.println("<table>");
		for(String strona : strony.keySet()){
			out.println("<tr><td>");
			out.println("<a href='/WebApp/wiki?strona="+strona+"'>"+strona+"</a>");
			out.println("</td></tr>");
		}
		out.println("</table>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String nazwaStrony = request.getParameter("strona");
		String trescStrony = request.getParameter("tresc");
		
		strony.put(nazwaStrony, trescStrony);
		
		response.sendRedirect("/WebApp/wiki?strona="+nazwaStrony);
	}

}
