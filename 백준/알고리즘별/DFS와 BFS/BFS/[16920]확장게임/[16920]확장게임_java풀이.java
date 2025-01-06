import java.util.*;
import java.io.*;

class Pos{
	int r; int c; int dist;
	public Pos(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}
}

public class Main {
	public static int n, m, p;
	public static char[][] map;
	public static int[] dr = {0,0,-1,1};
	public static int[] dc = {1,-1,0,0};
	public static int[] sList;
	public static Deque<Pos>[] qList;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	p = Integer.parseInt(st.nextToken());
    	
    	qList = new Deque[p+1];
    	for(int i = 0; i <= p; i++) {
    		qList[i] = new ArrayDeque<>();
    	}
    	
    	sList = new int[p+1];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= p; i++) {
    		sList[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	map = new char[n][m];
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m; j++) {
    			map[i][j] = str.charAt(j);
    			if(map[i][j] != '.' && map[i][j] != '#') {
    				int no = map[i][j] - '0';
    				qList[no].offer(new Pos(i, j, 0));
    			}
    		}
    	}
    	
    	while(true) {
    		
    		boolean flag = false;

    		for(int i = 1; i <= p; i++) {
    			Deque<Pos> nextQ = new ArrayDeque<>();
    			Deque<Pos> q = qList[i];
    			while(!q.isEmpty()) {
    				Pos now = q.poll();
    				if(now.dist >= sList[i]) {
    					nextQ.offer(new Pos(now.r, now.c, 0));
    					continue;
    				}
    				for(int d = 0; d < 4; d++) {
    					int nr = now.r + dr[d];
    					int nc = now.c + dc[d];
    					if(inRange(nr, nc) && map[nr][nc] == '.') {
    						map[nr][nc] = (char)(i + '0');
    						q.offer(new Pos(nr, nc, now.dist + 1));
    						flag = true;
    					}
    				}
    			}
    			
    			qList[i] = nextQ;

    		}
    		
    		if(!flag) break;
    	}
    	
    	// 정답 구하기
    	int[] answer = new int[p+1];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(Character.isDigit(map[i][j])) {
    				int no = map[i][j] - '0';
    				answer[no]++;
    			}
    		}
    	}
    	
    	for(int i = 1; i <= p; i++) {
    		bw.write(answer[i] + " ");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
    
    
}