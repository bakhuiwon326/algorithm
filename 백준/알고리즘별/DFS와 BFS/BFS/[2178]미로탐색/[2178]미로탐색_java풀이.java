import java.util.*;
import java.io.*;


public class Main {
	public static int n, m;
	public static int[][] map;
	public static int[][] dist;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    
    	map = new int[n][m];
    	dist = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j) - '0';
    			dist[i][j] = -1;
    		}
    	}
    	
    	bfs();
    	
    	bw.write(dist[n-1][m-1] + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
    
    public static void bfs() {
    	Deque<int[]> q =  new ArrayDeque<>();
    	q.offer(new int[] {0,0});
    	dist[0][0] = 1;
    	
    	while(!q.isEmpty()) {
    		int[] now = q.poll();
    		if(now[0] == n-1 && now[1] == m-1) {
    			break;
    		}
    		for(int i = 0; i < 4; i++){
    			int nr = now[0] + dr[i];
    			int nc = now[1] + dc[i];
    			if(inRange(nr, nc) && map[nr][nc] == 1 && dist[nr][nc] == -1) {
    				dist[nr][nc] = dist[now[0]][now[1]] + 1;
    				q.offer(new int[] {nr, nc});
    			}
    		}
    	}
    	
    	return;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}