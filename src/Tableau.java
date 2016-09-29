import java.math.BigInteger;

public class Tableau {

	String symboles[] = {"0","1","2","3","4","5","6","7","8","9","10","11"};
	String combinaison = "";
	
	public Tableau(BigInteger n, int b, int t){
		String s = convertion(n, b);
		for(int i=0; i<t-s.length(); i++){
			this.combinaison += ""+'0';
		}
		this.combinaison += s;
	}

	public String convertion(BigInteger n, int b){
		return convertion(n, b, 0, "" );
	}
	
	public String convertion(BigInteger n, int b, int position, String resultat){
		if (n.compareTo(BigInteger.valueOf((long)(Math.pow(b, position + 1)))) == -1){
			n = n.divide(BigInteger.valueOf((long)(Math.pow(b, position))));
			int i = n.intValue();
			return symboles[i] + resultat;
	    }
		else{
			BigInteger[] n1 = n.divideAndRemainder(BigInteger.valueOf((long)(Math.pow(b, position+1))));
	    	int reste = n1[1].intValue();
	    	return convertion (n.add(BigInteger.valueOf((-reste))), b, position + 1, symboles[reste / (int)(Math.pow(b, position))] + resultat);
		}
	}
	
	public void copieTableau(String tableau, int[] copie){
		for(int i=0; i<tableau.length(); i++){
			copie[i] = (int)(tableau.charAt(i)-48);
		}
	}
}
