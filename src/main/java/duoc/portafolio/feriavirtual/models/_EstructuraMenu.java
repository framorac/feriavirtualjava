package duoc.portafolio.feriavirtual.models;

import java.util.ArrayList;
import java.util.List;

public class _EstructuraMenu {
	
	
	private List<MenuItem> menus;
	private String title;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MenuItem> getMenus() {
		return menus;
	}

	public void setMenus(List<MenuItem> menus) {
		this.menus = menus;
	}

	public _EstructuraMenu(String title) {
		menus = new ArrayList<MenuItem>();
		setTitle(title);
	}
	
	public static class MenuItem{
		private String Link;
		private String Texto;

		public String getLink() {
			return Link;
		}
		public void setLink(String link) {
			Link = link;
		}
		public String getTexto() {
			return Texto;
		}
		public void setTexto(String texto) {
			Texto = texto;
		}
		public MenuItem(String link, String texto) {
			super();
			Link = link;
			Texto = texto;
		}
	}
}
