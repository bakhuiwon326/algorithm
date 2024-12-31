import java.util.*;
import java.io.*;


class Pos{
	int r; int c; int dist; 
	public Pos(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
	public static int[] dr = {1,-1,0,0};
	public static int[] dc = {0,0,1,-1};
	public static int n, answer;
	public static int[][] map = new int[100][100];
	public static int[][] land = new int[100][100];
	public static List<Pos>[] edge = new List[5100];
	public static List<Pos> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < n; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int num = 1;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(map[i][j] == 1 && land[i][j] == 0) {
    				findLandBFS(i, j, num);
    				num++;
    			}
    		}
    	}
    	
    	answer = Integer.MAX_VALUE;
    	for(int i = 1; i < num; i++) {
    		for(int j = 0; j < edge[i].size(); j++) {
    			Pos edgePos = edge[i].get(j);
    			findShortageBFS(edgePos.r, edgePos.c);
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void findLandBFS(int r, int c, int num) {
    	
    	edge[num] = new ArrayList<>();
    	boolean[][] visited = new boolean[100][100];
    	Deque<Pos> q = new ArrayDeque<>();
    	q.offer(new Pos(r, c));
    	visited[r][c] = true;
    	land[r][c] = num;
    	
    	while(!q.isEmpty()) {
    		
    		Pos now = q.poll();
    		int notLandCnt = 0;
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && !visited[nr][nc]) {
    				if(map[nr][nc] ==  1) {
    					q.offer(new Pos(nr, nc));
    					visited[nr][nc] = true;
    					land[nr][nc] = num;    					
    				}
    				else {
    					notLandCnt++;
    				}
    			}
    		}
    		
    		if(notLandCnt > 0) { // 가생이 위치 추출
    			edge[num].add(now);
    		}
    	}
    }
    
    public static void findShortageBFS(int r, int c) {
    	
    	int landNum = land[r][c];
    	boolean[][] visited = new boolean[100][100];
    	Deque<Pos> q = new ArrayDeque<>();
    	q.offer(new Pos(r, c, 0));
    	visited[r][c] = true;
    	
    	while(!q.isEmpty()) {
    		
    		Pos now = q.poll();

    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && !visited[nr][nc]) {
    				// 다른 땅 도착
    				if(map[nr][nc] == 1 && land[nr][nc] != landNum) {
    					answer = Math.min(now.dist, answer);
    				}
    				// 건너는 중
    				else if(map[nr][nc] == 0){
    					q.offer(new Pos(nr, nc, now.dist + 1));
    					visited[nr][nc] = true;   
    				}
    			}
    		}
    	}
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < n;
    }
}