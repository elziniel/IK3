import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.jdom2.JDOMException;

public class Stats extends JFrame implements ActionListener {
	public JFormattedTextField Taille;
	public DefaultTableModel model;
	public JComboBox comboBox;
	public JCheckBox checkBox, checkBox_1, chckbxCpu, chckbxCpuI, chckbxCpuVsJ;
	public JTable jt;
	public JButton btnValider = new JButton("Ok"),
			btnReset = new JButton("Reset");
	
	public Stats(){
		this.setTitle("Stats");
		this.setSize(600,250);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {100,100, 50, 50, 50, 50, 50, 50, 50};
		gridBagLayout.rowHeights = new int[] {100, 100,50};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		this.getContentPane().setLayout(gridBagLayout);
		JPanel choix=new JPanel();
		GridBagLayout gbl_choix = new GridBagLayout();
		gbl_choix.rowHeights = new int[] {40, 30, 30, 30, 30, 30,30,20};
		gbl_choix.columnWidths = new int[] {46,46, 46};
		choix.setLayout(gbl_choix);
		
		GridBagConstraints gbc_choix=new GridBagConstraints();
		gbc_choix.insets = new Insets(0, 10, 5, 5);
		gbc_choix.gridheight = 3;
		gbc_choix.gridwidth = 2;
		gbc_choix.fill = GridBagConstraints.BOTH;
		gbc_choix.gridx=0;
		gbc_choix.gridy=0;
		
		JLabel Joueur=new JLabel("Joueurs");
		GridBagConstraints gbc_joueur= new GridBagConstraints();
		gbc_joueur.fill = GridBagConstraints.BOTH;
		gbc_joueur.gridwidth = 2;
		gbc_joueur.insets = new Insets(0, 0, 5, 5);
		gbc_joueur.gridx=0;
		gbc_joueur.gridy=0;
		gbc_joueur.anchor=GridBagConstraints.FIRST_LINE_START;
		choix.add(Joueur,gbc_joueur);
		
		this.getContentPane().add(choix, gbc_choix);
		
		checkBox = new JCheckBox("1");
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.anchor = GridBagConstraints.WEST;
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 1;
		choix.add(checkBox, gbc_checkBox);
		
		checkBox_1 = new JCheckBox("2");
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_1.gridx = 1;
		gbc_checkBox_1.gridy = 1;
		choix.add(checkBox_1, gbc_checkBox_1);
		
		chckbxCpu = new JCheckBox("CPU M");
		GridBagConstraints gbc_chckbxCpu = new GridBagConstraints();
		gbc_chckbxCpu.fill = GridBagConstraints.BOTH;
		gbc_chckbxCpu.anchor = GridBagConstraints.WEST;
		gbc_chckbxCpu.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxCpu.gridx = 2;
		gbc_chckbxCpu.gridy = 1;
		choix.add(chckbxCpu, gbc_chckbxCpu);
		
		String[] s={"Joueur", "Score", "Tours", "Chrono","Parties","Victoire", "Defaite"};
		Object[][] ob=new Object[3][7];
		model=new DefaultTableModel(ob,s);
		jt=new JTable(model);
		jt.setShowVerticalLines(false);
		jt.setShowHorizontalLines(false);
		JScrollPane stats=new JScrollPane(jt);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 7;
		gbc.gridheight = 3;
		gbc.gridx=2;
		gbc.gridy=0;
		this.getContentPane().add(stats, gbc);
		
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);

		btnValider.addActionListener(this);
		
		chckbxCpuI = new JCheckBox("CPU I");
		GridBagConstraints gbc_chckbxCpuI = new GridBagConstraints();
		gbc_chckbxCpuI.anchor = GridBagConstraints.WEST;
		gbc_chckbxCpuI.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCpuI.gridx = 0;
		gbc_chckbxCpuI.gridy = 2;
		choix.add(chckbxCpuI, gbc_chckbxCpuI);
		
		chckbxCpuVsJ = new JCheckBox("CPU vs J.");
		GridBagConstraints gbc_chckbxCpuVsJ = new GridBagConstraints();
		gbc_chckbxCpuVsJ.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxCpuVsJ.gridx = 2;
		gbc_chckbxCpuVsJ.gridy = 2;
		choix.add(chckbxCpuVsJ, gbc_chckbxCpuVsJ);
		
		JLabel lblCouleurs = new JLabel("Couleurs");
		GridBagConstraints gbc_lblCouleurs = new GridBagConstraints();
		gbc_lblCouleurs.fill = GridBagConstraints.BOTH;
		gbc_lblCouleurs.anchor = GridBagConstraints.WEST;
		gbc_lblCouleurs.gridwidth = 2;
		gbc_lblCouleurs.insets = new Insets(0, 0, 5, 5);
		gbc_lblCouleurs.gridx = 0;
		gbc_lblCouleurs.gridy = 3;
		choix.add(lblCouleurs, gbc_lblCouleurs);
		
		comboBox = new JComboBox();
		for(int i=2; i<13; i++){
			comboBox.addItem(i);
		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 4;
		choix.add(comboBox, gbc_comboBox);
		
		JLabel lblTaille = new JLabel("Taille");
		GridBagConstraints gbc_lblTaille = new GridBagConstraints();
		gbc_lblTaille.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaille.gridx = 0;
		gbc_lblTaille.gridy = 5;
		choix.add(lblTaille, gbc_lblTaille);
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 5, 0);
		gbc_btnValider.gridx = 2;
		gbc_btnValider.gridy = 5;
		choix.add(btnValider, gbc_btnValider);
		
		btnReset.addActionListener(this);
		Taille=new JFormattedTextField(formatter);
		GridBagConstraints gbc_Taille = new GridBagConstraints();
		gbc_Taille.insets = new Insets(0, 0, 5, 5);
		gbc_Taille.fill = GridBagConstraints.BOTH;
		gbc_Taille.gridx=0;
		gbc_Taille.gridy=6;
		choix.add(Taille, gbc_Taille);
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 0);
		gbc_btnReset.gridx = 2;
		gbc_btnReset.gridy = 6;
		choix.add(btnReset, gbc_btnReset);
		model.setRowCount(0);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();
		
		if(source == btnValider){
			int taille=Integer.parseInt(Taille.getText());
			int couleurs=Integer.parseInt(comboBox.getSelectedItem().toString());
			boolean j0=chckbxCpu.isSelected(), j1=checkBox.isSelected(),j2=checkBox_1.isSelected(), j3=chckbxCpuI.isSelected(), j4=chckbxCpuVsJ.isSelected();
			StatsSaver ss=new StatsSaver();
			try {
				StatsSaver[] tss=ss.recup(taille, couleurs);
				Object[][] data=new Object[tss.length][7];
				for(int i=0; i<tss.length; i++){						
					if(tss[i]!=null && ((j0 && tss[i].joueur==0)||(j1 && tss[i].joueur==1)||(j2 && tss[i].joueur==2) ||(j3 && tss[i].joueur==3) ||(j4 && tss[i].joueur==4))){	
							if(tss[i].joueur==0){	
								data[i][0]="CPU M";
							}
							if(tss[i].joueur==3){
								data[i][0]="CPU I";
							}
							if(tss[i].joueur==4){
								data[i][0]="CPU vs J.";
							}
							if(tss[i].joueur!=0 && tss[i].joueur!=3 && tss[i].joueur!=4){
								data[i][0]=tss[i].joueur;
								data[i][1]=tss[i].score;
								data[i][3]=tss[i].chrono;
							}	
							data[i][2]=tss[i].tourSelec;
							data[i][4]=tss[i].nombreparties;	
							data[i][5]=tss[i].victoire;
							data[i][6]=tss[i].defaite;
							model.addRow(data[i]);
					}
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"Vous n'avez pas encore joue de parties, revenez apres!","Pas de stats",JOptionPane.ERROR_MESSAGE);				
			}
		}else{
			model.setRowCount(0);
		}
	}
}
