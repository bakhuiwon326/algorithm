import java.util.*;
import java.io.*;

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
	public String toString() {
		return String.format("(%d,%d)", r, c);
	}
}

public class Main {
	
	public static int day;
	public static int n, m;
	public static char[][] area;
	public static List<Pos> swan = new ArrayList<>();
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int[][] dist;
	public static boolean[][] visited;
	public static Deque<Pos> swanQ = new ArrayDeque<>(); // 백조1을 이동시킬 위치
	public static Deque<Pos> iceQ = new ArrayDeque<>(); // 물과 접촉한 빙판위치를 담은 큐
	public static Deque<Pos> waterQ = new ArrayDeque<>();
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// input
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	dist = new int[n][m];
    	area = new char[n][m];
    	visited = new boolean[n][m];
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			area[i][j] = str.charAt(j);
    			if(area[i][j] == 'L') {
    				area[i][j] = '.';
    				swan.add(new Pos(i, j));
    			}
    			if(area[i][j] == '.') {
    				waterQ.offer(new Pos(i,j));
    			}
    		}
    	}

    	// 첫번째 백조의 위치 넣기
    	swanQ.offer(new Pos(swan.get(0)));
    	visited[swan.get(0).r][swan.get(0).c] = true;
    	
//    	// 처음 물과 근접한 빙판 위치 넣기
//    	for(int i = 0; i < n; i++) {
//    		for(int j = 0; j < m; j++) {
//    			if(area[i][j] == 'X') {
//    				for(int dir = 0; dir < 4; dir++) {
//    					int nr = i + dr[dir];
//    					int nc = j + dc[dir];
//    					if(inRange(nr, nc) && area[nr][nc] != 'X') {
//    						iceQ.offer(new Pos(i, j));
//    						break;
//    					}
//    				}
//    			}
//    		}
//    	}
    	
    	// solve
    	while(!isMeetSwan()) {
    		melting();
    		day++;
    	}
    	
    	bw.write(day + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void melting(){
  
//    	int s = iceQ.size();
//    	
//    	for(int i = 0; i < s; i++) {
//    		Pos now = iceQ.poll();
//    		area[now.r][now.c] = '.';
//    		for(int dir = 0; dir < 4; dir++) {
//    			int nr = now.r + dr[dir];
//    			int nc = now.c + dc[dir];
//    			if(inRange(nr, nc) && area[nr][nc] == 'X') {
//    				iceQ.offer(new Pos(nr,nc));
//    			}
//    		}
//    	}
    	
    	int s = waterQ.size();
    	for(int i = 0; i < s; i++) {
    		Pos now = waterQ.poll();
    		for(int dir = 0; dir < 4; dir++) {
    			int nr = now.r + dr[dir];
    			int nc = now.c + dc[dir];
    			if(inRange(nr, nc) && area[nr][nc] == 'X') {
    				waterQ.offer(new Pos(nr, nc));
    				area[nr][nc] = '.';
    			}
    		}
    	}
    	
    }
    
    public static boolean isMeetSwan() {
    	
    	Deque<Pos> nextQ = new ArrayDeque<>();
    	
    	while(!swanQ.isEmpty()) {
    		Pos now = swanQ.poll();
    		
    		if(now.r == swan.get(1).r && now.c == swan.get(1).c) {
    			return true;
    		}
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && !visited[nr][nc]) {
    				visited[nr][nc] = true;
    				if(area[nr][nc] == 'X') {
    					nextQ.offer(new Pos(nr, nc));
    				}
    				else if(area[nr][nc] == '.') {
    					swanQ.offer(new Pos(nr, nc));
    				}
    			}
    		}
    	}
    	
    	swanQ = nextQ;
    	
    	return false;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
}