import java.util.*;
import java.io.*;


public class Main {
	public static int N;
	public static int[] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	
    	if(N % 2 > 0) {
    		bw.write("0");
    		bw.flush();
    		bw.close();
    		return;
    	}
    	
    	dp = new int[N+1];
    	
    	dp[2] = 3;
    	
    	// 짝수만
    	for(int n = 4; n <= N; n += 2) {
    		dp[n] = dp[2] * dp[n-2];
    		for(int i = 2; i <= n - 4; i += 2) {
    			dp[n] += dp[i] * 2;
    		}
    		dp[n] += 2;
    	}
    	
    	bw.write(dp[N] + "");
    	bw.flush();
    	bw.close();
    	
    }
}