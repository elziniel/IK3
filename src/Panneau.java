import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

public class Panneau extends JPanel{
	
	public int i;
	public Fenetre f;
	
	public Panneau(Fenetre f) {
		this.f = f;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawRoundRect(10, 10, 115, 75, 10,10);
		g.drawString("Chrono", 45, 30);
		g.drawRoundRect(10, 95, 115, 75, 10,10);
		g.drawString("Score", 51, 115);
		g.drawRoundRect(135, 10, 500, 75, 10,10);
		g.drawString("Mastermind", 335, 30);
		g.drawString("Un jeu de Zahoua B., Jordan D., Esteban E. et Laure V.", 220,55);
		if(f.container!=null){
			for (int i=0; i<f.container.mc.couleurs; i++) {
				g.setColor(f.container.mc.couleur[i]);
				if(f.container.taille_code<5){
					g.fillRect(278+i*30, 310, 30, 30);
				}else{	
					g.fillRect(278+i*30, 413, 30, 30);
				}
			}
			g.setColor(f.container.mc.couleur[this.i]);
		}
		else{
			for (int i=0; i<f.containercpu.couleurs; i++) {
				g.setColor(f.containercpu.couleur[i]);
				g.fillRect(278+i*30, 310, 30, 30);
			}
			g.setColor(f.containercpu.couleur[this.i]);
		}
		g.fillRect(30, 175, 75, 75);
	}
	
	public void couleurS(int c){
		this.i = c;
	}
	
	public JScrollPane setContainerJeu(MindZ container){
		container.mp.add(container.panel);
		int longeur=0, largeur;
		if(!container.mp.extra){
			longeur= 30*(container.tour+5);
		}else{
			longeur= 30*(container.tourSelec+5);
		}
		largeur=30*(container.taille_code+1)+30*(container.taille_code/4+1);
		container.mp.setPreferredSize(new Dimension(longeur,largeur));
		JScrollPane scroll=new JScrollPane(container.mp);
		JViewport viewPort = scroll.getViewport();
		viewPort.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}
	
	public JScrollPane setContainerJeu(MindCPU container){
		container.mp.add(container.panel);
		int longeur=0, largeur;
		if(!container.mp.extra){
			longeur= 30*(container.tour+5);
		}else{
			longeur= 30*(container.tourSelec+5);
		}
		largeur=30*(container.taille_code+1)+30*(container.taille_code/4+1);
		container.mp.setPreferredSize(new Dimension(longeur,largeur));
		JScrollPane scroll=new JScrollPane(container.mp);
		JViewport viewPort = scroll.getViewport();
		viewPort.setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		return scroll;
	}
}