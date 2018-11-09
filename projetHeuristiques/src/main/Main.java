package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import methodes.Descente;
import methodes.MonteCarlo;
import methodes.VNS;
import tools.FileReader;
import tools.Incompatibilite;
import tools.Objet;
import tools.Solution;
//import tools.Sac;

public class Main {
	

	public static void main(String[] args) {

		
		//File f = new File("../projetHeuristiques/conf/1I1");
		File f = new File(args[0]);
		
		int poidsMaxSac = 0;
		List<Objet> listObjet = new ArrayList<Objet>();
		List<Incompatibilite> listIncompatibilite = new ArrayList<Incompatibilite>();
		
		try {
			poidsMaxSac = FileReader.litFichierEtInitObjets(f, listObjet, listIncompatibilite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Solution s1 = new Solution(listObjet);
		
		//Solution best = Descente.resoudre(s1, poidsMaxSac, listIncompatibilite);
		//Solution best = MonteCarlo.resoudre(s1, poidsMaxSac, listIncompatibilite, 1000);
		Solution best = VNS.resoudre(s1, poidsMaxSac, listIncompatibilite, 0);
		
		//System.out.println(best);
		
		
		/*s1.evaluer(poidsMaxSac, listIncompatibilite);
		//System.out.println(s1);
		
		s1.getVoisinage();
		
		for (Solution s : s1.voisinage) {
			s.evaluer(poidsMaxSac, listIncompatibilite);
		}
		
		s1.separeVoisinage();	
		
		System.out.println(s1.voisinageAmeliorant);
		System.out.println(s1.voisinageNonAmeliorant);
		*/
		//System.out.print(listObjet);
		//System.out.print("\n");
		//System.out.print(listIncompatibilite);
		
		

	}

}
