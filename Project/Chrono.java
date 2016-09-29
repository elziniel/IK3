import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Chrono {
	public int minute = 0, seconde = 0, minute2 = 0, seconde2 = 0, mili = 0, mili2 = 0, delais = 10;
	public int[] tab=new int[6];
	public JLabel Label1;
	public JPanel panel = new JPanel(new BorderLayout());
	public Timer timer1;
	public ActionListener tache_timer;
	public boolean retour, active = false, fin = false;
	
	public Chrono(){
		Label1 = new JLabel(minute+minute2+":"+seconde+seconde2+":"+mili+mili2);
		tache_timer = new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				mili2++;
				if(mili2==10){
					mili2=0;
					mili++;
				}
				if(mili==10){
					mili=0;
					seconde2++;
				}
				if(seconde2==10){
					seconde2=0;
					seconde++;
				}
				if(seconde==6){
					seconde=0;
					minute2++;
				}
				if(minute2==10){
					minute2=0;
					minute++;
				}
				tab[0]=minute;
				tab[1]=minute2;
				tab[2]=seconde;
				tab[3]=seconde2;
				tab[4]=mili;
				tab[5]=mili2;
				Label1.setText(""+minute+minute2+":"+seconde+seconde2+":"+mili+mili2);
			}
		};
	}
	
	public Chrono(int[] t, boolean b){
		for(int i=0; i<t.length;i++){
			tab[i]=t[i];
		}
		Label1=new JLabel(tab[0]+tab[1]+":"+tab[2]+tab[3]+":"+tab[4]+tab[5]);
		if(b){
			tache_timer = new ActionListener(){
				public void actionPerformed(ActionEvent e1){
					tab[5]++;
					if(tab[5]==10){
						tab[5]=0;
						tab[4]++;
					}
					if(tab[4]==10){
						tab[4]=0;
						tab[3]++;
					}
					if(tab[3]==10){
						tab[3]=0;
						tab[2]++;
					}
					if(tab[2]==6){
						tab[2]=0;
						tab[1]++;
					}
					if(tab[1]==10){
						tab[1]=0;
						tab[0]++;
					}
					Label1.setText(""+tab[0]+tab[1]+":"+tab[2]+tab[3]+":"+tab[4]+tab[5]);
					panel.add(Label1);
				}
			};
		}
		else{
			tache_timer = new ActionListener(){
				public void actionPerformed(ActionEvent e1){
					tab[5]--;
					if(tab[5]<0){
						tab[5]=9;
						tab[4]--;
					}
					if(tab[4]<0){
						tab[4]=9;
						tab[3]--;
					}
					if(tab[3]<0){
						tab[3]=9;
						tab[2]--;
					}
					if(tab[2]<0){
						tab[2]=6;
						tab[1]--;
					}
					if(tab[1]<0){
						tab[1]=9;
						tab[0]--;
					}
					Label1.setText(""+tab[0]+tab[1]+":"+tab[2]+tab[3]+":"+tab[4]+tab[5]);
					panel.add(Label1);
					if(tab[0]==0 && tab[1]==0 && tab[2]==0 && tab[3]==0 && tab[4]==0 && tab[5]==0){
						fin = true;
						timer1.stop();
					}
				}
			};
		}
	}
	
	public Chrono(int t){
		while(t-600 >= 0){
			tab[0]++;
			t -= 600;
		}
		while(t-60 >= 0){
			tab[1]++;
			t -= 60;
		}
		while(t-10 >= 0){
			tab[2]++;
			t -= 10;
		}
		tab[3] = t;
		Label1 = new JLabel(tab[0]+tab[1]+":"+tab[2]+tab[3]+":"+tab[4]+tab[5]);
		tache_timer = new ActionListener(){
			public void actionPerformed(ActionEvent e1){
				tab[5]--;
				if(tab[5]<0){
					tab[5]=9;
					tab[4]--;
				}
				if(tab[4]<0){
					tab[4]=9;
					tab[3]--;
				}
				if(tab[3]<0){
					tab[3]=9;
					tab[2]--;
				}
				if(tab[2]<0){
					tab[2]=5;
					tab[1]--;
				}
				if(tab[1]<0){
					tab[1]=9;
					tab[0]--;
				}
				Label1.setText(""+tab[0]+tab[1]+":"+tab[2]+tab[3]+":"+tab[4]+tab[5]);
				if(tab[0]==0 && tab[1]==0 && tab[2]==0 && tab[3]==0 && tab[4]==0 && tab[5]==0){
					fin = true;
					timer1.stop();
				}
			}
		};
	}
	
	public void Start(){
		timer1 = new Timer(delais, tache_timer);
		panel.add(Label1);
		timer1.start();
		active = true;
	}
	public void Stop(){
		timer1.stop();
	}

}