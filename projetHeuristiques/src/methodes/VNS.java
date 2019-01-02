package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class VNS {
	/*methode permettant de changer la façon dont "mute" les solutions,*/
	public static Solution genereVoisinSmart(Solution s, int i, List<Incompatibilite> listIncompatibilite, int poidsMaxSac) {
		Solution newSol = new Solution(s.listObjets);
		newSol = s.genereVoisin(i);
		newSol.lastChangedObject = i;
		return newSol;
	}

	public static Solution resoudre(Solution x0, int poidsMaxSac, List<Incompatibilite> listIncompatibilite, int k, int iterationMax) { //voisinage int pour calculer plusieurs voisinags (Nk je change k valeurs)

		//Init
		boolean fin = false;
		Solution best = new Solution(x0.listObjets);
		Solution x = x0;
		best.evaluer(poidsMaxSac, listIncompatibilite);
		int ite = 0;
		
		while (!fin) {
			lefor:
			for (int i=1;i<=k;i++) {
				//Un voisinage correspond à l'insertion ou au retrait d'un nombre définit de valeur 
				//Vn = je retire ou ajoute n objet à mon sac à dos
				x = best;
				
				//Je choisis au hasard une solution dans le voisinage i 
				for(int j=0; j<i; j++) {
					//je genère le voisin du voisinage i
					x = genereVoisinSmart(x,(int) (Math.random() * Math.random() * best.listObjets.size()), listIncompatibilite, poidsMaxSac);
				}
				//J'avalue ma solution
				x.evaluer(poidsMaxSac, listIncompatibilite);
				
				//J'effectue la méthode de descente à partir de la solution prise au hasard
				x = Descente.resoudre(x, poidsMaxSac, listIncompatibilite);
				//j'évalue ma solution trouvée
				x.evaluer(poidsMaxSac, listIncompatibilite);
				
				//J'affiche le résultat
				System.out.println("Solution trouvée par descente : \n" + x);
				System.out.println("BEST : " + best.valeur);
				
				//Si la solution trouvé est meilleur que la meilleure précédente, je l'enregistre
				if (x.valeur > best.valeur) {
					best = x;
					System.out.println("ITE" +ite+ " meilleure Solution trouvée par descente : \n" + x);
					break lefor;
					
				}	
			}
		
		
			if (ite > iterationMax)
				fin = true;
			else
			 ite ++; 
		}
		return best;
	}

}

