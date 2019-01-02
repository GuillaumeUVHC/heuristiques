package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class Descente {
	//Implementation de la methode de descente
	
	public static Solution trouverMeilleurSol(List<Solution> liste) {
		/*permet de trouver la meilleure solution dans une liste de solution*/
		Solution best = liste.get(0);
		for (Solution s : liste) {
			if (s.valeur > best.valeur) {
				best = s;
			}
		}
		
		return best;
	}
	
	public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite) {
		
		Solution x = x0;
		boolean fin = false;
		
		
		while (!fin) {
			//evaluer la solution de d�part
			x.evaluer(poidsMaxSac, listIncompatibilite);
			
			//obtenir tous les voisins directe de la solution (ie ne modifier ajouter ou retirer un seul objet)
			x.getVoisinage();
			
			//evaluer l'ensemble de voisins
			for(Solution s : x.voisinage) {
				s.evaluer(poidsMaxSac, listIncompatibilite);
			}
			
			//les s�parer en fonction du fait qu'ils am�liorent ou non la solution
			x.separeVoisinage();
			
			//Si le voisinage am�liorant est vide on arr�te on a l'optimum local
			if (!x.voisinageAmeliorant.isEmpty()) {
				//si il n'est pas vide pendre la meilleure solution du voisinage am�liorant (am�loiration de la m�thode de descente)
				x = trouverMeilleurSol(x.voisinageAmeliorant);
				
			}else {
				//On sort de la boucle
				fin = true;
			}
		}
		//On renvoie la solution trouv�e
		return x;
	}

}
