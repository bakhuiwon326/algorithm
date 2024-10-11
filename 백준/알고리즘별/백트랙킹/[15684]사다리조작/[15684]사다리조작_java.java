import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int h;
    public static int[][] ladder;
    public static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m, h
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	h = Integer.parseInt(st.nextToken());
    	
    	ladder = new int[h][n];
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken()) - 1;
    		int b = Integer.parseInt(st.nextToken()) - 1;
    		ladder[a][b] = 1;
    		ladder[a][b + 1] = 2;
    	}
    	
    	answer = -1;
    	for(int i = 0; i <= 3; i++) {
    		boolean res = backtracking(0, i);
    		if(res) {
    			answer = i;
    			break;
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean backtracking(int cnt, int size) {
    	
    	if(cnt == size) {
    		if(isGameEnd()) return true;
    		else return false;
    	}

    	for(int i = 0; i < h; i++) {
    		for(int j = 0; j < n - 1; j++) {
    			if(ladder[i][j] == 0 && ladder[i][j + 1] == 0) {
    				ladder[i][j] = 1;
    				ladder[i][j + 1] = 2;
    				boolean res = backtracking(cnt + 1, size);
    				if(res) return true;
    				ladder[i][j] = 0;
    				ladder[i][j + 1] = 0;
    			}
    		}
    	}
    	
    	return false;
    	
    }
    
    public static boolean isGameEnd() {
    	for(int i = 0; i < n; i++) {
    		if(playLadder(i) != i) return false;
    	}
    	return true;
    }
    
    public static int playLadder(int col){
    	int r = 0;
    	int c = col;
    	while(r < h) {
    		if(ladder[r][c] == 1){
    			c++;
    		}
    		else if(ladder[r][c] == 2) {
    			c--;
    		}
    		r++;
    	}
    	return c;
    }
}