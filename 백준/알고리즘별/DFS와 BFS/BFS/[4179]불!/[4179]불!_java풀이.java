import java.util.*;
import java.io.*;

class Fire{
	int r; int c;
	public Fire(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Jihoon{
	int r; int c; int dist;
	public Jihoon(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}


public class Main {
	
	public static int n, m;
	public static char[][] map;
	public static Deque<Fire> fireQ = new ArrayDeque<>();
	public static Deque<Jihoon> jihoonQ = new ArrayDeque<>();
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static boolean[][] exit;
	public static boolean[][] visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new char[n][m];
    	exit = new boolean[n][m];
    	visited = new boolean[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j);
    			if(map[i][j] == 'F') fireQ.offer(new Fire(i, j));
    			else if(map[i][j] == 'J') {
    				map[i][j] = '.';
    				visited[i][j] = true;
    				jihoonQ.offer(new Jihoon(i, j, 0));
    			}
    		}
    	}

    	int answer = bfs();
    	
    	if(answer == -1) bw.write("IMPOSSIBLE");
    	else bw.write(answer + "");
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs() {
    	
    	while(!jihoonQ.isEmpty()) {
    		
    		int fs = fireQ.size();
    		
    		for(int i = 0; i < fs; i++) {
    			Fire fire = fireQ.poll();
    			for(int dir = 0; dir < 4 ; dir++) {
    				int nr = fire.r + dr[dir];
    				int nc = fire.c + dc[dir];
    				if(inRange(nr, nc) && map[nr][nc] == '.') {
    					map[nr][nc] = 'F';
    					fireQ.offer(new Fire(nr, nc));
    				}
    			}
    		}
    		
    		int js = jihoonQ.size();
    		
    		for(int i = 0; i < js; i++) {
    			Jihoon now = jihoonQ.poll();
    			
    			if(now.r == 0 || now.r == n-1 || now.c == 0 || now.c == m-1) return now.dist + 1;
    			
    			for(int dir = 0; dir < 4; dir++) {
    				int nr = now.r + dr[dir];
    				int nc = now.c + dc[dir];
    				if(inRange(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
    					visited[nr][nc] = true;
    					jihoonQ.offer(new Jihoon(nr, nc, now.dist + 1));
    				}
    			}
    		}
    		
    	}
    	
    	return -1;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
}