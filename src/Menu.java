import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame implements ActionListener{
	
	public Fenetre f;
	public JButton btnMenuPrincipal = new JButton("Menu principal"),
			btnNouvellePartie = new JButton("Nouvelle Partie"),
			btnParametres = new JButton("Parametres"),
			btnRetour = new JButton("Retour");
	
	public Menu(Fenetre f){
		
		this.f = f;
		this.setTitle("Menu");
		this.setSize(190,250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190, 0};
		gridBagLayout.rowHeights = new int[]{40,40,40,40,40};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0};
		this.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		JLabel menu=new JLabel("Menu");
		menu.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		panel.add(menu);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.getContentPane().add(panel, gbc_panel);
		
		GridBagConstraints gbc_btnMenuPrincipal = new GridBagConstraints();
		gbc_btnMenuPrincipal.insets = new Insets(0, 0, 5, 0);
		gbc_btnMenuPrincipal.gridx = 0;
		gbc_btnMenuPrincipal.gridy = 1;
		btnMenuPrincipal.addActionListener(this);
		this.getContentPane().add(btnMenuPrincipal, gbc_btnMenuPrincipal);
		
		GridBagConstraints gbc_btnNouvellePartie = new GridBagConstraints();
		gbc_btnNouvellePartie.insets = new Insets(0, 0, 5, 0);
		gbc_btnNouvellePartie.gridx = 0;
		gbc_btnNouvellePartie.gridy = 2;
		btnNouvellePartie.addActionListener(this);
		this.getContentPane().add(btnNouvellePartie, gbc_btnNouvellePartie);
		
		GridBagConstraints gbc_btnParametres = new GridBagConstraints();
		gbc_btnParametres.insets = new Insets(0, 0, 5, 0);
		gbc_btnParametres.gridx = 0;
		gbc_btnParametres.gridy = 3;
		btnParametres.addActionListener(this);
		this.getContentPane().add(btnParametres, gbc_btnParametres);
		
		GridBagConstraints gbc_btnRetour = new GridBagConstraints();
		gbc_btnRetour.insets = new Insets(0, 0, 5, 0);
		gbc_btnRetour.gridx = 0;
		gbc_btnRetour.gridy = 4;
		btnRetour.addActionListener(this);
		this.getContentPane().add(btnRetour, gbc_btnRetour);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e){

		Object source = e.getSource();
		
		if(source == btnMenuPrincipal){
			this.dispose();
			f.dispose();
			new MenuPrincipal();
		}
		if(source == btnNouvellePartie){
			this.dispose();
			if(f.container!=null){
				new Fenetre(f.container.joueur);
			}else{
				new Fenetre(f.containercpu.joueur);
			}
			f.dispose();
		}
		if(source == btnParametres){
			new Parametres(f,this);
		}
		if(source == btnRetour){
			this.dispose();
		}
	}
}
