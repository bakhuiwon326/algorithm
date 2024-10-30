import java.util.*;
import java.io.*;

// DFS

public class Main {
    
	public static int n;
	public static int m;
	public static long answer;
	
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
	
	public static int[][] dp;
	public static int[][] map;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// map
    	map = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// dp : -1 아직탐색x, 0 목적지까지 갈 방법 없음... 
    	dp = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			dp[i][j] = -1;
    		}
    	}	
    	
    	// dfs
    	dfs(0, 0);
    	
    	answer = dp[0][0];
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int dfs(int r, int c) {
    	
    	if(r == n - 1 && c == m - 1) {
    		return 1;
    	}
    	
    	dp[r][c] = 0;
    	for(int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		if(inRange(nr, nc) && map[r][c] > map[nr][nc]) {
    			if(dp[nr][nc] == -1) {
    				dp[r][c] += dfs(nr, nc);
    			}
    			else {
    				dp[r][c] += dp[nr][nc];
    			}
    		}
    	}
    	
    	return dp[r][c];
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
}