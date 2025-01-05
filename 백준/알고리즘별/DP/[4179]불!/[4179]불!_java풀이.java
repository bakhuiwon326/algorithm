import java.util.*;
import java.io.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
	public static int n, m;
	public static char[][] maze;
	public static int[][] fireTime;
	public static int[][] jihoonTime;
	public static Deque<Pos> fireQ = new ArrayDeque<>();
	public static Deque<Pos> jihoonQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	maze = new char[n][m];
    	fireTime = new int[n][m];
    	jihoonTime = new int[n][m];
    	for(int i = 0; i < n; i++) {
            Arrays.fill(fireTime[i], -1);
            Arrays.fill(jihoonTime[i], -1);
        }
        
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			maze[i][j] = str.charAt(j);
    			if(maze[i][j] == 'J') {
    				jihoonQ.offer(new Pos(i, j));
    				jihoonTime[i][j] = 0;
    			}
    			else if(maze[i][j] == 'F') {
    				fireQ.offer(new Pos(i, j));
    				fireTime[i][j] = 0;
    			}
    		}
    	}
    	
    	// solve
    	setFireTime();
    	int res = moveJihoon();
    	
    	String answer = res == -1 ? "IMPOSSIBLE" : String.valueOf(res);
    	bw.write(answer);
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void setFireTime() {
    	while(!fireQ.isEmpty()) {
    		Pos now = fireQ.poll();
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(!inRange(nr, nc) || fireTime[nr][nc] != -1 || maze[nr][nc] == '#') continue;
    			fireTime[nr][nc] = fireTime[now.r][now.c] + 1; 
    			fireQ.offer(new Pos(nr, nc));
    		}
    	}
    }
    
    public static int moveJihoon() {
    	
    	while(!jihoonQ.isEmpty()) {
    		
    		Pos now = jihoonQ.poll();
    		
    		if(canExit(now)) {
    			return jihoonTime[now.r][now.c] + 1;
    		}
    		
    		for(int i = 0 ; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			
    			if(inRange(nr, nc) && maze[nr][nc] != '#' && jihoonTime[nr][nc] == -1 && (fireTime[nr][nc] == -1 || fireTime[nr][nc] > jihoonTime[now.r][now.c] + 1)) {
    				jihoonQ.offer(new Pos(nr, nc));    				
    				jihoonTime[nr][nc] = jihoonTime[now.r][now.c] + 1;    				
    			}
				
    		}
    	}
    	
    	return -1;
    }
    
    public static boolean canExit(Pos now) {
    	//return !inRange(now.r, now.c);
    	return now.r == 0 || now.c == 0 || now.r == n-1 || now.c == m-1;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    public static void printFireTime() {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			System.out.printf("%d ", fireTime[i][j]);
    		}
    		System.out.println();
    	}
    }
    
}