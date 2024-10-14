import java.io.*;
import java.util.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	public Pos(Pos pos) {
		this.r = pos.r;
		this.c = pos.c;
	}
}

class Info {
	Pos red;
	Pos blue;
	int cnt;
	public Info(Pos red, Pos blue, int cnt) {
		this.red = red;
		this.blue = blue;
		this.cnt = cnt;
	}
}

public class Main {
    
	public static int n;
	public static int m;
	
	public static boolean[][] map;
	
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	
	public static Pos target;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	
    	// map
    	Pos red = null;
    	Pos blue = null;
    	map = new boolean[n][m];
    	for(int i = 0; i < n; i++) {
    		String tmp = br.readLine();
    		for(int j = 0; j < m; j++) {
    			char c = tmp.charAt(j);
    			if(c == '.') {
    				map[i][j] = true;
    			}
    			else if(c == 'R') {
    				map[i][j] = true;
    				red = new Pos(i, j);
    			}
    			else if(c == 'B') {
    				map[i][j] = true;
    				blue = new Pos(i, j);
    			}
    			else if(c == 'O') {
    				map[i][j] = true;
    				target = new Pos(i, j);
    			}
    		}
    	}
    	
    	int res = bfs(new Info(new Pos(red), blue, 0));
    	
    	bw.write(res + "");
    	bw.flush();
    	bw.close();

    }
    
    public static int bfs(Info start) {
    	
    	boolean[][][][] visited = new boolean[n][m][n][m];
    	
    	Deque<Info> q = new ArrayDeque<>();
    	q.offer(start);
    	visited[start.red.r][start.red.c][start.blue.r][start.blue.c] = true;
    	
    	while(!q.isEmpty()){
    		Info now = q.peek();
    		q.pop();
    		
    		now.cnt++;
    		
    		if(now.cnt > 10) return -1; // 10회 초과
    		
    		// 4방향으로 중력
    		for(int dir = 0; dir < 4; dir++) {
    			
    			Pos nextRed = gravity(now.red, dir);
    			Pos nextBlue = gravity(now.blue, dir);
    			
    			
    			// 파랑공이 타겟에 도착하면 안됨.
    			if(isSamePos(nextBlue, target)) {
    				continue;
    			}
    			
    			// 빨강공이 타겟이 도착하면, cnt + 1이 최소값임!
    			if(isSamePos(nextRed, target)) {
    				return now.cnt;
    			}
    			
    			// 빨강공, 파랑공 겹쳐질 때, 위치 조정
    			if(isSamePos(nextRed, nextBlue)) {
    				adjustPos(now.red, now.blue, nextRed, nextBlue, dir);
    			}
    			
    			if(!visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c]){
    				visited[nextRed.r][nextRed.c][nextBlue.r][nextBlue.c] = true;
    				q.offer(new Info(nextRed, nextBlue, now.cnt));
    			}
    			
    		}
    	}
    	
    	return -1;
    }
    
    public static void adjustPos(Pos preRed, Pos preBlue, Pos nextRed, Pos nextBlue, int dir) {
    	if(dir == 0) {
    		if(preRed.r > preBlue.r) nextRed.r++;
    		else if(preRed.r < preBlue.r) nextBlue.r++;
    	}
    	else if(dir == 1) {
    		if(preRed.r > preBlue.r) nextBlue.r--;
    		else if(preRed.r < preBlue.r) nextRed.r--;
    	}
    	else if(dir == 2) {
    		if(preRed.c > preBlue.c) nextRed.c++;
    		else if(preRed.c < preBlue.c) nextBlue.c++;
    	}
    	else if(dir == 3) {
    		if(preRed.c > preBlue.c) nextBlue.c--;
    		else if(preRed.c < preBlue.c) nextRed.c--;
    	}
    }
    
    public static boolean isSamePos(Pos one, Pos two) {
    	return one.r == two.r && one.c == two.c;
    }
    
    public static Pos gravity(Pos pos, int dir) {
    	
    	Pos res = new Pos(pos);
    	
    	while(true) {
    		
    		int nr = res.r + dr[dir];
    		int nc = res.c + dc[dir];
    		
    		
    		if(!map[nr][nc] || !inRange(nr, nc)) {
    			break;
    		}
    		else {
    			res.r = nr;
    			res.c = nc;
    		}
    		
    		if(isSamePos(res, target)) {
    			break;
    		}
    		
    	}
    	
    	return res;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}