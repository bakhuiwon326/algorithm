import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int m;
	public static int[] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	dp = new int[n + 1];
    	
    	dp[0] = 1;
    	dp[1] = 1;
    	
    	if(n >= 2) {
    		dp[2] = 2;    		
    	}
    
    	for(int i = 3; i <=n ; i++) {
    		dp[i] = dp[i - 1] + dp[i - 2];
    	}
    	
    	int answer = 1;
    	int lastVIP = 0;
    	for(int i = 0; i < m; i++) {
    		int idx = Integer.parseInt(br.readLine());
    		int cnt = idx - lastVIP - 1;
    		answer *= dp[cnt];
    		lastVIP = idx;
    	}
    	
    	answer *= dp[n - lastVIP];
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
}