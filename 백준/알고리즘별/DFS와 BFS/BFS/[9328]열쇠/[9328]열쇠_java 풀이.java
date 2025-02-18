import java.util.*;
import java.io.*;

class Pos{
	int r, c;
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}
	Pos(Pos pos){
		this.r = pos.r;
		this.c = pos.c;
	}
}

public class Main {
	public static int t, h, w, answer;
	public static List<Pos> aisleList = new ArrayList<>();
	public static char[][] building;
	public static Set<Character> key = new HashSet<>();
	public static List<Pos> wait = new ArrayList<>();
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	t = Integer.parseInt(br.readLine());
    	
    	while(t-- > 0){
    		
    		// 빌딩
    		st = new StringTokenizer(br.readLine());
    		h = Integer.parseInt(st.nextToken());
    		w = Integer.parseInt(st.nextToken());

    		init();
    		
    		for(int i = 0; i < h; i++) {
    			String str = br.readLine();
    			for(int j = 0; j < w; j++) {
    				building[i][j] = str.charAt(j);
    				if(building[i][j] != '*' && (i == 0 || i == h-1 || j == 0 || j == w-1)) {
    					aisleList.add(new Pos(i, j));
    				}
    			}
    		}
    		
    		// 기존 보유 키
    		String keyStr = br.readLine();
    		if(keyStr.charAt(0) != '0') {
    			for(char k : keyStr.toCharArray()) {
    				key.add(k);
    			}    			
    		}
    		
    		bfs();
    		
    		bw.write(answer + "\n");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs() {
    	
    	boolean[][] visited = new boolean[h][w];
    	Deque<Pos> q = new ArrayDeque<>();
    	
    	for(Pos pos : aisleList) {
    		char val = building[pos.r][pos.c];
    		if(val == '$') {
				answer++;
				visited[pos.r][pos.c] = true;
				q.offer(new Pos(pos.r, pos.c));
			}
			// 열쇠  줍기
			else if('a' <= val && val <= 'z') {
				key.add(val);
				visited[pos.r][pos.c] = true;
				q.offer(new Pos(pos.r, pos.c));
			}
			// 대문 열기 시도
			else if('A' <= val && val <= 'Z') {
				char tmpKey = (char)(val + 'a' - 'A');
				if(key.contains(tmpKey)) {
					visited[pos.r][pos.c] = true;
					q.offer(new Pos(pos.r, pos.c));
				}
				else {
					wait.add(new Pos(pos.r, pos.c));
				}
			}
			// 일반 이동
			else {
				visited[pos.r][pos.c] = true;
				q.offer(new Pos(pos.r, pos.c));
			}		
    	}
    	
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && !visited[nr][nc] && building[nr][nc] != '*') {
    				char val = building[nr][nc];
    				// 문서 줍기
    				if(val == '$') {
    					answer++;
    					visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
    				}
    				// 열쇠  줍기
    				else if('a' <= val && val <= 'z') {
    					key.add(val);
    					visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
    				}
    				// 대문 열기 시도
    				else if('A' <= val && val <= 'Z') {
    					char tmpKey = (char)(val + 'a' - 'A');
    					if(key.contains(tmpKey)) {
    						visited[nr][nc] = true;
    						q.offer(new Pos(nr, nc));
    					}
    					else {
    						wait.add(new Pos(nr, nc));
    					}
    				}
    				// 일반 이동
    				else {
    					visited[nr][nc] = true;
						q.offer(new Pos(nr, nc));
    				}
    			}
    		}
    		
    		for(Pos pos : wait) {
    			char val = building[pos.r][pos.c];
    			char tmpKey = (char)(val + 'a' - 'A');
    			if(!visited[pos.r][pos.c] && key.contains(tmpKey)) {
    				visited[pos.r][pos.c] = true;
					q.offer(new Pos(pos.r, pos.c));	
    			}
    		}
    	}
    	
    }
    
    public static void init() {
    	answer = 0;
    	aisleList = new ArrayList<>();
    	building = new char[h][w];
    	key = new HashSet<>();
    	wait = new ArrayList<>();
    }
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < h && c >= 0 && c < w;
    }
 
}