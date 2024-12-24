import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int m;
	public static int cnt;
	public static int maxVal = -1;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
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
    	
    	// visited
    	visited = new boolean[n][m];
    	
    	// dfs
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(!visited[i][j] && map[i][j] == 1) {
    				cnt++;
    				maxVal = Math.max(dfs(i, j), maxVal);
    			}
    		}
    	}
    	if(cnt == 0) maxVal = 0;
    	bw.write(cnt + "");
    	bw.newLine();
    	bw.write(maxVal + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int dfs(int r, int c) {
    	
    	int res = 1;
    	
    	visited[r][c] = true;
    	for(int i = 0; i < 4; i++) {
    		int nr = r + dr[i];
    		int nc = c + dc[i];
    		if(inRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
    			res += dfs(nr, nc);
    		}
    	}
    	
    	return res;
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
}