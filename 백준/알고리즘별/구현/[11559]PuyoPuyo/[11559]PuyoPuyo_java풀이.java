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
	
	public static int n = 12;
	public static int m = 6;
	public static char[][] map = new char[n][m];
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,-1,1};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j);
    		}
    	}
    	
    	int day = 0;
    	
    	while(true) {
    		int cnt = bfs(); // 몇 개의 그룹이 터졌는지
    		if(cnt == 0) break;
    		day++;
    	}
    	
    	bw.write(day + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs() {
    	
    	int group = 0;
    	
    	boolean[][] visited = new boolean[n][m];
    	Deque<Pos> stk = new ArrayDeque<>(); // 터뜨리 뿌요 위치를 담음
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m ; j++) {
    			
    			if(map[i][j] == '.' | visited[i][j]) continue;
    			
    			Pos start = new Pos(i, j);
    			q.offer(start);
    			visited[i][j] = true;
    			
    			// (i,j)에서 시작하는 영역 크기
    			int cnt = 1;
    			stk.push(start);
    			
    			while(!q.isEmpty()) {
    				Pos now = q.poll();
    				char color = map[now.r][now.c];
    				for(int d = 0; d < 4; d++) {
    					int nr = now.r + dr[d];
    					int nc = now.c + dc[d];
    					if(inRange(nr, nc) && map[nr][nc] == color && !visited[nr][nc]) {
    						Pos next = new Pos(nr, nc);
    						cnt++;
    						visited[nr][nc] = true;
    						q.offer(next);
    						stk.push(next);
    					}
    				}
    			}
    			
    			if(cnt < 4) {
    				for(int k = 0; k < cnt; k++) {
    					stk.pop();
    				}
    			}
    			else {
    				group++;
    			}
    			
    		}
    	}
    	
    	// 터뜨리기
    	popPuyo(stk);
    	
    	// 중력
    	gravity();
    	
    	return group;
    }
    
    public static void popPuyo(Deque<Pos> stk) {
    	for(Pos pos : stk) {
    		map[pos.r][pos.c] = '.';
    	}
    }
    
    public static void gravity() {
    	
    	char[][] nextMap = new char[n][m];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			nextMap[i][j] = '.';
    		}
    	}
    	
    	// 각각의 열에서 밑에서부터 읽으면서, 뿌요가 있따면 nextMap[next][c]에 기록!
    	for(int c = 0; c < m; c++) {    		
    		int next = n-1;
    		for(int r = n - 1; r >= 0; r--) {
    			if(map[r][c] != '.') {
    				nextMap[next][c] = map[r][c];
    				next--;
    			}
    		}
    	}
    	
    	map = nextMap;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
   
}