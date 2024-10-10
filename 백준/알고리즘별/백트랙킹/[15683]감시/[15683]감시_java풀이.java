import java.io.*;
import java.util.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class Main {
    public static int n;
    public static int m;
    public static int answer = Integer.MAX_VALUE;
    public static int[][] map;
    public static int[] dr = {0,1,0,-1}; // 우 -> 하 -> 좌 -> 상
    public static int[] dc = {1,0,-1,0};
    public static List<Pos> camPos = new ArrayList<>();
    public static int[] rotateCnt = {4,2,4,4,1};
    
    public static int[][] camDir = {
    		{0},
    		{0,2},
    		{0,3},
    		{0,2,3},
    		{0,1,2,3}
    };
    
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// map
    	map = new int[n][m];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(1 <= map[i][j] && map[i][j] <= 5) {
    				camPos.add(new Pos(i, j));
    			}
    		}
    	}
    	
    	backtracking(0);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void backtracking(int depth) {
    	if(depth == camPos.size()) {
    		// 최소 값 구하기
    		int zeroCnt = 0;
    		for(int i = 0; i < n; i++) {
    			for(int j = 0; j < m; j++) {
    				if(map[i][j] == 0) zeroCnt++;
    			}
    		}
    		answer = Math.min(answer, zeroCnt);
    		return;
    	}
    	
    	Pos cam = camPos.get(depth);
    	int camNo = map[cam.r][cam.c] - 1;
    	// 탐색 및 90도 회전
    	for(int i = 0; i < rotateCnt[camNo]; i++) {

    		// 원본 저장
    		int[][] tmp = new int[n][m];
    		copyMap(map, tmp);
    		
    		// 방향 별 탐색
    		for(int j = 0; j < camDir[camNo].length; j++) {
    			
    			
    			int idx = camDir[camNo][j];
    			int dir = (idx + i) % 4;
    			// dir 방향 탐색
    			int r = cam.r;
    			int c = cam.c;
    			//System.out.println("dir: " + dir + " / (" + r + "," + c + ")");
    			while(inRange(r, c) && map[r][c] != 6) {
    				if(map[r][c] == 0) {
						map[r][c] = -1; // cctv 검출가능 위치
					}
    				r = r + dr[dir];
    				c = c + dc[dir];
    			}
    			
    		}
    		//printMap();
    		backtracking(depth + 1);
    		
    		// 원본 복귀
    		copyMap(tmp, map);
    	}
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    public static void copyMap(int[][] origin, int[][] tmp) {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m ; j++) {
    			tmp[i][j] = origin[i][j];
    		}
    	}
    }
    
    public static void printMap() {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			System.out.printf("%3d ", map[i][j]);
    		}
    		System.out.println();
    	}
    }
 
}