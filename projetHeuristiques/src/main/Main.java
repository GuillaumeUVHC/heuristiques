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

		
		//Recupération du fichier en argument
		File f = new File(args[0]);
		
		//Initialisation des données par lecture du fichier
		int poidsMaxSac = 0;
		List<Objet> listObjet = new ArrayList<Objet>();
		List<Incompatibilite> listIncompatibilite = new ArrayList<Incompatibilite>();
		
		try {
			poidsMaxSac = FileReader.litFichierEtInitObjets(f, listObjet, listIncompatibilite);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Initialisation d'une solution de départ
		Solution s1 = new Solution(listObjet);
		
		Solution best = s1;
	    //best = VNS.resoudre(s1, poidsMaxSac, listIncompatibilite, 20, 200);
	    //best = Descente.resoudre(s1, poidsMaxSac, listIncompatibilite);
	    best = MonteCarlo.resoudre(s1, poidsMaxSac, listIncompatibilite, 1000000);
		
		System.out.println("Solution : " + best);
	}

}
