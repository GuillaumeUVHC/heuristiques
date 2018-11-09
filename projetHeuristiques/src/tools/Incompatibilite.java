package tools;

public class Incompatibilite {

	private int objet1;
	private int objet2;
	
	public Incompatibilite() {
		super();
	}

	public int getObjet1() {
		return objet1;
	}

	public void setObjet1(int objet1) {
		this.objet1 = objet1;
	}

	public int getObjet2() {
		return objet2;
	}

	public void setObjet2(int objet2) {
		this.objet2 = objet2;
	}
	
	public String toString() {
		return objet1 + " | " + objet2;
	}
	
}
