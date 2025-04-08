import java.util.*;
import java.io.*;

class Building{
	int r; int c; int turn;
	public Building(int r, int c, int turn) {
		this.r = r;
		this.c = c;
		this.turn = turn;
	}
}

class Virus{
	int r; int c;
	public Virus(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int n, m;
	public static int Tg, Tb, X, B;
	public static char[][] map;
	public static Deque<Virus> virusQ = new ArrayDeque<>();
	public static Deque<Building> buildingQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	map = new char[n][m];
    	
    	st = new StringTokenizer(br.readLine());
    	Tg = Integer.parseInt(st.nextToken());
    	Tb = Integer.parseInt(st.nextToken());
    	X = Integer.parseInt(st.nextToken());
    	B = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j);
    			if(map[i][j] == '*') {
    				virusQ.offer(new Virus(i, j));
    			}
    		}
    	}
    	
    	int t = 0;
    	while(t < Tg) {
    		
    		// bfs - virus
    		boolean[][] isSpreading = new boolean[n][m]; // 전파중인 건물 유무 파악
    		int s = virusQ.size();
    		for(int i = 0; i < s; i++) {
    			Virus now = virusQ.poll();
    			for(int d = 0; d < 4; d++) {
    				int nr = now.r + dr[d];
    				int nc = now.c + dc[d];
    				if(inRange(nr, nc)) {
    					if(map[nr][nc] == '#') {
    						if(!isSpreading[nr][nc]) {
    							isSpreading[nr][nc] = true;
    							buildingQ.offer(new Building(nr, nc, 0));
    						}
    					}
    					else if(map[nr][nc] == '.') {
    						virusQ.offer(new Virus(nr, nc));
    						map[nr][nc] = '*';
    					}
    				}
    			}
    		}
    		
    		// buiding
    		s = buildingQ.size();
    		for(int i = 0; i < s; i++) {
    			Building now = buildingQ.poll();
    			if(now.turn >= Tb) {
    				virusQ.offer(new Virus(now.r, now.c));
    				map[now.r][now.c] = '*';
    				continue;
    			}
    			now.turn++;
    			buildingQ.offer(now);
    		}
    		
    		t++;
    	}
    	
    	boolean flag = false;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			//System.out.printf("%c ", map[i][j]);
    			if(map[i][j] == '#' || map[i][j] == '.') {
    				flag = true;
    				int r = i + 1;
    				int c = j + 1;
    				bw.write(r + " " + c);
    				bw.newLine();
    			}
    		}
    		//System.out.println();
    	}
    	
    	if(!flag) bw.write("-1");
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}