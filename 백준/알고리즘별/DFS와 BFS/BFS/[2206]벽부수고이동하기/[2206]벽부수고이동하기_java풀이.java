import java.util.*;
import java.io.*;

class Pos{
	int r; int c; int crashed; int dist;
	public Pos(int r, int c, int crashed, int dist) {
		this.r = r;
		this.c = c;
		this.crashed = crashed;
		this.dist = dist;
	}
}

public class Main {
	public static int answer = -1;
	public static int[] dr = {1,0,-1,0};
	public static int[] dc = {0,1,0,-1};
	public static int n;
	public static int m;
	public static int[][] map = new int[1000][1000];
	public static boolean[][][] visited = new boolean[1000][1000][2];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j) - '0';
    		}
    	}

    	Pos target = new Pos(n-1, m-1, 0, 0);

    	// bfs
    	int[][] dist = new int[n][m];
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	q.addLast(new Pos(0,0,0,1));
    	dist[0][0] = 1;
    	visited[0][0][0] = true;
    	
    	while(!q.isEmpty()) {
    		
    		Pos now = q.peek();
    		q.removeFirst();
    		
    		if(isSame(now, target)) {
    			answer = now.dist;
    			break;
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(!inRange(nr, nc)) continue;
    			
    			// 이미 벽을 부순 전적이 있다면?
    			if(now.crashed == 1) {
    				if(map[nr][nc] == 0 && !visited[nr][nc][1]) {
    					visited[nr][nc][1] = true;
    					q.addLast(new Pos(nr, nc, 1, now.dist + 1));
    				}
    			}
    			// 벽을 아직 안부셨다면?
    			else {
    				// 다음 위치가 벽이 아니면, 그냥 진행
    				if(map[nr][nc] == 0 && !visited[nr][nc][0]) {
    					visited[nr][nc][0] = true;
    					q.addLast(new Pos(nr, nc, 0, now.dist + 1));
    				}
    				// 다음 위치가 벽이라면, 한번 부셔본다
    				else if(map[nr][nc] == 1 && !visited[nr][nc][1]) {
    					visited[nr][nc][1] = true;
    					q.addLast(new Pos(nr, nc, 1, now.dist + 1));
    				}
    			}
    			
    		}
    		
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    public static boolean isSame(Pos pos1, Pos pos2) {
    	return pos1.r == pos2.r && pos1.c == pos2.c;
    }
}