package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class Descente {
	//Implementation de la methode de descente
	
	public static Solution trouverMeilleurSol(List<Solution> liste) {
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
			x.evaluer(poidsMaxSac, listIncompatibilite);
			x.getVoisinage();
			
			for(Solution s : x.voisinage) {
				s.evaluer(poidsMaxSac, listIncompatibilite);
			}
			
			x.separeVoisinage();
			//System.out.println("Taille voisinage améliorant : " + x.voisinage.size());
			
			if (!x.voisinageAmeliorant.isEmpty()) {
				
				//x = x.voisinageAmeliorant.get(0);
				x = trouverMeilleurSol(x.voisinageAmeliorant);
				
			}else {
				fin = true;
			}
		}
		
		return x;
	}

}
