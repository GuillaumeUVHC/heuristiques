package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class MonteCarlo {
	
public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite, int ite) {
		
		Solution x = x0;
		Solution best = x;
		Solution x2 = null;
		boolean fin = false;
		int i = 0;
		
		x.evaluer(poidsMaxSac, listIncompatibilite);
		while (!fin) {
			
			x.getVoisinage();
			
			for(Solution s : x.voisinage) {
				s.evaluer(poidsMaxSac, listIncompatibilite);
			}
			
			x2 = x.voisinage.get((int) (Math.random() * x.voisinage.size()));
			
			if(x2.valeur > best.valeur) {
				best = x2;
			}
			
			x = x2;
			i++;
			System.out.println("Itération " + i + " valeur sol : " + best.valeur);
			if (i > ite) {
				fin = true;
			}
		}
		

		
		return best;
	}

}
