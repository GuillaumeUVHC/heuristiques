package methodes;

import java.util.ArrayList;
import java.util.List;

import tools.Incompatibilite;
import tools.Objet;
import tools.Solution;

public class VNS {
	
	public static Solution genereVoisinSmart(Solution s, int i, List<Incompatibilite> listIncompatibilite, int poidsMaxSac) {
		Solution newSol = new Solution(s.listObjets);
		
		newSol = s.genereVoisin(i);
		
		/*newSol.evaluer(poidsMaxSac, listIncompatibilite);
		if(newSol.poids < poidsMaxSac) {
			newSol.listObjets.get(i).setDansSac(false);
		}*/
		
		//System.out.print(" Ajout " + i);
		
		/*for (Incompatibilite imcomp : listIncompatibilite) {
			if ( (newSol.listObjets.get(imcomp.getObjet1()).estDansSac()) && (newSol.listObjets.get(imcomp.getObjet2()).estDansSac()) ) {
				newSol.listObjets.get(imcomp.getObjet1()).setDansSac(false);
			}
		}*/
		
		
		
		newSol.lastChangedObject = i;
		return newSol;
	}

	public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite, int k) { //voisinage int pour calculer plusieurs voisinags (Nk je change k valeurs)

		boolean fin = false;
		Solution best = new Solution(x0.listObjets);
		Solution x = x0;
		//best.randomize();
		List<Solution> voisinage= new ArrayList<Solution>();
		best.evaluer(poidsMaxSac, listIncompatibilite);
		int n = 0;
		boolean realisable = false;
		//best.voisinage.clear();
		
		while (!fin) {
			lefor:
			for (int i=1;i<=k;i++) {
				//System.out.println("VNS ITE " + i);
				
				x = best;
				
				for(int j=0; j<i; j++) {
					x = genereVoisinSmart(x,(int) (Math.random() * Math.random() * best.listObjets.size()), listIncompatibilite, poidsMaxSac);
				}
				x.evaluer(poidsMaxSac, listIncompatibilite);
				System.out.println("solution au pif dans v"+i+"\n" + x);
				
				x = Descente.resoudre(x, poidsMaxSac, listIncompatibilite);

				
				x.evaluer(poidsMaxSac, listIncompatibilite);
				
				System.out.println("Solution trouvée par descente : \n" + x);
				System.out.println("BEST : " + best.valeur);
				if (x.valeur > best.valeur) {
					best = x;
					System.out.println("ITE" +i+ " meilleure Solution trouvée par descente : \n" + x);
					break lefor;
					
				}	
				
				
			

			}
			
			//System.out.println("Exploration "+k+" voisinages terminée");
		
		}
		
		
		
		

		//System.out.println(best);

		return best;
	}

}

