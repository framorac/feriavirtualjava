package duoc.portafolio.feriavirtual.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionAuxiliar {

	public String IsSessioned(String from, HttpSession session) {
		String response = "/login/login";
		// si no trae ni un carajo ... te devuelve a login gil pollo!
		boolean sessionExist = session.isNew();
		if(!sessionExist) {
			return response;
		}
		boolean isVacio = ((String)session.getAttribute("nombre")).trim().isEmpty();
		from = isVacio ? response : from;
		return from;
	}
	
	public boolean IsSessioned(HttpSession session, HttpServletRequest request){
		boolean r = true;
		
		session = request.getSession(false);
		if(session == null) {
			r = false;
		}
		return r;
	}
}