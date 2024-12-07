import java.util.*;
import java.io.*;


public class Main {
	
	public static int n;
	public static int[] T;
	public static int[] P;
	public static int[] dp;
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	n = Integer.parseInt(br.readLine());
    	
    	// T, P
    	T = new int[n + 1];
    	P = new int[n + 1];
    	
    	for(int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		T[i] = Integer.parseInt(st.nextToken());
    		P[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// dp
    	dp = new int[n + 1];
    	for(int i = 1; i <= n; i++) {
    		int next = i + T[i] - 1;
    		if(next > n) {
    			dp[i] = Math.max(dp[i], dp[i - 1]);
    			continue;
    		}
    		// 오늘자 상담 실행 O
    		dp[next] = Math.max(dp[next], dp[i - 1] + P[i]);
    		// 오늘자 상담 실행 X
    		dp[i] = Math.max(dp[i - 1], dp[i]);
    	}
    	
    	bw.write(dp[n] + "");
    	bw.flush();
    	bw.close();
    	
    }
}