import java.util.*;
import java.io.*;

class Pos{
	int r; int c; int nightMove; int dist;
	public Pos(int r, int c, int nightMove, int dist) {
		this.r = r;
		this.c = c;
		this.nightMove = nightMove;
		this.dist = dist;
	}
}

public class Main {
	public static int res = Integer.MAX_VALUE;
	public static int k, w, h;
	public static int[][] map;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int[] ndr = {-2,-2,2,2,-1,-1,1,1};
	public static int[] ndc = {-1,1,-1,1,-2,2,-2,2};

    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	k = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());
    	w = Integer.parseInt(st.nextToken());
    	h = Integer.parseInt(st.nextToken());
    	
    	map = new int[h+1][w+1]; // 1: 장애물
    	
    	for(int i = 0; i < h; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < w ;j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	res = bfs();
    	
    	bw.write(res + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs() {
    	
    	int answer = Integer.MAX_VALUE;
    	boolean[][][] visited = new boolean[h+1][w+1][k+1];
    	Deque<Pos> q = new ArrayDeque<>();
    	q.offer(new Pos(0,0,0,0));
    	visited[0][0][0] = true;
    	
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		if(now.r == h-1 && now.c == w-1) {
    			answer = now.dist;
    			break;
    		}
    		// 일반 움직임
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][now.nightMove]) {
    				visited[nr][nc][now.nightMove] = true;
    				q.offer(new Pos(nr, nc, now.nightMove, now.dist + 1));
    			}
    		}
    		// 나이트 움직임 : 장애물을 넘을 수 있다.
    		if(now.nightMove < k) {
    			for(int i = 0; i < 8; i++) {
    				int nr = now.r + ndr[i];
    				int nc = now.c + ndc[i];
    				if(inRange(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc][now.nightMove+1]) {
    					visited[nr][nc][now.nightMove+1] = true;
    					q.offer(new Pos(nr, nc, now.nightMove+1, now.dist + 1));
    				}
    			}    					
    		}
    		
    	}
    	
    	return answer == Integer.MAX_VALUE ? -1 : answer;
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < h && c >= 0 && c < w;
    }
    
}