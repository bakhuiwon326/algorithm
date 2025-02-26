import java.util.*;
import java.io.*;


public class Main {
	public static int maxArea = 0;
	public static boolean[][] visited;
	public static int n, m, cnt;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int[][] map;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new int[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	visited = new boolean[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(map[i][j] == 1 && !visited[i][j]) {
    				cnt++;
    				maxArea = Math.max(maxArea, bfs(i, j));    				
    			}
    		}
    	}
    	
    	
    	bw.write(cnt + "\n" + maxArea);
    	bw.flush();
    	bw.close();
    	
    	
    }
    
    public static int bfs(int startR, int startC) {
    	
    	int area = 0;
    	Deque<int[]> q = new ArrayDeque<>();
    	visited[startR][startC] = true;
    	q.offer(new int[] {startR, startC});
    	
    	while(!q.isEmpty()) {
    		
    		int[] now = q.poll();
    		
    		area++;
    		for(int i = 0; i < 4; i++) {
    			int nr = now[0] + dr[i];
    			int nc = now[1] + dc[i];
    			if(inRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
    				visited[nr][nc] = true;
    				q.offer(new int[] {nr, nc});
    			}
    		}
    		
    	}
    	
    	return area;
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}