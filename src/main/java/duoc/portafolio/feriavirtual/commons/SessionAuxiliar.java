package duoc.portafolio.feriavirtual.commons;

import javax.servlet.http.HttpSession;

public class SessionAuxiliar {

	public String IsSessioned(String from, HttpSession session) {
		String response = "/login";
		// si no trae ni un carajo ... te devuelve a login gil pollo!
		boolean r = !((String)session.getAttribute("nombre")).trim().isEmpty() ? false : true;
		from = r ? response : from;
		return from;
	}
}