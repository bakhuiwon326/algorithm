import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int absN;
	public static int[] dp = new int[1000001];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	absN = Math.abs(n);
    	
    	dp[0] = 0;
    	dp[1] = 1;
    	
    	if(n > 0) {
    		for(int i = 2; i <= absN; i++) {
    			dp[i] = (dp[i-1] + dp[i-2]) % 1000000000;
    		}
    	}
    	else if(n < 0) {
    		for(int i = 2; i <= absN; i++){
    			dp[i] = (dp[i-2] - dp[i-1]) % 1000000000;
    		}
    	}
    	
    	int answer = 0;
    	if(dp[absN] > 0) answer = 1;
    	else if(dp[absN] < 0) answer = -1;
    	
    	bw.write(answer + "");
    	bw.newLine();
    	bw.write(Math.abs(dp[absN]) + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
}