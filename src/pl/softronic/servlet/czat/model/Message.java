package pl.softronic.servlet.czat.model;

import java.util.Date;

import pl.softronic.servlet.czat.util.Constants;

public class Message {

	private Date czas;
	private String user;
	private String message;
	
	public Message(){
		czas = new Date();
	}

	public Message(String user, String message) {
		this();
		this.user = user;
		this.message = message;
	}

	public Date getCzas() {
		return czas;
	}
	public void setCzas(Date czas) {
		this.czas = czas;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getHTML(){
		String template = "<tr>"
				+ "<td>{{czas}}</td>"
				+ "<td>{{user}}</td>"
				+ "<td>{{message}}</td>"
				+ "</tr>";
		template = template.replaceAll("\\{\\{czas\\}\\}", Constants.czatDF.format(czas));
		template = template.replaceAll("\\{\\{user\\}\\}", user);
		template = template.replaceAll("\\{\\{message\\}\\}", message);

//		String[] tab =  template.split("(\\{\\{|\\}\\})");
//		List<String> zmienne = new ArrayList<>();
//		for(int i=1; i<tab.length; i=i+2){
//			zmienne.add(tab[i]);
//		}
//		for(String zmienna : zmienne){
//			
//			try {
//				String zmiennaZDuzej = zmienna.substring(0, 1).toUpperCase() + zmienna.substring(1);
//				Method m = getClass().getMethod("get"+zmiennaZDuzej, null);
//				Object o = m.invoke(this, new Object[]{});
//				if(o instanceof Date){
//					template = template.replaceAll("\\{\\{"+zmienna+"\\}\\}", Constants.czatDF.format(o));
//				} else {
//					template = template.replaceAll("\\{\\{"+zmienna+"\\}\\}", o.toString());
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			} 
//			
//		}
		
		
		return template;
	}
	
}
