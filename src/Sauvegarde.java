import java.awt.Color;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Sauvegarde implements Serializable {
	private static final long serialVersionUID = 1L;
	public int tour, taille_code, couleurs, couleurSelec, tourSelec, score,joueur, temps;
	public Color[][] placerCouleur, indice;
	public boolean doublon;
	public int[] chrono;
	public int[] cs;
	
	public Sauvegarde(MindZ m){
		tour=m.tour;
		taille_code=m.taille_code;
		tourSelec=m.mp.tourSelec;
		couleurs=m.couleurs;
		joueur=m.joueur;
		chrono=new int[m.c.tab.length];
		for(int i=0; i<chrono.length; i++){
			chrono[i]=m.c.tab[i];
		}
		placerCouleur=new Color[tour][taille_code];
		indice=new Color[tour][taille_code];
		for(int i=0; i<tour; i++){
			for(int j=0;j<taille_code;j++){
				placerCouleur[i][j]=m.placerCouleur[i][j];
				indice[i][j]=m.indice[i][j];
			}
		}
		doublon=m.doublon;
		temps=m.temps;
		score=m.sc.score;
		cs=new int[m.cs.length];
		for(int i=0; i<cs.length;i++){
			cs[i]=m.cs[i];
		}
	}
	
	public Sauvegarde(MindCPU m){
		tour=m.tour;
		taille_code=m.taille_code;
		tourSelec=m.mp.tourSelec;
		couleurs=m.couleurs;
		joueur=m.joueur;
		placerCouleur=new Color[tour][taille_code];
		indice=new Color[tour][taille_code];
		for(int i=0; i<tour; i++){
			for(int j=0;j<taille_code;j++){
				placerCouleur[i][j]=m.placerCouleur[i][j];
				indice[i][j]=m.indice[i][j];
			}
		}
		doublon=m.doublon;
	}
	
	public Sauvegarde(){
		
	}
	
	public Sauvegarde Deserializable(String fichier){
		Sauvegarde s;
		try{
			FileInputStream fin = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fin);
			s=(Sauvegarde) ois.readObject();
			ois.close();
			return s;
		}catch(Exception e){
			e.printStackTrace();
			return null;	
		}
	}
}
