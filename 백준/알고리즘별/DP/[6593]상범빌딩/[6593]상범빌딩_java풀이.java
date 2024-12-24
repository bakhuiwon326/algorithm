import java.util.*;
import java.io.*;

class Pos{
	int l; int r; int c;
	public Pos(int l, int r, int c) {
		this.l = l;
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static int[] dr = {0,0,1,-1,0,0};
	public static int[] dc = {1,-1,0,0,0,0};
	public static int[] dl = {0,0,0,0,-1,1};
	public static int L;
	public static int R;
	public static int C;
	public static Pos sPos;
	public static Pos ePos;
	public static int[][][] map;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	while(true) {
    		
    		st = new StringTokenizer(br.readLine());
    		L = Integer.parseInt(st.nextToken());
    		R = Integer.parseInt(st.nextToken());
    		C = Integer.parseInt(st.nextToken());
    		
    		if(R == 0 && C == 0 && L == 0) break;
    		
    		map = new int[L][R][C];    		
    		
    		for(int l = 0; l < L ; l++) {
    			for(int r = 0; r < R; r++) {
    				String str = br.readLine();
    				for(int c = 0; c < C; c++) {
    					char val = str.charAt(c);
    					if(val == 'S') {
    						sPos = new Pos(l, r, c);
    						map[l][r][c] = 0;
    					}
    					else if(val == 'E') {
    						ePos = new Pos(l, r, c);
    						map[l][r][c] = 0;
    					}
    					else if(val == '#'){
    						map[l][r][c] = 1;
    					}
    					else if(val == '.') {
    						map[l][r][c] = 0;
    					}
    				}
    			}
    			br.readLine();
    		}
    		
    		int res = bfs();
    		
    		if(res == 0) {
    			System.out.println("Trapped!");
    		}
    		else {
    			System.out.printf("Escaped in %d minute(s).\n", res);
    		}
    		
    	}
    	
    	
    }
    
    public static int bfs() {
    	int res = 0;
    	boolean[][][] visited = new boolean[L][R][C];
    	int[][][] dist = new int[L][R][C];
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	
    	q.addLast(sPos);
    	visited[sPos.l][sPos.r][sPos.c] = true;
    	dist[sPos.l][sPos.r][sPos.c] = 0;
    	
    	while(!q.isEmpty()) {
    		Pos now = q.peek();
    		q.removeFirst();
    		for(int i = 0; i < 6; i++) {
    			int nl = now.l + dl[i];
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nl ,nr , nc) && !visited[nl][nr][nc] && map[nl][nr][nc] == 0 ) {
    				visited[nl][nr][nc] = true;
    				dist[nl][nr][nc] = dist[now.l][now.r][now.c] + 1;
    				q.addLast(new Pos(nl, nr, nc));
    			}
    		}
    	}
    	
    	res = dist[ePos.l][ePos.r][ePos.c] != Integer.MAX_VALUE ? dist[ePos.l][ePos.r][ePos.c] : 0;
    	
    	return res;
    }
    
    public static boolean inRange(int l, int r, int c) {
    	return r >= 0 && r < R && c >= 0 && c < C && l >= 0 && l < L;
    }
}