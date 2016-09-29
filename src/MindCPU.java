import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;
import java.math.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MindCPU{
	public int tour, taille_code, couleurs, l, longueur, largeur, couleurSelec, tourSelec, joueur, delai=500;
	public Color[] couleur = {Color.red, Color.green, Color.blue, Color.cyan, Color.magenta, Color.yellow, Color.orange, Color.pink,Color.LIGHT_GRAY, Color.gray, Color.DARK_GRAY, Color.black};
	public Color[][] placerCouleur, indice;
	public JPanel panel;
	public boolean doublon = false, start=false, fini=false, gagne=false, perdu=false, choixcouleurs;
	public JButton okgen;
	public Fenetre f;
	public int[] cp, cs, pc;
	public int[][] ap;
	public String[] s2;
	public String s;
	public ActionListener actionScore;
	public Timer ok;
	public Properties prop = new Properties();
	public MindCraft mcft;
	public MindPanel mp;
	public Tableau t;
	
	public void indicationCouleur(String s){
		mcft.indicationCouleur(indice, mp.tourSelec, s);
	}
	
	public boolean verification(String indice){
		return mcft.verification(indice);
	}
	
	public void Malingre(){
		cp=mcft.codeSecret();
		for(int i=0; i<taille_code; i++){
			placerCouleur[0][i]=couleur[cp[i]];
		}
	}
	
	public MindCPU(Fenetre fen, int y){
		this.f=fen;
		this.okgen=f.btnOk;
		this.joueur=y;
		mcft=new MindCraft(this);
		mcft.setPar();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(480,190));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		s2 = new String[tour];
		pc = new int[taille_code];
		ap = new int[tour][taille_code];
		if(joueur == 4){
			choixcouleurs = true;
			cs=new int[taille_code];
			okgen.addActionListener(new OkListener(this, t));
		}else{
			cs=mcft.codeSecret();
		}
		mp=new MindPanel(this);
		mp.reset();
		if(joueur == 0){
			Malingre();
		}
		if(joueur==3){
			pc = mcft.codeSecret();
			for(int i=0; i<taille_code; i++){
				placerCouleur[0][i] = couleur[pc[i]];
			}
		}
		final JButton Ok=new JButton();
		Ok.addActionListener(new OkListener(this, t));
		actionScore = new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				Ok.doClick();
			}
		};
		ok = new Timer(delai, actionScore);
		if(joueur!=4){
			ok.start();
		}
	}
}