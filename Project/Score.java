import javax.swing.JLabel;
import javax.swing.JPanel;
import java.lang.Math;

public class Score{
	public int score = 0, delai = 1000, calc, s;
	public JLabel label; 
	public JPanel panel = new JPanel();
	
	public Score(){
		label = new JLabel(""+score);
	}
	
	public Score(int sc){
		score=sc;
		label = new JLabel(""+score);
	}
	
	public void Start(){
		panel.add(label);
	}
	
	public void tour(MindZ m){
		for(int i=0; i<m.taille_code; i++){
			if(m.s.charAt(i) == '+'){
				score += calc*Math.pow(Math.E, -s*(4./5));
			}
			else if(m.s.charAt(i) == '*'){
				score += calc*Math.pow(Math.E, -s*(5./6));
			}
		}
		label.setText(""+score);
	}
	
	public void Victoire(MindZ m){
		score+=(int)(m.mp.taille_code*Math.pow(Math.E, m.mp.tour-m.mp.tourSelec));
		label.setText(""+score);
	}
}