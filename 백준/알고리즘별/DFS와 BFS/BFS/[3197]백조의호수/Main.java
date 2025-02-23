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
	public static List<Pos> bird = new ArrayList<>();
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// input
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	area = new char[n][m];
    	Deque<Pos> q = new ArrayDeque<>();
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			area[i][j] = str.charAt(j);
    			if(area[i][j] == 'L') {
    				bird.add(new Pos(i, j));
    			}
    			else if(area[i][j] == 'X') {
    				for(int dir = 0; dir < 4; dir++) {
    					int nr = i + dr[dir];
    					int nc = j + dc[dir];
    					//System.out.printf("(%d,%d) -> (%d,%d)\n", i, j, nr, nc);
    					if(inRange(nr, nc) && (i == 2 || i == 7)) {
    						System.out.printf("*(%d,%d) -> (%d,%d : %c)\n", i, j, nr, nc, area[nr][nc]);
    					}
    					if(inRange(nr, nc) && (area[nr][nc] == '.' || area[nr][nc] == 'L')) {
    						System.out.printf("물이야 : (%d,%d : %c)\n", nr, nc, area[nr][nc]);
    						q.offer(new Pos(i, j));
    						break;
    					}
    				}
    			}
    		}
    	}
    	System.out.printf("(8,0) -> %c", area[8][0]);
    	System.out.printf("(7,1) -> %c", area[7][1]);
    	System.out.printf("(7,0) -> %c", area[7][0]);
    	printArea(q);
    	// solve
    	while(!isMeetBirds()) {
    		q = melting(q);
    		day++;
    	}
    	
    	bw.write(day + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static Deque<Pos> melting(Deque<Pos> meltQ){
    	
    	Deque<Pos> nextQ = new ArrayDeque<>();
    	
    	while(!meltQ.isEmpty()) {
    		Pos now = meltQ.poll();
    		area[now.r][now.c] = '.';
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && area[nr][nc] == 'X') {
    				nextQ.offer(new Pos(nr,nc));
    			}
    		}
    	}
    	System.out.println("멜팅후");
    	return nextQ;
    }
    
    public static boolean isMeetBirds() {
    	int[][] dist = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			dist[i][j] = -1;
    		}
    	}
    	Deque<Pos> q = new ArrayDeque<>();
    	
    	q.offer(new Pos(bird.get(0)));
    	dist[bird.get(0).r][bird.get(0).c] = 0;
    	
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && dist[nr][nc] == -1 && area[nr][nc] != 'X') {
    				dist[nr][nc] = dist[now.r][now.c] + 1;
    				q.offer(new Pos(nr, nc));
    			}
    		}
    	}
    	
    	return dist[bird.get(1).r][bird.get(1).c] != -1;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    public static void printArea(Deque<Pos> q) {
    	System.out.println("****************");
    	System.out.printf("q: %s\n", q.toString());
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m ;j++) {
    			System.out.printf("%c ", area[i][j]);
    		}
    		System.out.println();
    	}
    }
}