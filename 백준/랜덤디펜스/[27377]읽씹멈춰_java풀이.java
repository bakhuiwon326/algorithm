import java.util.*;
import java.io.*;
import java.math.BigInteger;


public class Main {
	public static int T;
	public static BigInteger n;
	public static BigInteger s, t;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	T = Integer.parseInt(br.readLine());
    	
    	while(T-- > 0) {
    		
    		n = new BigInteger(br.readLine());    		
    		st = new StringTokenizer(br.readLine());
    		s = new BigInteger(st.nextToken());
    		t = new BigInteger(st.nextToken());
    		
    		BigInteger answer = BigInteger.ZERO;
    		    		
    		while(n.compareTo(BigInteger.ZERO) > 0) {
    			if(n.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
    				n = n.subtract(BigInteger.ONE);
    				answer = answer.add(s);
    			}
    			else {
    				n = n.divide(BigInteger.TWO);
    				answer = answer.add(n.multiply(s).min(t));
    			}
    		}
    		
    		bw.write(answer.toString() + "");
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
}