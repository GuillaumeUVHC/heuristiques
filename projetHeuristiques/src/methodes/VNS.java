package methodes;

import java.util.ArrayList;
import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class VNS {
	
	public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite, int k) { //voisinage int pour calculer plusieurs voisinags (Nk je change k valeurs)
		
		boolean fin = false;
		Solution best = x0;
		Solution x = x0;
        best.randomize();
	    List<Solution> voisinage= new ArrayList<Solution>();
	    best.evaluer(poidsMaxSac, listIncompatibilite);
		
		while (!fin) {
			for (int i=0;i<=k;i++) {
				if(i == 0) {
					best.getVoisinage();
					voisinage = best.voisinage;
					x = voisinage.get((int)(Math.random() * voisinage.size()));
					x = Descente.resoudre(x, poidsMaxSac, listIncompatibilite);
					
					x.evaluer(poidsMaxSac, listIncompatibilite);
					
					if (x.valeur > best.valeur) {
						best = x;
					}			
				}
			}
		}
		
		System.out.println(best);
		
		return null;
		
	}
	
}

