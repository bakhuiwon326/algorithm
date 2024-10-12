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
	public static int oneCnt;
	public static int answer = Integer.MAX_VALUE;
    public static int[][] board = new int[10][10];
    public static boolean[][] attached = new boolean[10][10];
    public static int[] state = {0,5,5,5,5,5};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	for(int i = 0; i < 10; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 10; j++) {
    			board[i][j] = Integer.parseInt(st.nextToken());
    			if(board[i][j] == 1) oneCnt++;
    		}
    	}
    	
    	backtracking(0);
    	
    	if(answer == Integer.MAX_VALUE) {
    		answer = -1;
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void backtracking(int cnt) {
    	
    	Pos pos = getPos();
    	if(pos == null) {
    		answer = Math.min(answer, cnt);
    		return;
    	}
    	
    	for(int n = 5; n >= 1; n--) {
			if(!attachable(pos.r, pos.c, n)) continue;
			boolean[][] tmp = new boolean[10][10];
			copyArray(attached, tmp);
			state[n]--;
			attach(pos.r, pos.c, n);
			backtracking(cnt + 1);
			copyArray(tmp, attached);
			state[n]++;
		}
    }
    
    public static Pos getPos() {
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			if(board[i][j] == 0) continue;
    			else if(!attached[i][j]) {
    				return new Pos(i, j);
    			}
    		}
    	}
    	return null;
    }
    
    public static boolean attachable(int r, int c, int n) {
    	if(state[n] == 0) return false;
    	for(int i = r; i < r + n; i++) {
    		for(int j = c; j < c + n; j++) {
    			if(!inRange(i, j) || board[i][j] == 0 || attached[i][j]) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static void attach(int r, int c, int n) {
    	for(int i = r; i < r + n; i++) {
    		for(int j = c; j < c + n; j++) {
    			attached[i][j] = true;
    		}
    	}
    }
    
    public static void copyArray(boolean[][] origin, boolean[][] tmp) {
    	for(int i = 0; i < 10; i++) {
    		for(int j = 0; j < 10; j++) {
    			tmp[i][j] = origin[i][j];
    		}
    	}
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 &&  r < 10 && c >= 0 && c < 10;
    }
}