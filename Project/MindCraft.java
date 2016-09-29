import java.awt.Color;
import java.io.FileInputStream;
import java.util.Properties;


public class MindCraft {
	public int taille_code, tour, couleurs, joueur,temps;
	public int[] cp;
	public boolean doublon=false, extra=false;
	public Color[] couleur = {Color.red, Color.green, Color.blue, Color.cyan, Color.magenta, Color.yellow, Color.orange, Color.pink,Color.LIGHT_GRAY, Color.gray, Color.DARK_GRAY, Color.black};
	public Color[][] placerCouleur, indice;
	public Properties prop=new Properties();
	public MindZ m;
	public MindCPU mcpu;
	
	public MindCraft(MindZ mz){
		m=mz;
		taille_code=mz.taille_code;
		tour=mz.tour;
		couleurs=mz.couleurs;
		joueur=mz.joueur;
		doublon=mz.doublon;
		placerCouleur=mz.placerCouleur;
		indice=mz.indice;
		cp=mz.cp;
	}
	
	public MindCraft(MindCPU mcp){
		mcpu=mcp;
		taille_code=mcp.taille_code;
		tour=mcp.tour;
		couleurs=mcp.couleurs;
		joueur=mcp.joueur;
		doublon=mcp.doublon;
		placerCouleur=mcp.placerCouleur;
		indice=mcp.indice;
		cp=mcp.cp;
	}

	public int[] codeSecret(){
		int[] cs = new int[taille_code];
		int[] choixCode = new int[couleurs];
		
		for(int i=0; i<taille_code; i++){
			cs[i] = (int)(Math.random() * couleurs);
			
			if(!doublon){
				while(choixCode[cs[i]] == 1){
					cs[i] = (int)(Math.random() * couleurs);
				}
			}
			
			choixCode[cs[i]] = 1;
		}
		
		return cs;
	}
	
	public void copieTableau(int[] tableau, int[] copie){
		for(int i=0; i<tableau.length; i++){
			copie[i] = tableau[i];
		}
	}
	
	public int[] comparaison(int[] codeSecret, int[] codePropose){
		int[] codeSecret1 = new int[taille_code];
		copieTableau(codeSecret, codeSecret1);
		int[] codePropose1 = new int[taille_code];
		copieTableau(codePropose, codePropose1);
		int[] placement = new int[taille_code];
		int k = 0;
		
		for(int i=0; i<taille_code; i++){
			if(codeSecret1[i] == codePropose1[i]){
				codeSecret1[i] = codePropose1[i] = -1;
				placement[k] = 2;
				k++;
			}
		}
		
		for(int i=0; i<taille_code; i++){
			for(int j=0; j<taille_code; j++){
				if(codeSecret1[j] == codePropose1[i] && codeSecret1[j] != -1 && codePropose1[i] != -1){
					codeSecret1[j] = codePropose1[i] = -1;
					placement[k] = 1;
					k++;
				}
			}
		}
		
		return placement;
	}
	
	public String indication(int[] placement){
		String indice = "";
		
		for(int i=0; i<taille_code; i++){
			if(placement[i] == 2){
				indice += "+";
			}
		}
		for(int i=0; i<taille_code; i++){
			if(placement[i] == 1){
				indice += "*";
			}
		}
		while(indice.length() < taille_code){
			indice += "-";
		}
		
		return indice;
	}
	
	public void indicationCouleur(Color[][] indice, int tourSelec, String s){
		int k = 0;
		for (int j=0; j < taille_code; j++) {
			if(s.charAt(j) == '+'){
				indice[tourSelec][k] = Color.red;
				k++;
			}
		}
		for (int j=0; j < taille_code; j++) {
			if(s.charAt(j) == '*'){
				indice[tourSelec][k] = Color.white;
				k++;
			}
		}
	}
	
	public void setPar(){
		try{
			this.prop.load(new FileInputStream("options.properties"));
			this.taille_code = Integer.parseInt(this.prop.getProperty("taille"));
			tour = Integer.parseInt(this.prop.getProperty("tours"));
			couleurs = Integer.parseInt(this.prop.getProperty("nbrechoix"));
			doublon = Boolean.parseBoolean(this.prop.getProperty("doublons"));
			if(joueur==1 || joueur==2){
				temps = Integer.parseInt(this.prop.getProperty("temps"));
			}
			if(Boolean.parseBoolean(this.prop.getProperty("Extravaganza"))){
				extra=true;
				tour=100000;
			}
			int i = 0;
			while(this.prop.getProperty("Couleur"+i)!=null){
				couleur[i]=new Color(Integer.parseInt(this.prop.getProperty("Couleur"+i)));
				i++;
			}
			
		}catch(Exception e){	
			tour=10;
			taille_code=4;
			couleurs=6;
			doublon=false;
			int i = 0;
			while(this.prop.getProperty("Couleur"+i)!=null){
				couleur[i]=new Color(Integer.parseInt(this.prop.getProperty("Couleur"+i)));
				i++;
			}
		}
		placerCouleur=new Color[tour][taille_code];
		indice=new Color[tour][taille_code];
		cp=new int[taille_code];
		if(m!=null){
			m.tour=tour;
			m.taille_code=taille_code;
			m.couleurs=couleurs;
			m.doublon=doublon;
			m.temps=temps;
			m.placerCouleur=placerCouleur;
			m.indice=indice;
			m.couleur=couleur;
			m.cp=cp;
		}else{
			mcpu.tour=tour;
			mcpu.taille_code=taille_code;
			mcpu.couleurs=couleurs;
			mcpu.doublon=doublon;
			mcpu.placerCouleur=placerCouleur;
			mcpu.indice=indice;
			mcpu.couleur=couleur;
			mcpu.cp=cp;
		}
	}
	
	public boolean verification(String indice){
		boolean trouve = true;
		
		for(int i=0; i<taille_code; i++){
			if(indice.charAt(i) != '+'){
				trouve = false;
				break;
			}
		}
		
		return trouve;
	}
	
}
