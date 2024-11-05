import java.util.*;
import java.io.*;

public class Main {
	
	public static int n;
	public static int m;
	public static boolean[] isSmall;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// isSmall
    	isSmall = new boolean[n + 1];
    	for(int i = 0; i < m; i++) {
    		int idx = Integer.parseInt(br.readLine());
    		isSmall[idx] = true;
    	}
    	
    	// dp
    	int maxJmp = (int)Math.sqrt(2 * n);
    	int[][] dp = new int[n + 1][maxJmp + 1];
    	
    	// 초기화
    	for(int i = 1; i <= n; i++) {
    		for(int j = 0; j <= maxJmp; j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    		}
    	}
    	
    	dp[2][1] = 1;
    	
    	for(int pos = 1; pos < n; pos++) {
    		if(isSmall[pos]) continue;
    		for(int prevJmp = 1; prevJmp <= maxJmp; prevJmp++) {
    			if(dp[pos][prevJmp] == Integer.MAX_VALUE) continue;
    			for(int jmp = prevJmp - 1; jmp <= prevJmp + 1; jmp++) {
    				int next = pos + jmp;
    				if(next > n || jmp <= 0 || jmp > maxJmp|| isSmall[next]) continue;
    				dp[next][jmp] = Math.min(dp[pos][prevJmp] + 1, dp[next][jmp]);
    			}
    		}
    	}
    	
    	int answer = Integer.MAX_VALUE;
    	for(int i = 1; i <= maxJmp; i++) {
    		answer = Math.min(dp[n][i], answer);
    	}
    	
    	if(answer == Integer.MAX_VALUE) {
    		answer = -1;
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    }
}
