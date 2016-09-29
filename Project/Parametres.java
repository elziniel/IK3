import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Parametres extends JFrame implements ActionListener{

	public Fenetre fen;
	public JFrame jf;
	public File f;
	public JButton btnCharger = new JButton("Charger une partie"),
			btnOptions = new JButton("Options"),
			btnCouleur = new JButton("Couleur"),
			btnRetour = new JButton("Retour"),
			tmp=new JButton();
	
	public Parametres(Fenetre f, JFrame jf){
		this.fen = f;
		this.jf = jf;
		this.setTitle("Parametres");
		this.setSize(190,260);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {190, 0};
		gridBagLayout.rowHeights = new int[]{40,40,40,40,40};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0};
		this.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		JLabel menu=new JLabel("Parametres");
		menu.setFont(new Font("", Font.BOLD, 24));
		panel.add(menu);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.getContentPane().add(panel, gbc_panel);
		
		GridBagConstraints gbc_btnCharger = new GridBagConstraints();
		gbc_btnCharger.insets = new Insets(0, 0, 5, 0);
		gbc_btnCharger.gridx = 0;
		gbc_btnCharger.gridy = 1;
		btnCharger.addActionListener(this);
		this.getContentPane().add(btnCharger, gbc_btnCharger);
		
		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.insets = new Insets(0, 0, 5, 0);
		gbc_btnOptions.gridx = 0;
		gbc_btnOptions.gridy = 2;
		btnOptions.addActionListener(this);
		this.getContentPane().add(btnOptions, gbc_btnOptions);
		
		GridBagConstraints gbc_btnCouleur = new GridBagConstraints();
		gbc_btnCouleur.gridx = 0;
		gbc_btnCouleur.gridy = 3;
		btnCouleur.addActionListener(this);
		this.getContentPane().add(btnCouleur, gbc_btnCouleur);
		
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
		
		if(source == btnCharger){
			JFrame charge=new JFrame();
			JFileChooser jfc=new JFileChooser(".");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Sauvegarde", "ser", "ser");
			jfc.setFileFilter(filter);
			charge.setTitle("Charger");
			charge.setSize(400, 400);
			charge.setResizable(false);
			charge.setLocationRelativeTo(null);
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.rowWeights = new double[]{1.0, 0.0};
			gridBagLayout.columnWeights = new double[]{1.0};
			gridBagLayout.columnWidths = new int[]{200};
			gridBagLayout.rowHeights = new int[]{200,100};
			charge.getContentPane().setLayout(gridBagLayout);
			GridBagConstraints gbc_jfc = new GridBagConstraints();
			gbc_jfc.fill = GridBagConstraints.BOTH;
			gbc_jfc.gridx = 0;
			gbc_jfc.gridheight = 2;
			gbc_jfc.gridy = 0;
			charge.getContentPane().add(jfc, gbc_jfc);
			charge.setVisible(false);
			if(jfc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
				f=jfc.getSelectedFile();
				Sauvegarde s=new Sauvegarde();
				s=s.Deserializable(f.getName());
				if(fen!=null){
					fen.dispose();
				}
				charge.dispose();
				this.dispose();
				jf.dispose();
				Fenetre f=new Fenetre(s);
				tmp.addActionListener(new OkListener(f.container,f));
				tmp.doClick();
			}
		}
		if(source == btnOptions){
			new Options(this);
		}
		if(source == btnCouleur){
			new RGB(fen,this);
		}
		if(source == btnRetour){
			this.dispose();
		}
	}
}
