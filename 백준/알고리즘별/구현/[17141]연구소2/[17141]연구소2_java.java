import java.util.*;
import java.io.*;

class Pos {
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	public String toString() {
		return String.format("(%d,%d)", r, c);
	}
}


public class Main {
	
	public static int n, m;
	public static int wallN;
	public static List<Pos> virusPos = new ArrayList<>();
	public static Pos[] selected;
	public static int[][] map;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new int[n][n];
    	selected = new Pos[m];
    	
    	for(int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < n; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j] == 2) {
    				virusPos.add(new Pos(i, j));
    			}
    		}
    	}
    	
    	int answer = 0;
    	
    	if(virusPos.size() > m) answer = combination(0, m, 0);
    	else answer = combination(0, virusPos.size(), 0);
    	
    	if(answer == Integer.MAX_VALUE) answer = -1;
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
    
    public static int combination(int depth, int len, int next) {
    	if(depth == len) {
    		return bfs(len);
    	}
    	int res = Integer.MAX_VALUE;
    	for(int i = next; i < virusPos.size(); i++) {
    		selected[depth] = virusPos.get(i);
    		int preRes = combination(depth + 1, len, i + 1);
    		res = Math.min(res, preRes);
    	}
    	return res;
    }
    
    public static int bfs(int len) {
    	
    	int[][] dist = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n ; j++) {
    			dist[i][j] = -1;
    		}
    	}
    	
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	
    	for(int i = 0; i < len; i++) {
    		Pos pos = selected[i];
    		q.offer(pos);
    		dist[pos.r][pos.c] = 0;
    	}
    	
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && dist[nr][nc] == -1 && map[nr][nc] != 1) {
    				dist[nr][nc] = dist[now.r][now.c] + 1;
    				q.offer(new Pos(nr, nc));
    			}
    		}
    	}
    	
    	int res = -1;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n ; j++) {
    			if(map[i][j] != 1 && dist[i][j] == -1) return Integer.MAX_VALUE;
    			res = Math.max(res, dist[i][j]);
    		}
    	}
    	
    	return res;    	
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < n;
    }
    
}