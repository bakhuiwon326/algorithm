import java.util.*;
import java.io.*;
import java.util.Map.Entry;

class Pos{
	int r, c, cnt;
	public Pos(int r, int c, int cnt) {
		this.r = r;
		this.c = c;
		this.cnt = cnt;
	}
}

public class Main {
	public static int n, m, p;
	public static int[] s, answer;
	public static char[][] map;
	public static Deque<Pos>[] qList;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	p = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	
    	s = new int[p+1];
    	for(int i = 1; i <= p; i++) {
    		s[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	qList = new Deque[p+1];
    	for(int i = 1; i <= p; i++) {
    		qList[i] = new ArrayDeque<>();
    	}
    	
    	map = new char[n][m];
    	for(int i = 0; i < n; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < m ;j++) {
    			map[i][j] = str.charAt(j);
    			if(map[i][j] != '.' && map[i][j] != '#') {
    				int no = map[i][j] - '0';
    				Deque<Pos> q = qList[no];
    				q.offer(new Pos(i, j, 0));
    			}
    		}
    	}

    	while(true) {
    		boolean anyMove = false;
    		for(int i = 1; i <= p; i++) {
    			if(qList[i].size() > 0) {
    				bfs(i);
    				anyMove = true;
    			}
    		}
    		if(!anyMove) break;
    	}
    	//printMap();
    	
    	// answer 기록
    	answer = new int[p+1];
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			if(Character.isDigit(map[i][j])){
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
    
    public static void printMap() {
    	System.out.println("---");
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < m; j++) {
    			System.out.printf("%c ",map[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    public static void bfs(int no) {
        Deque<Pos> q = qList[no];
        for (int moves = 0; moves < s[no]; moves++) { // s[i]만큼 확장
            int size = q.size();
            for (int j = 0; j < size; j++) {
                Pos now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];
                    if (inRange(nr, nc) && map[nr][nc] == '.') {
                        map[nr][nc] = (char)(no + '0');
                        q.offer(new Pos(nr, nc, 0));
                    }
                }
            }
            if (q.isEmpty()) break; // 더 확장할 곳이 없으면 종료
        }
    }
    
    
    public static boolean isFinish() {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0;j < m; j++) {
    			if(map[i][j] == '.') return false;
    		}
    	}
    	return true;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}