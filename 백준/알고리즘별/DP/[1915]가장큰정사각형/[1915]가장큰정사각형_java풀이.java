import java.util.*;
import java.io.*;

class Info{
	int r; int c; int len;
	public Info(int r, int c, int len) {
		this.r = r;
		this.c = c;
		this.len = len;
	}
}

public class Main {
	public static int n, m;
	public static int[][] map;
	public static int[][] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	// n, m
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// map
    	map = new int[n+1][m+1];
    	for(int i = 1; i <= n; i++) {
    		String str = br.readLine();
    		for(int j = 1; j <= m; j++) {
    			map[i][j] = str.charAt(j-1) - '0';
    		}
    	}
    	
    	// dp
    	dp = new int[n+1][m+1];
    	int maxLen = 0;
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= m; j++) {
    			if(map[i][j] == 1) {
    				dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
    				maxLen = Math.max(maxLen, dp[i][j]);
    			}
    		}
    	}
    	
    	int answer = maxLen * maxLen;
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}