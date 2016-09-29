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

public class MenuPrincipal extends JFrame implements ActionListener{
	
	public Fenetre f = new Fenetre();
	public JButton btnJouer=new JButton("Jouer"),
			btnMode = new JButton("Changer de mode"),
			btnJoueur = new JButton("1 Joueur"),
			btnJoueurs = new JButton("2 Joueurs"),
			btnCPUj=new JButton("CPU vs. Joueur"),
			btnOrdinateurF = new JButton("Ordinateur malingre"),
			btnOrdinateurD = new JButton("Ordinateur intelligent"),
			btnQuitter = new JButton("Quitter"),
			btnNouvellePartie = new JButton("Nouvelle Partie"),
			btnStats = new JButton("Stats"),
			btnParametres = new JButton("Parametres"),
			btnRetour = new JButton("Retour");
	public MenuPrincipal mp;
	
	public MenuPrincipal(){
		
		this.setTitle("Demarrage");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(190,250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190};
		gridBagLayout.rowHeights = new int[] {40, 40, 40, 40 ,40};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		JLabel lblNewLabel = new JLabel("Mastermind");
		lblNewLabel.setFont(new Font("", Font.BOLD, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		this.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		btnJouer.addActionListener(this);
		GridBagConstraints gbc_btnJouer = new GridBagConstraints();
		gbc_btnJouer.insets = new Insets(0, 0, 5, 0);
		gbc_btnJouer.gridx = 0;
		gbc_btnJouer.gridy = 1;
		this.getContentPane().add(btnJouer, gbc_btnJouer);
		
		btnQuitter.addActionListener(this);
		
		btnParametres.addActionListener(this);
		
		btnStats.addActionListener(this);
		
		GridBagConstraints gbc_btnStats = new GridBagConstraints();
		gbc_btnStats.insets = new Insets(0, 0, 5, 0);
		gbc_btnStats.gridx = 0;
		gbc_btnStats.gridy = 2;
		this.getContentPane().add(btnStats, gbc_btnStats);
		
		GridBagConstraints gbc_btnParametres = new GridBagConstraints();
		gbc_btnParametres.insets = new Insets(0, 0, 5, 0);
		gbc_btnParametres.gridx = 0;
		gbc_btnParametres.gridy = 3;
		this.getContentPane().add(btnParametres, gbc_btnParametres);
		
		GridBagConstraints gbc_btnQuitter = new GridBagConstraints();
		gbc_btnQuitter.gridx = 0;
		gbc_btnQuitter.gridy = 4;
		this.getContentPane().add(btnQuitter, gbc_btnQuitter);
		
		this.setVisible(true);
	}
	
	public MenuPrincipal(MenuPrincipal m){
		
		this.mp = m;
		this.setTitle("Mode de jeu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(190,325);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190};
		gridBagLayout.rowHeights = new int[] {40, 40, 40, 40, 40, 40, 40};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		JLabel lblNewLabel = new JLabel("Mode de jeu");
		lblNewLabel.setFont(new Font("", Font.BOLD, 24));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		this.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
			
		btnJoueur.addActionListener(this);
		GridBagConstraints gbc_btnJoueur = new GridBagConstraints();
		gbc_btnJoueur.insets = new Insets(0, 0, 5, 0);
		gbc_btnJoueur.gridx = 0;
		gbc_btnJoueur.gridy = 1;
		this.getContentPane().add(btnJoueur, gbc_btnJoueur);
			
		btnJoueurs.addActionListener(this);
		GridBagConstraints gbc_btnJoueurs = new GridBagConstraints();
		gbc_btnJoueurs.insets = new Insets(0, 0, 5, 0);
		gbc_btnJoueurs.gridx = 0;
		gbc_btnJoueurs.gridy = 2;
		this.getContentPane().add(btnJoueurs, gbc_btnJoueurs);
			
		btnCPUj.addActionListener(this);
		GridBagConstraints gbc_btnCPUj = new GridBagConstraints();
		gbc_btnCPUj.insets = new Insets(0, 0, 5, 0);
		gbc_btnCPUj.gridx = 0;
		gbc_btnCPUj.gridy = 3;
		this.getContentPane().add(btnCPUj, gbc_btnCPUj);
	
		btnOrdinateurF.addActionListener(this);
		GridBagConstraints gbc_btnOrdinateurF = new GridBagConstraints();
		gbc_btnOrdinateurF.insets = new Insets(0, 0, 5, 0);
		gbc_btnOrdinateurF.gridx = 0;
		gbc_btnOrdinateurF.gridy = 4;
		this.getContentPane().add(btnOrdinateurF, gbc_btnOrdinateurF);
	
		btnOrdinateurD.addActionListener(this);
		GridBagConstraints gbc_btnOrdinateurD = new GridBagConstraints();
		gbc_btnOrdinateurD.insets = new Insets(0, 0, 5, 0);
		gbc_btnOrdinateurD.gridx = 0;
		gbc_btnOrdinateurD.gridy = 5;
		this.getContentPane().add(btnOrdinateurD, gbc_btnOrdinateurD);
		
		GridBagConstraints gbc_btnRetour = new GridBagConstraints();
		gbc_btnRetour.insets = new Insets(0, 0, 5, 0);
		gbc_btnRetour.gridx = 0;
		gbc_btnRetour.gridy = 6;
		btnRetour.addActionListener(this);
		this.getContentPane().add(btnRetour, gbc_btnRetour);
				
		this.setVisible(true);
	}
	
	public MenuPrincipal(Fenetre f){
		
		this.f = f;
		this.setTitle("Menu");
		this.setSize(190,300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190, 0};
		gridBagLayout.rowHeights = new int[]{40,40,40,40,40,40};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0};
		this.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		JLabel menu=new JLabel("Menu");
		menu.setFont(new Font("", Font.BOLD, 24));
		panel.add(menu);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.getContentPane().add(panel, gbc_panel);
		
		GridBagConstraints gbc_btnMode = new GridBagConstraints();
		gbc_btnMode.insets = new Insets(0, 0, 5, 0);
		gbc_btnMode.gridx = 0;
		gbc_btnMode.gridy = 1;
		btnMode.addActionListener(this);
		this.getContentPane().add(btnMode, gbc_btnMode);
		
		GridBagConstraints gbc_btnNouvellePartie = new GridBagConstraints();
		gbc_btnNouvellePartie.insets = new Insets(0, 0, 5, 0);
		gbc_btnNouvellePartie.gridx = 0;
		gbc_btnNouvellePartie.gridy = 2;
		btnNouvellePartie.addActionListener(this);
		this.getContentPane().add(btnNouvellePartie, gbc_btnNouvellePartie);
		
		GridBagConstraints gbc_btnStats = new GridBagConstraints();
		gbc_btnStats.insets = new Insets(0, 0, 5, 0);
		gbc_btnStats.gridx = 0;
		gbc_btnStats.gridy = 3;
		btnStats.addActionListener(this);
		this.getContentPane().add(btnStats, gbc_btnStats);
		
		GridBagConstraints gbc_btnParametres = new GridBagConstraints();
		gbc_btnParametres.insets = new Insets(0, 0, 5, 0);
		gbc_btnParametres.gridx = 0;
		gbc_btnParametres.gridy = 4;
		btnParametres.addActionListener(this);
		this.getContentPane().add(btnParametres, gbc_btnParametres);
		
		GridBagConstraints gbc_btnRetour = new GridBagConstraints();
		gbc_btnRetour.insets = new Insets(0, 0, 5, 0);
		gbc_btnRetour.gridx = 0;
		gbc_btnRetour.gridy = 5;
		btnRetour.addActionListener(this);
		this.getContentPane().add(btnRetour, gbc_btnRetour);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		Object source = e.getSource();
		
		if(source == btnJouer || source == btnMode){
			new MenuPrincipal(this);
		}

		if(source == btnJoueur){
			if(f.container!=null || f.containercpu!=null){
				mp.f.dispose();
			}
			this.dispose();
			mp.dispose();
			new Fenetre(1);
		}
		if(source == btnJoueurs){
			if(f.container!=null || f.containercpu!=null){
				mp.f.dispose();
			}
			this.dispose();
			mp.dispose();
			new Fenetre(2);
		}
		if(source == btnCPUj){
			if(f.container!=null || f.containercpu!=null){
				mp.f.dispose();
			}
			this.dispose();
			mp.dispose();
			new Fenetre(4);
		}
		if(source == btnOrdinateurF){
			if(f.container!=null || f.containercpu!=null){
				mp.f.dispose();
			}
			this.dispose();
			mp.dispose();
			new Fenetre(0);
		}
		if(source == btnOrdinateurD){
			if(f.container!=null || f.containercpu!=null){
				mp.f.dispose();
			}
			this.dispose();
			mp.dispose();
			new Fenetre(3);
		}
		if(source == btnStats){
			new Stats();
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
			new Parametres(f, this);
		}
		
		if(source == btnRetour){
			if(f.container!=null && f.container.mp.fini==false && f.container.mp.start==true){
				f.Reprise();
				f.container.mp.pause=false;
			}
			this.dispose();
		}
		
		if(source == btnQuitter){
			System.exit(0);
		}
	}
}
