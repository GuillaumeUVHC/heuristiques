package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	
	public static int litFichierEtInitObjets(File f, List<Objet> listObjet, List<Incompatibilite> listIncompatibilite) throws IOException {
		InputStream fichier = new FileInputStream(f);
		InputStreamReader lecture = new InputStreamReader(fichier);
		BufferedReader buff=new BufferedReader(lecture);
		String ligne;
		String separateur = " ";
		List<Integer> datasPoids = new ArrayList<>();
		List<Integer> datasValeurs = new ArrayList<>();
		int compteur_incomp = 0;
		int compteur = 0; // compteur nous permettant de savoir dans quel bloc nous nous trouvons
		int poidsSac = 0;
		while ((ligne=buff.readLine())!=null){
			String[] valeurs = ligne.split(separateur);
			if(ligne.equals("")) {
				compteur++; //on passe dans le bloc suivant
				continue;
			}
			//premiere ligne du fichier
			if(compteur == 0) {
				//initialisation des objets
				for(int i = 0; i < Integer.parseInt(valeurs[1]); i++) {
					Objet objet = new Objet();
					listObjet.add(objet);
				}

				//initialisation des incompatibilite
				for(int i = 0; i < Integer.parseInt(valeurs[2]); i++) {
					Incompatibilite incompatibilite = new Incompatibilite();
					listIncompatibilite.add(incompatibilite);
				}

				//sac.setPoidsMax(Integer.parseInt(valeurs[3]));
				poidsSac = Integer.parseInt(valeurs[3]);
				
			}

			if(compteur == 1) {// on est dans le 1er bloc => poids des objets
				for(int i= 0; i < valeurs.length; i++) {
					datasPoids.add(Integer.parseInt(valeurs[i]));
				}
			}

			if(compteur == 2) {// on est dans le 2e bloc => valeurs des objets
				for(int i= 0; i < valeurs.length; i++) {
					datasValeurs.add(Integer.parseInt(valeurs[i]));
				}
			}

			if(compteur == 3) {// on est dans le 3e bloc => incompatibilités
				listIncompatibilite.get(compteur_incomp).setObjet1(Integer.parseInt(valeurs[1])-1);
				listIncompatibilite.get(compteur_incomp).setObjet2(Integer.parseInt(valeurs[2])-1);
				compteur_incomp++;
			}
		}
		//on set le poids des objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setPoids(datasPoids.get(i));
		}
		//on set la valeur des objets
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setValeur(datasValeurs.get(i));
		}
		//on set les ids pour affichage
		for(int i = 0; i < listObjet.size(); i++) {
			listObjet.get(i).setId(i+1);
		}
		buff.close(); 
		return poidsSac;
	}

}
