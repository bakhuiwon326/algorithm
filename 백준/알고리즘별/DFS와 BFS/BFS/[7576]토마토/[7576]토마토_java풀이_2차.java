import java.util.*;
import java.io.*;

class Pos {
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static int day = 0;
	public static int n, m, notRipeCnt, newRipeCnt, ripeCnt;
	public static int[][] tomato;
	public static Deque<Pos> q = new ArrayDeque<>();
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	
    	tomato = new int[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			tomato[i][j] = Integer.parseInt(st.nextToken());
    			if(tomato[i][j] == 1) {
    				ripeCnt++;
    				q.offer(new Pos(i, j));
    			}
    			else if(tomato[i][j] == 0) notRipeCnt++;
    		}
    	}
    	
    	if(ripeCnt > 0 && notRipeCnt == 0) day = 0;
    	else {
    		bfs();
    		if(newRipeCnt < notRipeCnt) day = -1;
    	}
    	
    	bw.write(day + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs() {
    	while(!q.isEmpty()) {
    		int s = q.size();
    		for(int i = 0; i < s; i++) {
    			Pos now = q.poll();
    			for(int dir = 0; dir < 4; dir++) {
    				int nr = now.r + dr[dir];
    				int nc = now.c + dc[dir];
    				if(inRange(nr, nc) && tomato[nr][nc] == 0) {
    					newRipeCnt++;
    					tomato[nr][nc] = 1;
    					q.offer(new Pos(nr, nc));
    				}
    			}
    		}
    		if(!q.isEmpty()) day++;
    	}
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}