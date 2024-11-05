import java.util.*;
import java.io.*;


public class Main {
	public static int answer = -1;
	public static int n;
	public static int[][] forest;
	public static int[] dr = {0,0, 1, -1};
	public static int[] dc = {1, -1, 0, 0};
	public static boolean[][] visited;
	public static int[][] dp; // dp[i][j] : (i, j)에서 시작했을 때 이동하는 최대 깊이
	public static boolean flag;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	n = Integer.parseInt(br.readLine());
    	visited = new boolean[n][n];
    	dp = new int[n][n];
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			dp[i][j] = -1;
    		}
    	}
    	
    	// forest
    	forest = new int[n][n];
    	for(int i = 0 ; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < n; j++) {
    			forest[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			int res = dfs(i, j);
    			answer = Math.max(answer, res);
    		}
    	}
    
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int dfs(int r, int c) {
    	
    	if(dp[r][c] > 0) {
    		return dp[r][c];
    	}
    	 
    	dp[r][c] = 1;
    	
    	for(int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		if(inRange(nr, nc) && forest[r][c] < forest[nr][nc]) {
    			dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
    		}
    	}
    	
    	return dp[r][c];
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < n;
    }
}