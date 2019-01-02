package methodes;

import java.util.List;

import tools.Incompatibilite;
import tools.Solution;

public class VNS {
	/*methode permettant de changer la fa�on dont "mute" les solutions,*/
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
				//Un voisinage correspond � l'insertion ou au retrait d'un nombre d�finit de valeur 
				//Vn = je retire ou ajoute n objet � mon sac � dos
				x = best;
				
				//Je choisis au hasard une solution dans le voisinage i 
				for(int j=0; j<i; j++) {
					//je gen�re le voisin du voisinage i
					x = genereVoisinSmart(x,(int) (Math.random() * Math.random() * best.listObjets.size()), listIncompatibilite, poidsMaxSac);
				}
				//J'avalue ma solution
				x.evaluer(poidsMaxSac, listIncompatibilite);
				
				//J'effectue la m�thode de descente � partir de la solution prise au hasard
				x = Descente.resoudre(x, poidsMaxSac, listIncompatibilite);
				//j'�value ma solution trouv�e
				x.evaluer(poidsMaxSac, listIncompatibilite);
				
				//J'affiche le r�sultat
				System.out.println("Solution trouv�e par descente : \n" + x);
				System.out.println("BEST : " + best.valeur);
				
				//Si la solution trouv� est meilleur que la meilleure pr�c�dente, je l'enregistre
				if (x.valeur > best.valeur) {
					best = x;
					System.out.println("ITE" +ite+ " meilleure Solution trouv�e par descente : \n" + x);
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

