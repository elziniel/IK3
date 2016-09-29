import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class StatsSaver {
	public int tour, taille_code, couleurs, tourSelec, score, joueur, victoire, defaite, nombreparties;
	public boolean doublon, gagnant;
	public String chrono;
	
	public StatsSaver(Sauvegarde s, boolean gagnant){
		tour=s.tour;
		taille_code=s.taille_code;
		couleurs=s.couleurs;
		tourSelec=s.tourSelec;
		doublon=s.doublon;
		joueur=s.joueur;
		if(s.chrono!=null){	
			this.chrono=""+s.chrono[0]+s.chrono[1]+":"+s.chrono[2]+s.chrono[3]+":"+""+s.chrono[4]+s.chrono[5];
			score=s.score;
		}
		this.gagnant=gagnant;
	}
	
	public StatsSaver(){
		
	}
	
	public void Stats() throws JDOMException, IOException{
		Document doc;
		Element root=null;
		File Stats = new File("Stats.xml");
		
		if(Stats.exists()){
			boolean nontaille=true, taille_noncol=true, nonjoueur=true;
			FileInputStream fis=new FileInputStream(Stats);
			SAXBuilder sb=new SAXBuilder();
			doc=sb.build(fis);
			root=doc.getRootElement();
			Element Parties=root.getChild("Parties");
			List list = Parties.getChildren("Joueur");
			int i=0;
			Element pere=(Element) list.get(i);
			while(i<list.size()){
				pere=(Element) list.get(i);
				if(pere.getAttributeValue("joueur").equals(String.valueOf(joueur))){
					nonjoueur=false;
					i=list.size()-1;
				}
				i++;
			}
			i=0;
			List list2=pere.getChildren("Taille");
			Element fils=(Element) list2.get(i);
			while(i<list2.size()){
				fils=(Element) list2.get(i);
				if(fils.getAttributeValue("taille").equals(String.valueOf(taille_code))){
					nontaille=false;
					i=list.size()-1;
				}
				i++;
			}
			List list3=fils.getChildren("Couleurs");
			for(int j=0; j<list3.size(); j++){
				Element petitfils=(Element) list3.get(j);
				if(nontaille==false && petitfils.getAttributeValue("couleurs").equals(String.valueOf(couleurs))){
					taille_noncol=false;
				}
				if(taille_noncol==false && nontaille==false && nonjoueur==false){
					String sc=petitfils.getChildText("Score");
					String tS=petitfils.getChildText("NombreTours");
					if((joueur!=0 && joueur!=3 && joueur!=4 && joueur!=4) && Integer.parseInt(sc)<score){
						petitfils.getChild("Score").setText(String.valueOf(score));
					}	
					if(Integer.parseInt(tS)>tourSelec){
						petitfils.getChild("NombreTours").setText(String.valueOf(tourSelec+1));
					}
					if(petitfils.getChild("Chrono")!=null){
						String chr=petitfils.getChildText("Chrono");
						if(Character.getNumericValue(chr.charAt(0))>Character.getNumericValue(chrono.charAt(0)) || Character.getNumericValue(chr.charAt(1))>Character.getNumericValue(chrono.charAt(1)) || Character.getNumericValue(chr.charAt(3))>Character.getNumericValue(chrono.charAt(3)) || Character.getNumericValue(chr.charAt(4))>Character.getNumericValue(chrono.charAt(4)) || Character.getNumericValue(chr.charAt(6))>Character.getNumericValue(chrono.charAt(6)) || Character.getNumericValue(chr.charAt(7))>Character.getNumericValue(chrono.charAt(7))){
							petitfils.getChild("Chrono").setText(chrono);
						}
					}
					String nbr=petitfils.getChildText("NombreParties");
					petitfils.getChild("NombreParties").setText(String.valueOf(Integer.parseInt(nbr)+1));
					if(gagnant){
						String g=petitfils.getChildText("Victoire");
						petitfils.getChild("Victoire").setText(String.valueOf(Integer.parseInt(g)+1));
					}else{
						String p=petitfils.getChildText("Defaite");
						petitfils.getChild("Defaite").setText(String.valueOf(Integer.parseInt(p)+1));
					}
					break;
				}				
			}
			if(nonjoueur==true){
				Element j=new Element("Joueur");
				Element TC=new Element("Taille");
				Element Couleurs=new Element("Couleurs");
				Couleurs.setAttribute("couleurs",String.valueOf(couleurs));
				j.setAttribute("joueur", String.valueOf(joueur));
				TC.setAttribute("taille",String.valueOf(taille_code));
				Couleurs.addContent(new Element("NombreTours").setText(String.valueOf(tourSelec+1)));
				if(joueur!=0 && joueur!=3 && joueur!=4){
					Couleurs.addContent(new Element("Score").setText(String.valueOf(score)));
					Couleurs.addContent(new Element("Chrono").setText(chrono));
				}
				Couleurs.addContent(new Element("NombreParties").setText(String.valueOf(1)));
				if(gagnant){
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(1)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(0)));
				}else{
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(0)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(1)));
				}
				TC.addContent(Couleurs);
				j.addContent(TC);
				Parties.addContent(j);
			}
			
			if(nontaille==true && nonjoueur==false){
				Element TC=new Element("Taille");
				TC.setAttribute("taille",String.valueOf(taille_code));
				Element Couleurs=new Element("Couleurs");
				Couleurs.setAttribute("couleurs",String.valueOf(couleurs));
				Couleurs.addContent(new Element("NombreTours").setText(String.valueOf(tourSelec+1)));
				if(joueur!=0 && joueur!=3 && joueur!=4){
					Couleurs.addContent(new Element("Score").setText(String.valueOf(score)));
					Couleurs.addContent(new Element("Chrono").setText(chrono));
				}
				Couleurs.addContent(new Element("NombreParties").setText(String.valueOf(1)));
				if(gagnant){
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(1)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(0)));
				}else{
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(0)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(1)));
				}
				TC.addContent(Couleurs);
				pere.addContent(TC);
			}
			if(taille_noncol==true && nontaille==false && nonjoueur==false){
				Element Couleurs=new Element("Couleurs");
				Couleurs.setAttribute("couleurs",String.valueOf(couleurs));
				Couleurs.addContent(new Element("NombreTours").setText(String.valueOf(tourSelec+1)));
				if(joueur!=0 && joueur!=3 && joueur!=4){
					Couleurs.addContent(new Element("Score").setText(String.valueOf(score)));
					Couleurs.addContent(new Element("Chrono").setText(chrono));
				}
				Couleurs.addContent(new Element("NombreParties").setText(String.valueOf(1)));
				if(gagnant){
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(1)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(0)));
				}else{
					Couleurs.addContent(new Element("Victoire").setText(String.valueOf(0)));
					Couleurs.addContent(new Element("Defaite").setText(String.valueOf(1)));
				}
				fils.addContent(Couleurs);
			}
			
			fis.close();
		}else{
			doc=new Document();
			root=new Element("Mastermind");
			Element Parties=new Element("Parties");
			Element Joueur=new Element("Joueur");
			Element TC=new Element("Taille");
			Element Couleurs=new Element("Couleurs");
			Couleurs.setAttribute("couleurs",String.valueOf(couleurs));
			Joueur.setAttribute("joueur", String.valueOf(joueur));
			TC.setAttribute("taille",String.valueOf(taille_code));
			Couleurs.addContent(new Element("NombreTours").setText(String.valueOf(tourSelec+1)));
			if(joueur !=0 && joueur!=3){
				Couleurs.addContent(new Element("Score").setText(String.valueOf(score)));
				Couleurs.addContent(new Element("Chrono").setText(chrono));
			}
			Couleurs.addContent(new Element("NombreParties").setText(String.valueOf(1)));
			if(gagnant){
				Couleurs.addContent(new Element("Victoire").setText(String.valueOf(1)));
				Couleurs.addContent(new Element("Defaite").setText(String.valueOf(0)));
			}else{
				Couleurs.addContent(new Element("Victoire").setText(String.valueOf(0)));
				Couleurs.addContent(new Element("Defaite").setText(String.valueOf(1)));
			}
			TC.addContent(Couleurs);
			Joueur.addContent(TC);
			Parties.addContent(Joueur);
			root.addContent(Parties);
			doc.setContent(root);
		}
		
	     FileWriter writer = new FileWriter("Stats.xml");
	     XMLOutputter outputter = new XMLOutputter();
	     outputter.setFormat(Format.getPrettyFormat());
	     outputter.output(doc, writer);
	     writer.close();

	}
	
	public StatsSaver[] recup(int taille, int couleurs) throws JDOMException, IOException{
		StatsSaver[] s=new StatsSaver[5];
		StatsSaver j0=null, j1=null, j2=null, j3=null, j4=null;
		boolean c=false,t=false;
		SAXBuilder sb=new SAXBuilder();
		File Stats=new File("Stats.xml");
		Document doc=(Document) sb.build(Stats);
		Element root=doc.getRootElement(),Parties=root.getChild("Parties"), Joueur=Parties.getChild("Joueur");
		List l=Parties.getChildren("Joueur");
		for(int i=0; i<l.size(); i++){
			boolean fait=false;
			Element jo=(Element) l.get(i);
			List l2=jo.getChildren("Taille");
			int j=0;
			Element ta=null;
			while(j<l2.size()){
				ta=(Element) l2.get(j);
				if(ta.getAttributeValue("taille").equals(String.valueOf(taille))){
					j=l2.size()-1;
					c=true;
					
				}
				j++;
			}
			j=0;
			List l3=ta.getChildren("Couleurs");
			Element ca=null;
			while(j<l3.size()){
				ca=(Element) l3.get(j);
				if(ca.getAttributeValue("couleurs").equals(String.valueOf(couleurs))){
					j=l3.size()-1;
					t=true;
				}
				j++;
			}
			if(c==true && t==true){
				if(j0==null && fait==false){
					j0=new StatsSaver();
					j0.joueur=Integer.parseInt(jo.getAttributeValue("joueur"));
					j0.tourSelec=Integer.parseInt(ca.getChildText("NombreTours"));
					if(j0.joueur!=0 && j0.joueur!=3 && j0.joueur!=4){
						j0.score=Integer.parseInt(ca.getChildText("Score"));
						j0.chrono=ca.getChildText("Chrono");
					}
					j0.victoire=Integer.parseInt(ca.getChildText("Victoire"));
					j0.defaite=Integer.parseInt(ca.getChildText("Defaite"));
					j0.nombreparties=Integer.parseInt(ca.getChildText("NombreParties"));
					c=false;
					t=false;
					fait=true;
				}
				if(j0!=null && j1==null && fait==false){
					j1=new StatsSaver();
					j1.joueur=Integer.parseInt(jo.getAttributeValue("joueur"));
					j1.tourSelec=Integer.parseInt(ca.getChildText("NombreTours"));
					if(j1.joueur!=0 && j1.joueur!=3 && j1.joueur!=4){
						j1.score=Integer.parseInt(ca.getChildText("Score"));
						j1.chrono=ca.getChildText("Chrono");
					}
					j1.victoire=Integer.parseInt(ca.getChildText("Victoire"));
					j1.defaite=Integer.parseInt(ca.getChildText("Defaite"));
					j1.nombreparties=Integer.parseInt(ca.getChildText("NombreParties"));
					c=false;
					t=false;
					fait=true;
				}
				if(j0!=null && j1!=null && j2==null && fait==false){
					j2=new StatsSaver();
					j2.joueur=Integer.parseInt(jo.getAttributeValue("joueur"));
					j2.tourSelec=Integer.parseInt(ca.getChildText("NombreTours"));
					if(j2.joueur!=0 && j2.joueur!=3 && j2.joueur!=4){
						System.out.println(j2.joueur);
						j2.score=Integer.parseInt(ca.getChildText("Score"));
						j2.chrono=ca.getChildText("Chrono");
					}
					j2.victoire=Integer.parseInt(ca.getChildText("Victoire"));
					j2.defaite=Integer.parseInt(ca.getChildText("Defaite"));
					j2.nombreparties=Integer.parseInt(ca.getChildText("NombreParties"));
					c=false;
					t=false;
					fait=true;
				}
				if(j0!=null && j1!=null && j2!=null && j3==null && fait==false){
					j3=new StatsSaver();
					j3.joueur=Integer.parseInt(jo.getAttributeValue("joueur"));
					j3.tourSelec=Integer.parseInt(ca.getChildText("NombreTours"));
					if(j3.joueur!=0 && j3.joueur!=3 && j3.joueur!=4){
						j3.score=Integer.parseInt(ca.getChildText("Score"));
						j3.chrono=ca.getChildText("Chrono");
					}
					j3.victoire=Integer.parseInt(ca.getChildText("Victoire"));
					j3.defaite=Integer.parseInt(ca.getChildText("Defaite"));
					j3.nombreparties=Integer.parseInt(ca.getChildText("NombreParties"));
					c=false;
					t=false;
					fait=true;
				}
				if(j0!=null && j1!=null && j2!=null && j3!=null && j4==null && fait==false){
					j4=new StatsSaver();
					j4.joueur=Integer.parseInt(jo.getAttributeValue("joueur"));
					j4.tourSelec=Integer.parseInt(ca.getChildText("NombreTours"));
					if(j4.joueur!=0 && j4.joueur!=3 && j4.joueur!=4){
						j4.score=Integer.parseInt(ca.getChildText("Score"));
						j4.chrono=ca.getChildText("Chrono");
					}
					j4.victoire=Integer.parseInt(ca.getChildText("Victoire"));
					j4.defaite=Integer.parseInt(ca.getChildText("Defaite"));
					j4.nombreparties=Integer.parseInt(ca.getChildText("NombreParties"));
					c=false;
					t=false;
					fait=true;
				}

			}
		}
		s[0]=j0;
		s[1]=j1;
		s[2]=j2;
		s[3]=j3;
		s[4]=j4;
		return s;
	}
	
}
