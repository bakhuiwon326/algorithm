import java.util.*;
import java.io.*;

public class Main {
	
	public static int t;
	public static int w, h;
	public static int[][] fire;
	public static int[][] sg;
	public static Deque<int[]> fireQ;
	public static Deque<int[]> sgQ;
	public static char[][] map;
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	t = Integer.parseInt(br.readLine());
    	
    	while(t-- > 0) {
    		
    		st = new StringTokenizer(br.readLine());
    		w = Integer.parseInt(st.nextToken());
    		h = Integer.parseInt(st.nextToken());
    		
    		init();
    		
    		for(int i = 0; i < h; i++) {
    			String str = br.readLine();
    			for(int j = 0; j < w; j++) {
    				map[i][j] = str.charAt(j);
    				if(map[i][j] == '@') {
    					sg[i][j] = 0;
    					sgQ.offer(new int[] {i, j});
    				}
    				else if(map[i][j] == '*') {
    					fire[i][j] = 0;
    					fireQ.offer(new int[] {i, j});
    				}
    			}
    			
    		}
    		 		 		
    		moveFire();
    		String answer = moveSG();
    		
    		bw.write(answer);
    		bw.newLine();
    		
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void moveFire() {
    	while(!fireQ.isEmpty()) {
    		int[] now = fireQ.poll();
    		int r = now[0];
    		int c = now[1];
    		for(int i = 0; i < 4; i++) {
    			int nr = r + dr[i];
    			int nc = c + dc[i];
    			if(inRange(nr, nc) && map[nr][nc] != '#' && fire[nr][nc] == -1) {
    				fire[nr][nc] = fire[r][c] + 1;
    				fireQ.offer(new int[] {nr, nc});
    			}
    		}
    	}
    }
    
    public static String moveSG() {
    	while(!sgQ.isEmpty()) {
    		int[] now = sgQ.poll();
    		int r = now[0];
    		int c = now[1];
    		for(int i = 0; i < 4; i++) {
    			int nr = r + dr[i];
    			int nc = c + dc[i];
    			if(!inRange(nr, nc)) {
    				int answer = sg[r][c] + 1;
    				return String.valueOf(answer + "");
    			}
    			else if(map[nr][nc] != '#' && sg[nr][nc] == -1 && (fire[nr][nc] == -1 || fire[nr][nc] > sg[r][c] + 1)) {
    				sg[nr][nc] = sg[r][c] + 1;
    				sgQ.offer(new int[] {nr, nc});
    			}
    		}
    	}
    	return "IMPOSSIBLE";
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < h && c >= 0 && c < w;
    }
    
    public static void init() {
    	map = new char[h][w];
    	fireQ = new ArrayDeque<>();
    	sgQ = new ArrayDeque<>();
    	fire = new int[h][w];
    	sg = new int[h][w];
    	for(int i = 0; i < h; i++) {
    		for(int j = 0; j < w; j++) {
    			fire[i][j] = -1;
    			sg[i][j] = -1;
    		}
    	}
    }
}