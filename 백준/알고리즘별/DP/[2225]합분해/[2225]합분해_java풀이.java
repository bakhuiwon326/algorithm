import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int k;
	public static final int mod = 1000000000;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, k
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	// dp[횟수][합]
    	int[][] dp = new int[k + 1][n + 1];
    	
    	
    	// 초기화
    	for(int i = 0; i <= n; i++) {
    		dp[0][i] = 0;
    		dp[1][i] = 1;
    	}
    	for(int i = 0; i <= k; i++) {
    		dp[i][0] = 1;
    	}
    	
    	for(int i = 2; i <= k ; i++) {
    		for(int j = 1; j <= n; j++) {
    			dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % mod;
    		}
    	}
    	
    	bw.write(dp[k][n] + "");
    	bw.flush();
    	bw.close();
    	
    }
}