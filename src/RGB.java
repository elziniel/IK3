import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RGB extends JFrame implements ActionListener, ChangeListener, MouseListener{
	public int r,v,b, couleurSelec;
	public JFrame fr;
	public JPanel couleur=new JPanel();
	public JSlider sliderrouge,slidervert,sliderbleu;
	public Couleur panel;
	public Fenetre f;
	public Color[] c;
	public JButton btnRetour = new JButton("Retour"),
			btnValider = new JButton("Valider");
	public Properties prop=new Properties();
	
	public RGB(Fenetre f, JFrame fr){
		this.f = f;
		this.fr = fr;
		if(f.container!=null){
			c=new Color[f.container.couleurs];
			this.panel= new Couleur(f.container,couleur,this);
		}else{
			c=new Color[f.containercpu.couleurs];
			this.panel= new Couleur(f.containercpu,couleur,this);
		}
		this.panel.addMouseListener(this);
		couleur.setVisible(true);
		couleur.setBackground(new Color(r,v,b));
		this.setTitle("Couleurs");
		this.setSize(375,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {125, 101, 101, 120, 0};
		gridBagLayout.rowHeights = new int[]{70,70,70,260,80,40};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		
		JLabel Rouge = new JLabel("Rouge");
		GridBagConstraints gbc_Rouge = new GridBagConstraints();
		gbc_Rouge.anchor = GridBagConstraints.EAST;
		gbc_Rouge.fill = GridBagConstraints.BOTH;
		gbc_Rouge.insets = new Insets(0, 30, 5, 5);
		gbc_Rouge.gridx = 0;
		gbc_Rouge.gridy = 0;
		this.getContentPane().add(Rouge, gbc_Rouge);

		sliderrouge = new JSlider(JSlider.HORIZONTAL,0,255,0);
		sliderrouge.addChangeListener(this);
		sliderrouge.setMajorTickSpacing(255);
		sliderrouge.setMajorTickSpacing(40);
		sliderrouge.setPaintTicks(true);
		sliderrouge.setPaintLabels(true);
		sliderrouge.getValue();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.gridwidth = 2;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.fill = GridBagConstraints.BOTH;
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 0;
		this.getContentPane().add(sliderrouge, gbc_slider);
		
		JLabel Vert = new JLabel("Vert");
		GridBagConstraints gbc_Vert = new GridBagConstraints();
		gbc_Vert.anchor = GridBagConstraints.EAST;
		gbc_Vert.fill = GridBagConstraints.BOTH;
		gbc_Vert.insets = new Insets(0, 30, 5, 5);
		gbc_Vert.gridx = 0;
		gbc_Vert.gridy = 1;
		this.getContentPane().add(Vert, gbc_Vert);

		slidervert = new JSlider(JSlider.HORIZONTAL,0,255,0);
		slidervert.setMajorTickSpacing(255);
		slidervert.setMajorTickSpacing(40);
		slidervert.setPaintTicks(true);
		slidervert.setPaintLabels(true);
		slidervert.addChangeListener(this);
		GridBagConstraints gbc_slider_vert = new GridBagConstraints();
		gbc_slider_vert.gridwidth = 2;
		gbc_slider_vert.insets = new Insets(0, 0, 5, 5);
		gbc_slider_vert.fill = GridBagConstraints.BOTH;
		gbc_slider_vert.gridx = 1;
		gbc_slider_vert.gridy = 1;
		this.getContentPane().add(slidervert, gbc_slider_vert);
		
		JLabel Bleu = new JLabel("Bleu");
		GridBagConstraints gbc_bleu = new GridBagConstraints();
		gbc_bleu.anchor = GridBagConstraints.EAST;
		gbc_bleu.fill = GridBagConstraints.BOTH;
		gbc_bleu.insets = new Insets(0, 30, 5, 5);
		gbc_bleu.gridx = 0;
		gbc_bleu.gridy = 2;
		this.getContentPane().add(Bleu,gbc_bleu);
		
		sliderbleu = new JSlider(JSlider.HORIZONTAL,0,255,0);
		sliderbleu.addChangeListener(this);
		sliderbleu.setMajorTickSpacing(255);
		sliderbleu.setMajorTickSpacing(40);
		sliderbleu.setPaintTicks(true);
		sliderbleu.setPaintLabels(true);
		sliderbleu.getValue();
		GridBagConstraints gbc_slider_bleu = new GridBagConstraints();
		gbc_slider_bleu.gridwidth = 2;
		gbc_slider_bleu.insets = new Insets(0, 0, 5, 5);
		gbc_slider_bleu.fill = GridBagConstraints.BOTH;
		gbc_slider_bleu.gridx = 1;
		gbc_slider_bleu.gridy = 2;
		this.getContentPane().add(sliderbleu, gbc_slider_bleu);

		GridBagConstraints gbc_couleur = new GridBagConstraints();
		gbc_couleur.fill = GridBagConstraints.BOTH;
		gbc_couleur.gridwidth = 3;
		gbc_couleur.insets = new Insets(0, 30, 5, 5);
		gbc_couleur.gridx = 0;
		gbc_couleur.gridy = 3;
		this.getContentPane().add(couleur, gbc_couleur);
		
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 35, 0, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 4;
		this.getContentPane().add(panel, gbc_panel);
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout());
		GridBagConstraints gbc_buttons=new GridBagConstraints();
		gbc_buttons.gridwidth = 2;
		gbc_buttons.insets=new Insets(0,0,0,55);
		gbc_buttons.gridx=1;
		gbc_buttons.gridy=5;

		btnRetour.addActionListener(this);
		btnValider.addActionListener(this);
		buttons.add(btnValider);
		buttons.add(btnRetour);
		this.getContentPane().add(buttons,gbc_buttons);
		this.setVisible(true);
	}
	
	public void mousePressed(MouseEvent e){
		int x = e.getX(), y = e.getY();
		if(f.container!=null){	
			if(y>18 && y<45 && x>0 && x<f.container.couleur.length*21){
				couleurSelec = (x-3)/21;
				this.r = f.container.couleur[couleurSelec].getRed();
				this.v = f.container.couleur[couleurSelec].getGreen();
				this.b = f.container.couleur[couleurSelec].getBlue();
				sliderrouge.setValue(this.r);
				slidervert.setValue(this.v);
				sliderbleu.setValue(this.b);
				couleur.setBackground(new Color(this.r,this.v,this.b));
				this.validate();
			}
		}else{
			if(y>18 && y<45 && x>0 && x<f.containercpu.couleur.length*21){
				couleurSelec = (x-3)/21;
				this.r = f.containercpu.couleur[couleurSelec].getRed();
				this.v = f.containercpu.couleur[couleurSelec].getGreen();
				this.b = f.containercpu.couleur[couleurSelec].getBlue();
				sliderrouge.setValue(this.r);
				slidervert.setValue(this.v);
				sliderbleu.setValue(this.b);
				couleur.setBackground(new Color(this.r,this.v,this.b));
				this.validate();
			}
		}
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	
	public void actionPerformed(ActionEvent e){
		
		Object source = e.getSource();
		
		if(source == btnValider){
			try {
				prop.load(new FileInputStream("options.properties"));
				for(int i=0; i<this.c.length; i++){
					if(c[i]!=null){
						prop.setProperty("Couleur"+i, String.valueOf((c[i].getRGB())));
					}else{
						if(f.container==null){
							this.c[i]=f.containercpu.couleur[i];
							prop.setProperty("Couleur"+i, String.valueOf((f.containercpu.couleur[i].getRGB())));
						}
						else{
							this.c[i]=f.container.couleur[i];
							prop.setProperty("Couleur"+i, String.valueOf((f.container.couleur[i].getRGB())));
						}
					}
				}
				prop.store(new FileOutputStream("options.properties"), null);
			}catch (IOException ex) {
				for(int i=0; i<this.c.length; i++){
					if(c[i]!=null){
						prop.setProperty("Couleur"+i, String.valueOf((c[i].getRGB())));
					}else{
						if(f.container!=null){
							this.c[i]=f.container.couleur[i];
							prop.setProperty("Couleur"+i, String.valueOf((f.container.couleur[i].getRGB())));
						}else{
							this.c[i]=f.containercpu.couleur[i];
							prop.setProperty("Couleur"+i, String.valueOf((f.containercpu.couleur[i].getRGB())));
						}
					}
				}
				try {
					prop.store(new FileOutputStream("options.properties"), null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			this.dispose();
		}
		if(source == btnRetour){
			this.dispose();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		
		Object source = e.getSource();

		if(source == sliderrouge){
			r = sliderrouge.getValue();
			if(this.f.container!=null){
				v=this.f.container.couleur[this.couleurSelec].getGreen();
				b=this.f.container.couleur[this.couleurSelec].getBlue();
				this.f.container.couleur[this.couleurSelec]=new Color(r,v,b);
			}else{
				v=this.f.containercpu.couleur[this.couleurSelec].getGreen();
				b=this.f.containercpu.couleur[this.couleurSelec].getBlue();
				this.f.containercpu.couleur[this.couleurSelec]=new Color(r,v,b);
			}
			c[couleurSelec]=new Color(r,v,b);
			panel.repaint();
			couleur.setBackground(new Color(r, v, b));
			couleur.repaint();
		}
		if(source == slidervert){
			v = slidervert.getValue();
			if(this.f.container!=null){
				r=this.f.container.couleur[this.couleurSelec].getRed();
				b=this.f.container.couleur[this.couleurSelec].getBlue();
				this.f.container.couleur[this.couleurSelec]=new Color(r,v,b);
			}else{
				r=this.f.containercpu.couleur[this.couleurSelec].getRed();
				b=this.f.containercpu.couleur[this.couleurSelec].getBlue();
				this.f.containercpu.couleur[this.couleurSelec]=new Color(r,v,b);
			}
			c[couleurSelec]=new Color(r,v,b);
			panel.repaint();
			couleur.setBackground(new Color(r, v, b));
			couleur.repaint();
		}
		if(source == sliderbleu){
			b = sliderbleu.getValue();
			if(this.f.container!=null){
				r=this.f.container.couleur[this.couleurSelec].getRed();
				v=this.f.container.couleur[this.couleurSelec].getGreen();
				this.f.container.couleur[this.couleurSelec]=new Color(r,v,b);
			}else{
				r=this.f.containercpu.couleur[this.couleurSelec].getRed();
				v=this.f.containercpu.couleur[this.couleurSelec].getGreen();
				this.f.containercpu.couleur[this.couleurSelec]=new Color(r,v,b);
			}
			c[couleurSelec]=new Color(r,v,b);
			panel.repaint();
			couleur.setBackground(new Color(r, v, b));
			couleur.repaint();
		}
	}
}
