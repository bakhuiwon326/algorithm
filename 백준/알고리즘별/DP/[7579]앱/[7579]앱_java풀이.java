import java.util.*;
import java.io.*;

public class Main {
	public static int N, M;
	public static int[] m;
	public static int[] c;
	public static int[][] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	
    	m = new int[N];
    	c = new int[N];
    	dp = new int[N][10001];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		m[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < N; i++) {
    		c[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	
    	for(int i = 0; i <= 10000; i++) {
    		if(i >= c[0]) dp[0][i] = m[0];
    	}
    	
    	for(int i = 1; i< N; i++) {
    		for(int j = 0; j <= 10000; j++) {
    			int cost = c[i];
    			int memory = m[i];
    			if(j >= cost) {
    				dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost] + memory);
    			}
    			else {
    				dp[i][j] = dp[i-1][j];
    			}
    		}
    	}
    	
    	int answer = Integer.MAX_VALUE;
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j <= 10000; j++) {
    			if(M <= dp[i][j]) {
    				answer = Math.min(answer, j);
    			}
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}