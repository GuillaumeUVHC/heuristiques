package tools;

public class Objet {
	private int id;
	private int poids;
	private int valeur;
	private boolean dansSac;
	
	public Objet() {
		super();
	}
	
	public Objet(int _id, int _poids, int _valeur, boolean _dansac) {
		this.dansSac = _dansac;
		this.id = _id;
		this.poids = _poids;
		this.valeur = _valeur;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public Integer getValeur() {
		return valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}
	
	public void setDansSac(boolean valeur) {
		this.dansSac = valeur;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean estDansSac() {
		if (dansSac) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = this.getId() + " " + this.getPoids() + " " + this.getValeur() + " " + this.estDansSac();
		return s;	
	}
	
}
