import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigInteger;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.jdom2.JDOMException;

public class OkListener implements ActionListener{
	public JButton ok;
	public Fenetre j;
	public MindZ m;
	public MindCPU mc;
	public MindCraft mcft;
	public Tableau t;
	public boolean joueur;
	public BigInteger n = BigInteger.ZERO;
	
	public OkListener(MindZ c, Fenetre jf){
		this.joueur=true;
		this.m=c;
		this.j=jf;
		this.ok=jf.btnOk;
	}
	
	public OkListener(MindCPU m, Tableau t){
		this.mc=m;
		this.mcft=mc.mcft;
		this.t = t;
		this.j=m.f;
		j.o.joueur=false;
	}
	
	public void actionPerformed(ActionEvent arg0){
		if(joueur==true){
			this.mcft=m.mc;
			if(ok.getText().equals("Rejouer ?") || ok.getText().equals("Perdu !")){
				j.dispose();
				new Fenetre(m.joueur);
			}else{
				if(m.c!=null && m.c.fin){
					ok.setText("Perdu !");
				}
				else{
					int white=0;
					for(int i=0; i<m.taille_code; i++){
						if(m.mp.placerCouleur[m.mp.tourSelec][i].equals(Color.white)){
							white++;
						}
					}
					if(m.mp.choixcouleurs == true){
						for(int i=0; i<m.taille_code; i++){
							for(int j=0; j<m.mp.couleur.length; j++){
								if(m.mp.placerCouleur[m.mp.tourSelec][i] == m.mp.couleur[j]){
									m.cs[i] = j;
									break;
								}
							}
							m.mp.placerCouleur[m.mp.tourSelec][i] = Color.white;
						}
						m.mp.cs=m.cs;
					}
					if((white==0 && m.mp.choixcouleurs==false) || (m.mp.reprise==true && white==0)){
						if(m.mp.reprise==true){
							m.mp.reprise=false;
						}
						for(int i=0; i<m.taille_code; i++){
							for(int j=0; j<m.mp.couleur.length; j++){
								if(m.mp.placerCouleur[m.mp.tourSelec][i].equals(m.mp.couleur[j])){
									m.cp[i] = j;
									break;
								}
							}
						}
						int[] placement=mcft.comparaison(m.cs, m.cp);
						m.s=mcft.indication(placement);
						m.indicationCouleur(m.s);
						m.mp.sc.tour(m);
						m.mp.repaint();
						if(m.verification(m.s)){
							m.mp.c.Stop();
							if(!m.mp.extra){
								m.mp.sc.Victoire(m);
							}
							Sauvegarde s=new Sauvegarde(m);
							StatsSaver ss=new StatsSaver(s,true);
							try {
								ss.Stats();
							} catch (JDOMException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							m.mp.fini = true;
							m.mp.gagne = true;
							ok.setText("Rejouer ?");
						}
						else if(m.mp.tourSelec > m.mp.tour - 2){
							m.mp.c.Stop();
							Sauvegarde s=new Sauvegarde(m);
							StatsSaver ss=new StatsSaver(s,false);
							try {
								ss.Stats();
							} catch (JDOMException e) {
							} catch (IOException e) {
							}
							m.mp.fini = true;
							m.mp.perdu = true;
							ok.setText("Rejouer ?");
						}
						else{
							m.mp.tourSelec++;
							if(m.mp.extra){
								int longeur= 30*(m.mp.tourSelec+5);
								int largeur=30*(m.mp.taille_code+1)+30*(m.mp.taille_code/4+1);
								m.mp.setPreferredSize(new Dimension(longeur,largeur));
								m.mp.revalidate();
								m.mp.repaint();
							}
						}
					}else{
						if(white==0){
							m.mp.tourSelec=0;
						}
						if(m.choixcouleurs==true){
							m.mp.choixcouleurs=false;
							m.mp.couleurSelec=0;
							m.mp.repaint();
						}
					}
				}
			}
		}if(mc!=null){
			if(j.btnOk.getText().equals("Rejouer ?")){
				j.dispose();
				new Fenetre(mc.joueur);
			}
			if(mc.mp.choixcouleurs==true){
				for(int i=0; i<mc.taille_code; i++){
					for(int j=0; j<mc.mp.couleur.length; j++){
						if(mc.mp.placerCouleur[0][i] == mc.mp.couleur[j]){
							mc.cs[i] = j;
							break;
						}
					}
					mc.mp.placerCouleur[0][i] = Color.white;
				}
				mc.mp.cs=mc.cs;
				if(!mcft.doublon){
					for(int i=0; i<mcft.taille_code-1; i++){
						for(int j=i+1; j<mcft.taille_code; j++){
							if(mc.mp.cs[i] == mc.mp.cs[j]){
								mcft.doublon = true;
							}
						}
					}
				}
			}else{
				for(int i=0; i<mc.taille_code; i++){
					for(int j=0; j<mc.mp.couleur.length; j++){
						if(mc.mp.placerCouleur[mc.mp.tourSelec][i].equals(mc.mp.couleur[j])){
							mc.ap[mc.mp.tourSelec][i] = mc.cp[i] = j;
							break;
						}
					}
				}
				int[] placement=mcft.comparaison(mc.cs, mc.cp);
				mc.s2[mc.mp.tourSelec] = mc.s=mcft.indication(placement);
				if(mc.joueur == 0){
					int[] choixBon = new int[mc.taille_code], choixBon2 = new int[mc.taille_code], choixCoul = new int[mc.mp.couleurs];
					int k, l;
					int[] bc = new int[mc.taille_code];
					for(int i=0; i<mc.s.length(); i++){
						k = (int)(Math.random()*bc.length);
						while(choixBon[k] == 1){
							k = (int)(Math.random()*bc.length);
						}
						if(mc.s.charAt(i) == '+'){
							choixCoul[mc.cp[k]] = 1;
							bc[k] = mc.cp[k];
							choixBon[k] = 1;
							choixBon2[k] = 1;
						}
						if(mc.s.charAt(i) == '*'){
							choixCoul[mc.cp[k]] = 1;
							l = (int)(Math.random()*bc.length);
							while(choixBon2[l] == 1){
								l = (int)(Math.random()*bc.length);
							}
							bc[l] = mc.cp[k];
							choixBon[k] = 1;
							choixBon2[l] = 1;
						}
						if(mc.s.charAt(i) == '-'){
							for(int j=0; j<bc.length; j++){
								choixCoul[mc.cp[j]] = 1;
							}
							while(choixBon2[k] == 1){
								k = (int)(Math.random()*bc.length);
							}
							l = (int)(Math.random()*mc.couleurs);
							if(!mc.doublon){
								while(choixCoul[l] == 1){
									l = (int)(Math.random()*mc.couleurs);
								}
								choixCoul[l] = 1;
							}
							bc[k] = l;
							choixBon2[k] = 1;
						}
					}
					if(mc.mp.tourSelec <= mc.tour - 2){
						for(int i=0; i<mc.taille_code; i++){
							mc.mp.placerCouleur[mc.mp.tourSelec+1][i] = mc.mp.couleur[bc[i]];
						}
					}
				}
				if(mc.joueur == 3 || (mc.joueur==4 && mc.mp.choixcouleurs==false)){
					boolean b = true;
					long l = 0;
					if(mc.s.charAt(0) == '-'){
						for(int i=0; i<mc.taille_code; i++){
							l += Math.pow(mc.couleurs, i);
						}
						n = n.add(BigInteger.valueOf(l));
					}
					else{
						n = n.add(BigInteger.valueOf(1));
					}
					while(b && !mc.verification(mc.s)){
						t = new Tableau(n, mc.couleurs, mc.taille_code);
						int[] p2;
						String in2="";
						int[] cp = new int[mc.cp.length];
						t.copieTableau(t.combinaison, cp);
						for(int i=0; i<=mc.mp.tourSelec; i++){
							p2 = mcft.comparaison(mc.ap[i], cp);
							in2 = mcft.indication(p2);
							for(int j=0; j<placement.length; j++){
								if(in2.charAt(j) != mc.s2[i].charAt(j)){
									b = true;
									break;
								}
								b = false;
							}
							if(b){
								break;
							}
						}
						if(!mcft.doublon){
							for(int i=0; i<mcft.taille_code-1; i++){
								for(int j=i+1; j<mcft.taille_code; j++){
									if(cp[i] == cp[j]){
										b = true;
									}
								}
							}
						}
						if(b){
							n = n.add(BigInteger.valueOf(1));
						}
					}
					if(!mc.verification(mc.s)){
						if(mc.mp.tourSelec <= mc.tour - 2){
							for(int i=0; i<mc.taille_code; i++){
								mc.mp.placerCouleur[mc.mp.tourSelec + 1][i] = mc.mp.couleur[(int)(t.combinaison.charAt(i)-48)];
							}
						}
					}
				}
				mc.indicationCouleur(mc.s);
				mc.mp.repaint();
				if(mc.verification(mc.s)){
					
					Sauvegarde s=new Sauvegarde(mc);
					StatsSaver ss=new StatsSaver(s,true);
					try {
						ss.Stats();
					} catch (JDOMException e) {
					} catch (IOException e) {
					}
					mc.mp.fini = true;
					mc.mp.gagne = true;
					mc.ok.stop();
					mc.okgen.setText("Rejouer ?");
				}
				else if(mc.mp.tourSelec > mc.tour - 2){
					Sauvegarde s=new Sauvegarde(mc);
					StatsSaver ss=new StatsSaver(s,false);
					try {
						ss.Stats();
					} catch (JDOMException e) {
					} catch (IOException e) {
					}
					mc.ok.stop();
					mc.mp.fini = true;
					mc.mp.perdu = true;
					mc.okgen.setText("Rejouer ?");
				}
				else{
					mc.mp.tourSelec++;
					if(mc.mp.extra){
						int longeur= 30*(mc.mp.tourSelec+5);
						int largeur=30*(mc.mp.taille_code+1)+30*(mc.mp.taille_code/4+1);
						mc.mp.setPreferredSize(new Dimension(longeur,largeur));
						mc.mp.revalidate();
						mc.mp.repaint();
					}
				}
			}if(mc.mp.choixcouleurs){
				mc.mp.choixcouleurs=false;
				mc.mp.couleurSelec=0;
				mc.pc = mcft.codeSecret();
				for(int i=0; i<mc.taille_code; i++){
					mc.placerCouleur[0][i] = mc.couleur[mc.pc[i]];
				}
				mc.mp.repaint();
				mc.okgen.removeActionListener(mc.okgen.getActionListeners()[0]);
				mc.ok.start();
			}
		}
		if(mc==null && joueur==false){
			if(j.btnOk.getText().equals("Rejouer ?")){
				j.dispose();
				if(j.container!=null){
					new Fenetre(j.container.joueur);
				}else{
					new Fenetre(j.containercpu.joueur);
				}
			}
		}
	}
}
