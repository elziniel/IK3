import java.awt.Graphics;

import javax.swing.JPanel;

public class Couleur extends JPanel{
	public MindZ m;
	public MindCPU cp;
	public int r, g, b;
	public RGB rb;
	public JPanel j;
	
	public Couleur(MindZ m, JPanel j,RGB rb){
		this.m = m;
		this.j=j;
		this.rb=rb;
	}
	
	public Couleur(MindCPU m, JPanel j,RGB rb){
		this.cp = m;
		this.j=j;
		this.rb=rb;
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (int i=0; i<rb.c.length; i++) {
			if(m!=null){	
				g.setColor(m.couleur[i]);
			}else{
				g.setColor(cp.couleur[i]);
			}
			g.fillRect(21*i, 21, 21, 21);
		}
	}
}