import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int answer;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	// n
    	n = Integer.parseInt(br.readLine());
    	
    	backtracking(0, "", bw);
    	
    	bw.flush();
    	bw.close();
    }
    
    public static void backtracking(int depth, String numStr, BufferedWriter bw) throws IOException{
    	
    	if(depth == n) {
    		if(isPrime(Integer.parseInt(numStr))) {
    			bw.write(numStr);
    			bw.newLine();    			
    		}
    		return;
    	}
    	
    	if(numStr.length() > 0 && !isPrime(Integer.parseInt(numStr))) return;
    	
    	for(int i = 1; i <= 9; i++) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(numStr);
    		sb.append(i);
    		backtracking(depth + 1, sb.toString(), bw);
    	}
    }
    
    public static boolean isPrime(int number) {
    	if(number < 2) return false;
    	for(int i = 2; i <= Math.sqrt(number); i++) {
    		if(number % i == 0) return false;
    	}
    	return true;
    }
}