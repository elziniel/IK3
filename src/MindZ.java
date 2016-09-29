import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MindZ {
	public int tour, taille_code, couleurs, l, longueur, largeur, couleurSelec, tourSelec, joueur=1, temps=0;
	public Color[] couleur = {Color.red, Color.green, Color.blue, Color.cyan, Color.magenta, Color.yellow, Color.orange, Color.pink,Color.LIGHT_GRAY, Color.gray, Color.DARK_GRAY, Color.black};
	public Color[][] placerCouleur, indice;
	public Chrono c;
	public Score sc;
	public JPanel panel, chrono, score;
	public boolean doublon = false, start=false, fini=false, gagne=false, perdu=false, choixcouleurs, reprise=false;
	public int[] cp, cs;
	public String s;
	public MindCraft mc;
	public MindPanel mp;
	public Properties prop = new Properties();
	
	public void indicationCouleur(String s){
		mc.indicationCouleur(indice, mp.tourSelec, s);
	}
	
	public boolean verification(String indice){
		return mc.verification(indice);
	}
	
	public MindZ(Sauvegarde s){
		joueur=s.joueur;
		taille_code=s.taille_code;
		tour=s.tour;
		couleurs=s.couleurs;
		couleurSelec=s.couleurSelec;
		tourSelec=s.tourSelec;
		placerCouleur=new Color[tour][taille_code];
		indice=new Color[tour][taille_code];
		cs=new int[s.cs.length];
		cp=new int[taille_code];
		reprise = true;
		for(int i=0; i<cs.length; i++){
			cs[i]=s.cs[i];
		}
		for(int i=0; i<tour; i++){
			for(int j=0; j<taille_code; j++){
				placerCouleur[i][j]=s.placerCouleur[i][j];
				indice[i][j]=s.indice[i][j];
			}
		}
		doublon=s.doublon;
		temps=s.temps;
		if(temps==0){
			c=new Chrono(s.chrono, true);
		}
		else{
			c=new Chrono(s.chrono, false);
		}
		mc=new MindCraft(this);
		chrono=c.panel;
		c.Start();
		sc = new Score(s.score);
		score=sc.panel;
		sc.Start();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(480,190));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		mp=new MindPanel(this);
		mp.tourSelec=tourSelec;
		mp.extra=mc.extra;
		mp.repaint();
	}
	
	public MindZ(int n){
		mc=new MindCraft(this);
		mc.setPar();
		joueur=n;
		if(n == 2){
			choixcouleurs=true;
			cs=new int[taille_code];
		}
		if(mc.temps==0){
			c=new Chrono();
		}
		else{
			c=new Chrono(mc.temps);
		}
		chrono=c.panel;
		sc = new Score();
		score=sc.panel;
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(480,190));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		if(n == 1){
			cs=mc.codeSecret();
		}
		if(n == 2){
			JOptionPane.showMessageDialog(null,"Joueur 1, allez vous cacher !\n"+"Le joueur 2 doit choisir un code !\n"+"Quand vous avez fini, cliquez sur OK.\n"+"N'oubliez pas de rapeller le joueur 1 !","Selection du code",JOptionPane.ERROR_MESSAGE);
		}
		mp=new MindPanel(this);
		mp.reset();
	}
	
	public MindZ(Color[] col){
		mc=new MindCraft(this);
		mc.setPar();
		c=new Chrono();
		chrono=c.panel;
		sc = new Score();
		score=sc.panel;
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(480,190));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		cs=mc.codeSecret();
		mp=new MindPanel(this);
		mp.reset();
	}
}