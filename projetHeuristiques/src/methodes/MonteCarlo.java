package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class MonteCarlo {
	
public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite, int ite) {
	
		long start = System.currentTimeMillis();
	
		Solution x = x0;
		Solution best = x;
		Solution x2 = null;
		boolean fin = false;
		int i = 0;
		
		//J'évalue la solution en entrée
		x.evaluer(poidsMaxSac, listIncompatibilite);
		while (!fin) {
			
			//J'obtient son voisinage direct (retirer ou ajouter un objet)
			//x.getVoisinage();
			//Je prends un solution au hasard dans le voisinage
			//x2 = x.voisinage.get((int) (Math.random() * x.voisinage.size()));
			x2 = x.genereVoisin((int) (Math.random() * x.listObjets.size()));
			//je l'evalue
			x2.evaluer(poidsMaxSac, listIncompatibilite);
			//si la solution est meilleur je l'enregistre
			if(x2.valeur > best.valeur) {
				best = x2;
			}
			
			//sinon je continue juste
			x = x2;
			i++;
			System.out.println("Itération " + i + " valeur sol : " + best.valeur);
			if (i > ite) {
				fin = true;
			}
		}
		

		long end = System.currentTimeMillis();
		System.out.println("trouve en " + (end - start));
		return best;
	}

}
