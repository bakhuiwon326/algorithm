import java.io.*;
import java.util.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	public String toString(Object obj) {
		if(obj instanceof Pos) {
			return String.format("(%d,%d)", this.r, this.c);
		}
		return null;
	}
}

public class Main {
	
    public static boolean[][] check = new boolean[5][5];
    public static char[][] map = new char[5][5];
    public static Pos[] res = new Pos[7];
    public static int[] dr = {1,-1,0,0};
    public static int[] dc = {0,0,1,-1};
    public static List<Pos> posList = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	for(int i = 0; i < 5 ; i++) {
    		String str = br.readLine();
    		for(int j = 0; j < 5; j++) {
    			map[i][j] = str.charAt(j);
    		}
    	}
    	
    	//printMap();
    	
    	int answer = combination(0,0);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int combination(int depth, int start) {
    	if(depth == 7) {
    		if(bfs()) {
    			//printChecked();
    			return 1;
    		}
    		else return 0;
    	}
    	int sum = 0;
    	for(int i = start; i < 25; i++) {
    		int r = i / 5;
    		int c = i % 5;
    		check[r][c] = true;
    		sum += combination(depth + 1, i + 1);
    		check[r][c] = false;
    	}
    	
    	return sum;
    }
    
    public static boolean bfs() {
    	boolean[][] visited = new boolean[5][5];
    	
    	Pos start = null;
    	
    	for(int i = 0; i < 25; i++) {
    		int r = i / 5;
    		int c = i % 5;
    		if(check[r][c]) {
    			start = new Pos(r, c);
				break;
    		}
    	}
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	q.add(start);
    	visited[start.r][start.c] = true;
    	
    	int sCnt = map[start.r][start.c] == 'S' ? 1 : 0;
    	int continuousCnt = 1;
    	while(!q.isEmpty()) {
    		Pos now = q.peek();
    		q.pop();
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && check[nr][nc] && !visited[nr][nc]) {
    				if(map[nr][nc] == 'S') sCnt++;
    				continuousCnt++;
    				visited[nr][nc] = true;
    				q.add(new Pos(nr, nc));
    			}
    		}
    		
    	}
    	
    	return sCnt >= 4 && continuousCnt == 7;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < 5 && c >= 0&& c < 5;
    }
    
    public static void printChecked() {
    	System.out.println("---------------------");
    	for(int i = 0; i <5; i++) {
    		for(int j = 0; j <5 ;j++) {
    			System.out.printf("%d ", check[i][j] ? 1 : 0);
    		}
    		System.out.println();
    	}
    }
    
    public static void printMap() {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5 ;j++) {
    			System.out.printf("%c ", map[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    public static boolean my() {
    	for(int i = 0; i < 5; i++) {
    		if(!check[1][i]) return false;
    	}
    	if(!check[2][4] || !check[3][4]) return false;
    	return true;
    }
    
}