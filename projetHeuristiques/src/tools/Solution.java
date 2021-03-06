package tools;

import java.util.ArrayList;
import java.util.List;


public class Solution {
	
	//Liste des objets de la solution
	public List<Objet> listObjets;
	//poids de la solution
	public float poids;
	//Valeur de la solution
	public float valeur;
	//Voisinages de la solution
	public List<Solution> voisinage;
	public List<Solution> voisinageAmeliorant;
	public List<Solution> voisinageNonAmeliorant;
	public int lastChangedObject;
	
	
	//Constructeur
	public Solution (List<Objet> liste) {
		listObjets = new ArrayList<Objet>();
		voisinage = new ArrayList<Solution>();
		voisinageAmeliorant = new ArrayList<Solution>();
		voisinageNonAmeliorant = new ArrayList<Solution>();
		//Construction d'une solution � partir d'une liste d'objet qui sera copi�e
		for(Objet o : liste) {
			this.listObjets.add(new Objet(o.getId(), o.getPoids(), o.getValeur(), o.estDansSac()));
		}
	}
	
	public void randomize () {
		//Calcul d'une solution au hasard
		for (int i=0; i<this.listObjets.size(); i++) {
			if (Math.random() < 0.05){ //changer en fonction de la densit� des imcompat ?
				this.listObjets.get(i).setDansSac(true);
			}else {
				this.listObjets.get(i).setDansSac(false);
			}
		}		
	}
	
	//Renvoie le poids de la solution
	float calculPoids() {
		float p = 0;
		for (Objet o : listObjets) {
			if (o.estDansSac())
				p += o.getPoids();
		}
		return p;
	}
	
	//Renvoie la valeur de la solution
	float calculValeur() {
		float v = 0;
		for (Objet o : listObjets) {
			if (o.estDansSac())
				v += o.getValeur();
		}
		return v;
	}
	
	//Evalue la solution (calcul du poids, de la valeur qui sera � 0 si la solution pr�sente des imcompatibilit�s ou si le poids > poidsMaxSac)
	public Float evaluer(float poidsSac, List<Incompatibilite> listIncompatibilite) {
		this.poids = calculPoids();
		this.valeur = calculValeur();
		
		//Si sac trop lourd valeur = -poids en trop
		if (this.poids > poidsSac) {
			this.valeur = poidsSac - this.poids;
		}
		
		//Si pas r�alisable -Inf
		if(!estRealisable(poidsSac, listIncompatibilite)) {
			this.valeur = -999999;
		}
		
		return valeur;
	}
	
	//V�rifie si la solution est r�alisable
	public boolean estRealisable(float poidsSac, List<Incompatibilite> listIncompatibilite) {
		
		//renvoie vraie la solution n'a pas d'incompatibilite
		//Contrainte incomptatibilit�
		for (Incompatibilite i : listIncompatibilite) {
			if ( (this.listObjets.get(i.getObjet1()).estDansSac()) && (this.listObjets.get(i.getObjet2()).estDansSac()) ) {
				return false;
			}
		}
		return true;	
	}
	
	public Solution genereVoisin(int i) {
		//genere le voisin direct en changeant l'el�ment i
		Solution newSol = new Solution(this.listObjets);
		
		if (newSol.listObjets.get(i).estDansSac()) {
			newSol.listObjets.get(i).setDansSac(false);
		}else {
			newSol.listObjets.get(i).setDansSac(true);
		}
		
		newSol.lastChangedObject = i;
		return newSol;
	}
	
	
	public void getVoisinage(){
		//Obtenir le voisinage direct
		//List<Solution> voisinage = new ArrayList<Solution>();
		for(int i = 0; i<this.listObjets.size(); i++) {
			this.voisinage.add(this.genereVoisin(i));	
		}	
	}
	
	public void getNextVoisinage() {
		//this.voisinage.clear();
		ArrayList<Solution> newVoisinage = new ArrayList<Solution>();
		for(Solution s : this.voisinage) {
			for(int i = 0; i<this.listObjets.size(); i++) {
				if(i!= s.lastChangedObject) {
					newVoisinage.add(s.genereVoisin(i));
				}
			}		
		}
		
		this.voisinage = newVoisinage;
		
	}
	
	public Solution getNextVoisin() {
		for(int i=0;i<this.listObjets.size(); i++) {
			if(i!= this.lastChangedObject) {
				return this.genereVoisin(i);
			}
		}
		return null;
	}
	
	/*public Solution genereVoisin(int[] toChange) {
		Solution newSol = new Solution(this.listObjets);
		for(int i : toChange) {
			if (newSol.listObjets.get(i).estDansSac()) {
				newSol.listObjets.get(i).setDansSac(false);
			}else {
				newSol.listObjets.get(i).setDansSac(true);
			}
		}
		
		return newSol;
	}
	
	public void getVoisinage2() {
		List<Integer> toChange = new ArrayList<Integer>();
		for(int i=0; i<this.listObjets.size(); i++) {
			for(int j=0; j<this.listObjets.size(); j++) {
				if(i!=j) {
					toChange.add(i);
					toChange.add(j);
					
				}
			}
		}
	}*/
	

	
	public void separeVoisinage(){
		//Repartir les voisins entre les ensembles am�liorant et non am�liorant
		for(Solution s : this.voisinage) {
			if (s.valeur > this.valeur)
				this.voisinageAmeliorant.add(s);
			else 
				this.voisinageNonAmeliorant.add(s);
		}
	}
	
	public String toString() {
		String s = "";
		
		s+="Objets dans le sac : ";
		
		for (int i = 0; i<this.listObjets.size(); i++) {
			if (this.listObjets.get(i).estDansSac()){
				s+= this.listObjets.get(i).getId() + " ";
			}
		}
		
		s+= "\n";
		s+="Poids : " + this.poids + " " + "Valeur : " + this.valeur; 
		s+= "\n";
		
		return s;
		
	}
	
	
	
}
