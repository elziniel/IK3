import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.NumberFormatter;

public class Options extends JFrame implements ActionListener{
	
	public JFrame fr;
	public JPanel panel = new JPanel();
	public JLabel titre = new JLabel("Options"), ltaille = new JLabel("Taille : "), couleur = new JLabel("Couleurs : "), ldoublons = new JLabel("Doublons :"), ltour = new JLabel("Tours :"), ltemps = new JLabel("Temps (s) :"), lblExtravaganza = new JLabel("Extravaganza :");
	public JComboBox choix = new JComboBox();
	public JCheckBox cdoublons = new JCheckBox(),
			extra = new JCheckBox();
	public JButton btnValider = new JButton("Valider"),
			btnRetour = new JButton("Retour");
	public JFormattedTextField taille, tours, temps;
	public int nbrtours, taille_code, couleurs, nbrtemps;
	public boolean doublons;
	public Properties prop = new Properties();


	public Options(JFrame fr){
		this.fr = fr;
		new Options();
	}
	
	public Options() {
		this.setTitle("Options");
		this.setResizable(false);
		this.setSize(250, 320);
		this.setLocationRelativeTo(null);
		for(int i=2; i<13; i++){
			choix.addItem(i);
		}
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{250, 0};
		gbl_panel.rowHeights = new int[]{100, 158, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		JPanel top = new JPanel();
		GridBagLayout gbl_top = new GridBagLayout();
		gbl_top.columnWidths = new int[] {45, 158, 0};
		gbl_top.rowHeights = new int[]{59, 0};
		gbl_top.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_top.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		top.setLayout(gbl_top);
		GridBagConstraints gbc_top = new GridBagConstraints();
		gbc_top.fill = GridBagConstraints.BOTH;
		gbc_top.insets = new Insets(0, 0, 0, 0);
		gbc_top.gridx = 0;
		gbc_top.gridy = 0;
		panel.add(top, gbc_top);
		titre.setFont(new Font("", Font.BOLD, 24));
		GridBagConstraints gbc_titre = new GridBagConstraints();
		gbc_titre.gridx = 1;
		gbc_titre.gridy = 0;
		top.add(titre, gbc_titre);
		JPanel centre = new JPanel();
		GridBagLayout gbl_centre = new GridBagLayout();
		gbl_centre.columnWidths = new int[] {130, 34, 34, 39, 56, 30, 10};
		gbl_centre.rowHeights = new int[] {28, 20, 23, 28, 29, 0, 24, 24};
		gbl_centre.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_centre.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		centre.setLayout(gbl_centre);
		GridBagConstraints gbc_ltaille = new GridBagConstraints();
		gbc_ltaille.anchor = GridBagConstraints.EAST;
		gbc_ltaille.insets = new Insets(0, 0, 5, 5);
		gbc_ltaille.gridx = 0;
		gbc_ltaille.gridy = 0;
		centre.add(ltaille, gbc_ltaille);
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    taille=new JFormattedTextField(formatter);
	    tours=new JFormattedTextField(formatter);
	    temps=new JFormattedTextField(formatter);
		
		GridBagConstraints gbc_Taille = new GridBagConstraints();
		gbc_Taille.gridwidth = 3;
		gbc_Taille.insets = new Insets(0, 0, 5, 5);
		gbc_Taille.fill = GridBagConstraints.HORIZONTAL;
		gbc_Taille.gridx = 1;
		gbc_Taille.gridy = 0;
		centre.add(taille, gbc_Taille);
		
		GridBagConstraints gbc_couleur = new GridBagConstraints();
		gbc_couleur.anchor = GridBagConstraints.EAST;
		gbc_couleur.insets = new Insets(0, 0, 5, 5);
		gbc_couleur.gridx = 0;
		gbc_couleur.gridy = 1;
		centre.add(couleur, gbc_couleur);
		choix.setPreferredSize(new Dimension(100, 20));
	
		GridBagConstraints gbc_choix = new GridBagConstraints();
		gbc_choix.anchor = GridBagConstraints.NORTHWEST;
		gbc_choix.insets = new Insets(0, 0, 5, 5);
		gbc_choix.gridwidth = 3;
		gbc_choix.gridx = 1;
		gbc_choix.gridy = 1;
		centre.add(choix, gbc_choix);
		
		GridBagConstraints gbc_ldoublons = new GridBagConstraints();
		gbc_ldoublons.anchor = GridBagConstraints.EAST;
		gbc_ldoublons.insets = new Insets(0, 0, 5, 5);
		gbc_ldoublons.gridx = 0;
		gbc_ldoublons.gridy = 2;
		centre.add(ldoublons, gbc_ldoublons);
		
		GridBagConstraints gbc_cdoublons = new GridBagConstraints();
		gbc_cdoublons.anchor = GridBagConstraints.NORTHWEST;
		gbc_cdoublons.insets = new Insets(0, 0, 5, 5);
		gbc_cdoublons.gridx = 2;
		gbc_cdoublons.gridy = 2;
		centre.add(cdoublons, gbc_cdoublons);
		
		GridBagConstraints gbc_centre = new GridBagConstraints();
		gbc_centre.fill = GridBagConstraints.BOTH;
		gbc_centre.gridx = 0;
		gbc_centre.gridy = 1;
		panel.add(centre, gbc_centre);
		taille.setText(String.valueOf(taille_code));
		choix.setSelectedItem(couleurs);
		cdoublons.setSelected(doublons);
		
		GridBagConstraints gbc_ltour = new GridBagConstraints();
		gbc_ltour.anchor = GridBagConstraints.EAST;
		gbc_ltour.insets = new Insets(0, 0, 5, 5);
		gbc_ltour.gridx = 0;
		gbc_ltour.gridy = 3;
		centre.add(ltour, gbc_ltour);
		
		GridBagConstraints gbc_formattedTextField = new GridBagConstraints();
		gbc_formattedTextField.gridwidth = 2;
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 2;
		gbc_formattedTextField.gridy = 3;
		centre.add(tours, gbc_formattedTextField);
		gbc_ltour.anchor = GridBagConstraints.EAST;
		gbc_ltour.insets = new Insets(0, 0, 5, 5);
		gbc_ltour.gridx = 0;
		gbc_ltour.gridy = 4;
		
		GridBagConstraints gbc_temps = new GridBagConstraints();
		gbc_temps.fill = GridBagConstraints.BOTH;
		gbc_temps.gridwidth = 2;
		gbc_temps.insets = new Insets(0, 0, 5, 5);
		gbc_temps.gridx = 2;
		gbc_temps.gridy = 4;
		gbc_formattedTextField.gridwidth = 2;
		gbc_formattedTextField.insets = new Insets(0, 0, 5, 5);
		gbc_formattedTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_formattedTextField.gridx = 2;
		gbc_formattedTextField.gridy = 4;
		centre.add(temps, gbc_temps);
		
		GridBagConstraints gbc_ltemps = new GridBagConstraints();
		gbc_ltemps.anchor = GridBagConstraints.EAST;
		gbc_ltemps.insets = new Insets(0, 0, 5, 5);
		gbc_ltemps.gridx = 0;
		gbc_ltemps.gridy = 4;
		centre.add(ltemps, gbc_ltemps);
		
		GridBagConstraints gbc_lblExtravaganza = new GridBagConstraints();
		gbc_lblExtravaganza.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblExtravaganza.insets = new Insets(0, 0, 5, 5);
		gbc_lblExtravaganza.gridx = 0;
		gbc_lblExtravaganza.gridy = 5;
		centre.add(lblExtravaganza, gbc_lblExtravaganza);
		
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.anchor = GridBagConstraints.WEST;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 2;
		gbc_checkBox.gridy = 5;
		extra.addActionListener(this);
		centre.add(extra, gbc_checkBox);
		
		GridBagConstraints gbc_valider = new GridBagConstraints();
		gbc_valider.insets = new Insets(0, 0, 5, 5);
		gbc_valider.anchor = GridBagConstraints.NORTHEAST;
		gbc_valider.gridx = 0;
		gbc_valider.gridy = 6;
		btnValider.addActionListener(this);
		centre.add(btnValider, gbc_valider);
		
		GridBagConstraints gbc_Retour = new GridBagConstraints();
		gbc_Retour.insets = new Insets(0, 0, 5, 5);
		gbc_Retour.anchor = GridBagConstraints.NORTHEAST;
		gbc_Retour.gridx = 2;
		gbc_Retour.gridy = 6;
		btnRetour.addActionListener(this);
		centre.add(btnRetour, gbc_Retour);
		
		try{
			this.prop.load(new FileInputStream("options.properties"));
			taille_code = Integer.parseInt(this.prop.getProperty("taille"));
			taille.setText(String.valueOf(taille_code));
			nbrtours = Integer.parseInt(this.prop.getProperty("tours"));
			tours.setText(String.valueOf(nbrtours));
			couleurs = Integer.parseInt(this.prop.getProperty("nbrechoix"));
			choix.setSelectedItem(couleurs);
			doublons = Boolean.parseBoolean(this.prop.getProperty("doublons"));
			cdoublons.setSelected(doublons);
			extra.setSelected(Boolean.parseBoolean(this.prop.getProperty("Extravaganza")));
			nbrtemps = Integer.parseInt(this.prop.getProperty("temps"));
			temps.setText(String.valueOf(nbrtemps));
		}catch(Exception e){	
			taille.setText(String.valueOf(4));
			tours.setText(String.valueOf(10));
			choix.setSelectedItem(6);
			cdoublons.setSelected(false);
			extra.setSelected(false);
			temps.setText(String.valueOf(0));
		}
		this.setContentPane(panel);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		Object source = e.getSource();
		
		if(source == btnValider){
			Properties prop=new Properties();
			String tou="",tai=taille.getText().replace("\u00A0",""),tem=temps.getText().replace("\u00A0","");
			tou=tours.getText().replace("\u00A0","");		
			try{
				prop.load(new FileInputStream("options.properties"));
				prop.setProperty("taille",tai);
				prop.setProperty("tours",tou);
				prop.setProperty("temps",tem);
				prop.setProperty("nbrechoix",String.valueOf(choix.getSelectedItem()));
				int taille_code = Integer.parseInt(prop.getProperty("taille")), couleurs = Integer.parseInt(prop.getProperty("nbrechoix"));
				boolean d=cdoublons.isSelected();
				if(taille_code>couleurs && d==false){
					JOptionPane.showMessageDialog(null,"Le code doit contenir des doublons\n"+"car la taille du code est plus\n"+"grande que le nombre de couleurs","Doublons Necessaires",JOptionPane.ERROR_MESSAGE);
					d=true;
				}
				prop.setProperty("doublons",String.valueOf(d));
				prop.setProperty("Extravaganza", String.valueOf(extra.isSelected()));
				prop.store(new FileOutputStream("options.properties"), null);
			}catch(IOException ex){
				prop.setProperty("taille",tai);
				prop.setProperty("tours",tou);
				prop.setProperty("temps",tem);
				prop.setProperty("nbrechoix",String.valueOf(choix.getSelectedItem()));
				int taille_code = Integer.parseInt(prop.getProperty("taille")), couleurs = Integer.parseInt(prop.getProperty("nbrechoix"));
				boolean d=cdoublons.isSelected();
				if(taille_code>couleurs && d==false){
					JOptionPane.showMessageDialog(null,"Le code doit contenir des doublons\n"+"car la taille du code est plus\n"+"grande que le nombre de couleurs","Doublons Necessaires",JOptionPane.ERROR_MESSAGE);
					d=true;
				}
				prop.setProperty("doublons",String.valueOf(d));
				prop.setProperty("Extravaganza", String.valueOf(extra.isSelected()));
				try {
					prop.store(new FileOutputStream("options.properties"), null);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			this.dispose();
			if(fr != null){
				fr.dispose();
			}
		}
		if(source == btnRetour){
			this.dispose();
		}
	}
}
