import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Fenetre extends JFrame implements ActionListener, MouseListener{

	public Sauvegarde s;
	public File f;
	public Panneau pan;
	public MindZ container;
	public MindCPU containercpu;
	public JScrollPane code;
	public JButton btnOk = new JButton("Ok"),
			btnMenu = new JButton("Menu"),
			btnQuitter=new JButton("Quitter"),
			btnSave = new JButton("Sauvegarde"),
			btnCharge = new JButton("Charger"),
			tmp=new JButton();
	public OkListener o;
	public int i;
	
	public Fenetre(Sauvegarde s){
		this.container=new MindZ(s);
		this.i=1;
		this.pan= new Panneau(this);
		setFenetre();
	}
	
	public Fenetre(int i){
		this.i=i;
		if(i==1 || i==2){	
			this.container=new MindZ(i);
			this.pan= new Panneau(this);
			setFenetre();
		}else{
			o=new OkListener(container,this);
			this.containercpu=new MindCPU(this, i);
			this.pan=new Panneau(this);
			setFenetre();
		}
	}
	
	public Fenetre(){
		this.container=new MindZ(1);
	}
	
	public void setFenetre(){
		if(i==1 || i==2){
			code = pan.setContainerJeu(container);
		}else{
			code = pan.setContainerJeu(containercpu);
		}
		this.setTitle("Mastermind");
		this.setSize(650,490);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		addMouseListener(this);
		getContentPane().setLayout(new GridLayout());
		getContentPane().add(pan);
		GridBagLayout gbl_pan = new GridBagLayout();
		gbl_pan.columnWidths = new int[]{123, 130, 130, 130, 39, 73};
		gbl_pan.rowHeights = new int[] {118, 98, 109, 98, 93};
		gbl_pan.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_pan.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		pan.setLayout(gbl_pan);
		if(container!=null){
			GridBagConstraints gbc_chrono=new GridBagConstraints();
			gbc_chrono.gridx=0;
			gbc_chrono.gridy=0;
			gbc_chrono.anchor = GridBagConstraints.LINE_START;
			gbc_chrono.insets=new Insets(30,25,0,0);
			pan.add(container.chrono,gbc_chrono);
			GridBagConstraints gbc_score=new GridBagConstraints();
			gbc_score.gridx = 0;
			gbc_score.gridy = 1;
			gbc_score.anchor = GridBagConstraints.FIRST_LINE_START;
			gbc_score.insets = new Insets(30,33,0,0);
			pan.add(container.score,gbc_score);
			btnOk.addActionListener(new OkListener(container,this));
		}else{
			btnOk.addActionListener(o);	
		}
		GridBagConstraints gbc_code = new GridBagConstraints();
		if((container !=null && container.taille_code<5) || (containercpu!=null && containercpu.taille_code<5)){
			gbc_code.gridheight = 2;
		}else{
			gbc_code.gridheight = 3;
		}
		gbc_code.gridwidth = 5;
		gbc_code.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_code.fill = GridBagConstraints.BOTH;
		gbc_code.gridx = 1;
		gbc_code.gridy = 1;
		code.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pan.add(code, gbc_code);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridy = 2;
		gbc.gridx = 0;
		gbc.gridheight=3;
		
		JPanel boutons=new JPanel();
		btnMenu.addActionListener(this);
		boutons.setLayout(new GridLayout(0, 1, 0, 0));
		boutons.add(btnMenu);
		
		
		GridBagConstraints gbc2=new GridBagConstraints();
		gbc2.insets = new Insets(10, 0, 0, 0);
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		if((container !=null && container.taille_code<5) || (containercpu!=null && containercpu.taille_code<5)){
			gbc2.gridy = 3;
		}else{
			gbc2.gridy = 4;
		}
		gbc2.gridx = 1;
		pan.add(btnOk,gbc2);
		
		btnSave.addActionListener(this);
		boutons.add(btnSave);
		
		btnCharge.addActionListener(this);
		boutons.add(btnCharge);	
		pan.add(boutons,gbc);
		
		btnQuitter.addActionListener(this);
		boutons.add(btnQuitter);
		this.setVisible(true);
	}
	
	public void mousePressed(MouseEvent e){
		int x = e.getX(), y = e.getY();
		if(this.container!=null){
			if(x > 278 && x < 278+30*this.container.couleurs && ((container.taille_code<5 && y > 335 && y < 365) || (container.taille_code>=5 && y > 435 && y < 465))){
				container.mp.setCouleurSelec((x-281)/30);
				pan.couleurS(container.mp.couleurSelec);
				pan.repaint();
			}
		}
		if(i==4 && containercpu.mp.choixcouleurs==true){
			if(x > 278 && x < 278+30*this.containercpu.couleurs && ((containercpu.taille_code<5 && y > 335 && y < 365) || (containercpu.taille_code>=5 && y > 435 && y < 465))){
				containercpu.mp.setCouleurSelec((x-281)/30);
				pan.couleurS(containercpu.mp.couleurSelec);
				pan.repaint();
			}
		}
	}
	
	public void Reprise(){
		int[] tab=container.mp.c.tab;
		container.mp.chrono.removeAll();
		Chrono ch=new Chrono(tab,true);
		container.mp.chrono.add(ch.panel);
		container.mp.c.Start();
		container.mp.chrono.repaint();
		int s=container.mp.sc.score;
		container.mp.score.removeAll();
		Score sc=new Score(s);
		container.mp.score.add(sc.panel);
		container.mp.sc.Start();
		container.mp.score.repaint();
		repaint();
	}
	
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }

	public void actionPerformed(ActionEvent e){
		
		Object source = e.getSource();
		
		if(source == btnMenu){
			if(container!=null && container.mp.start){
				container.mp.c.Stop();
				container.mp.pause=true;
			}
			new MenuPrincipal(this);
		}
		if(source == btnQuitter){
			System.exit(0);
		}
		if(source == btnSave){
			s = new Sauvegarde(container);
			Date d=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH-mm");
			String date=sdf.format(d);
			try{	   
				FileOutputStream fout = new FileOutputStream("Sauvegarde du "+date+".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fout);   
				oos.writeObject(s);
				fout.close();
				oos.close();
			}catch(Exception ex){
			}
		}
		if(source == btnCharge){
			if(container!=null && container.mp.start){
				container.c.Stop();
				container.mp.pause=true;
			}
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
				this.dispose();
				new Fenetre(s);
				if(btnOk.getText().equals("Ok")){
					tmp.addActionListener(new OkListener(container,this));
					tmp.doClick();
				}
				charge.dispose();
			}else{
				if(container.mp.fini==false && container.mp.start){
					Reprise();
					container.mp.pause=false;
				}
			}
		}
	}
}
