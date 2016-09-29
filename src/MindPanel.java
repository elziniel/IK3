import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class MindPanel extends JPanel implements MouseListener{
	public int tour, taille_code, couleurs, l, longueur, largeur, couleurSelec, tourSelec;
	public Color[] couleur;
	public Color[][] placerCouleur, indice;
	public boolean doublon = false, start=false, fini=false, gagne=false, perdu=false, choixcouleurs, reprise=false, pause=false, extra=false;
	public Chrono c; 
	public Score sc;
	public int[] cs;
	public JPanel panel, chrono, score;
	
	public MindPanel(MindZ m){
		tour=m.tour;
		taille_code=m.taille_code;
		couleurs=m.couleurs;
		cs=new int[taille_code];
		couleur=new Color[couleurs];
		for(int i=0; i<taille_code; i++){
			cs[i]=m.cs[i];
		}
		for(int i=0; i<couleurs; i++){
			couleur[i]=m.couleur[i];
		}
		start=m.start;
		choixcouleurs=m.choixcouleurs;
		reprise=m.reprise;
		placerCouleur=m.placerCouleur;
		indice=m.indice;
		extra=m.mc.extra;
		c=m.c;
		sc=m.sc;
		if(extra){
			sc.s=couleurs+taille_code;
		}else{
			sc.s=tour+couleurs+taille_code;
		}
		sc.calc=(int) Math.pow(Math.E, sc.s);
		addMouseListener(this);
		this.setPreferredSize(new Dimension(480,190));		
	}
	
	public MindPanel(MindCPU m){
		tour=m.tour;
		taille_code=m.taille_code;
		couleurs=m.couleurs;
		cs=new int[taille_code];
		couleur=new Color[couleurs];
		for(int i=0; i<taille_code; i++){
			cs[i]=m.cs[i];
		}
		for(int i=0; i<couleurs; i++){
			couleur[i]=m.couleur[i];
		}
		start=m.start;
		choixcouleurs=m.choixcouleurs;
		placerCouleur=m.placerCouleur;
		indice=m.indice;
		addMouseListener(this);
		this.setPreferredSize(new Dimension(480,190));		
	}
	
	public void paint(Graphics g) {
	    super.paintComponent(g);
		longueur =30;
		largeur = 30;
		remplirPlateau(g);
		dessinerPlateau(g);
	}
	
	public void setCouleurSelec(int i){
		couleurSelec = i;		
	}
	
	public void reset() {
		for (int i=0; i < tour; i++) {
			for (int j=0; j < taille_code; j++) {
				placerCouleur[i][j] = Color.white;
				indice[i][j] = Color.lightGray;
			}
		}
		start=false;
		tourSelec = 0;
		couleurSelec = 0;
	}
	
	private void remplirPlateau(Graphics g) {
		for (int i=0; i <= tourSelec; i++) {
			for (int j=0; j < taille_code; j++) {
				g.setColor(placerCouleur[i][j]);
				g.fillOval(longueur*(i+1), largeur*(j+1), longueur-5, largeur-5);
			}
		}
		for (int i=0; i < tourSelec; i++) {
			for (int j=0; j < taille_code; j++) {
				g.setColor(indice[i][j]);
				if(j%2!=0){
					// Indice a droite
					g.fillRect(longueur*(i+1)+j%2*longueur/2-1, (taille_code+1)*largeur+j/2*largeur/2, (longueur)/2-4, largeur/2);
				}else{
					// Indice a gauche
					g.fillRect(longueur*(i+1)+j%2*longueur/2, (taille_code+1)*largeur+j/2*largeur/2, (longueur)/2-1, largeur/2);
				}
			}
		}
	}

	private void dessinerPlateau(Graphics g) {
		g.setColor(Color.black);
		// Emplacement des pions
		int tmp=0;
		if(extra){
			tmp=tour;
			tour=tourSelec+1;
		}
		for (int i=0; i<tour; i++) {
			for (int j=0; j<taille_code; j++) {
				g.drawOval(longueur*(i+1), largeur*(j+1), longueur-5, largeur-5);
			}
		}
		if(taille_code%2!=0){
			l = taille_code/2+1;
		}
		else{
			l = taille_code/2;
		}
		// Premiere barre verticale des indices
		for (int i=0; i<tour; i++) {
			g.drawLine(longueur*(i+1), largeur*(taille_code+1), longueur*(i+1), largeur*(taille_code+1)+(largeur*l)/2);
		}
		// Deuxieme barre verticale des indices
		for (int i=0; i<tour; i++) {
			g.drawLine((i+1)*longueur-2+longueur/2, largeur*(taille_code+1), (i+1)*longueur-2+longueur/2, largeur*(taille_code+1)+(largeur*l)/2);
		}
		// Derniere barre verticale des indices
		for (int i=1; i<tour+1; i++) {
			g.drawLine(longueur*(i+1)-5, largeur*(taille_code+1), longueur*(i+1)-5, largeur*(taille_code+1)+(largeur*l)/2);
		}
		for (int i=0; i<tour; i++) {
				// Premiere barre horizontale des indices
				g.drawLine(longueur*(i+1), largeur*(taille_code+1), longueur*(i+2)-5, largeur*(taille_code+1));
				// Prochaines barres horizontales des indices
				for(int j=0; j<l+1; j++){
					g.drawLine(longueur*(i+1), largeur*(taille_code+1)+j*largeur/2, longueur*(i+2)-5, largeur*(taille_code+1)+j*largeur/2);
				}
				// Derniere barre horizontale des indices
				g.drawLine(longueur*(i+1), largeur*(taille_code+2), longueur*(i+2)-5, largeur*(taille_code+2));
		}
		if(tmp!=0 && !fini)
			tour=tmp;
		if(fini){
			for(int i = 0; i < taille_code; i++){
				g.setColor(couleur[cs[i]]);
				g.fillOval(longueur*(tour+3), largeur*(i+1), longueur-5, largeur-5);
				g.setColor(Color.black);
				g.drawOval(longueur*(tour+3), largeur*(i+1), longueur-5, largeur-5);
			}
			if(gagne){
				g.drawString("FATALITY!", longueur*(tour+3), largeur*(taille_code+1)+largeur/2);
			}
			if(perdu){
				g.drawString("PERDU", longueur*(tour+3), largeur*(taille_code+1)+largeur/2);
			}
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		if (x < longueur) {
			repaint();
		}
		else if (x < longueur*(tour+1)+longueur/2 && pause==false){
			if(y < largeur*(taille_code+1) && x > longueur+longueur*tourSelec && y > largeur && x < longueur*2+longueur*tourSelec){
				y = (y-largeur)/largeur;
				placerCouleur[tourSelec][y] = couleur[couleurSelec];
				if(!start && !choixcouleurs){
					start=true;
					if(reprise==false){
						c.Start();
						chrono=c.panel;
						sc.Start();
						score = sc.panel;
					}
				}
				repaint();
			}
		}
	}	
	
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
}
