import java.util.*;
import java.io.*;

public class Main {
	
	public static int n;
	public static int[][] triangle;
	public static int[][] prefix;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	triangle = new int[n][n];
    	prefix = new int[n][n];
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			triangle[i][j] = -1;
    		}
    	}
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < i + 1 ; j++) {
    			triangle[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	prefix[0][0] = triangle[0][0];
    	
    	for(int r = 0; r < n - 1; r++) {
    		for(int c = 0; c < r + 1; c++) {
    			// 왼쪽 아래 (r+1, c)
    			prefix[r+1][c] = Math.max(prefix[r][c] + triangle[r+1][c], prefix[r+1][c]);
    			
    			// 오른쪽 아래 (r+1, c+1)
    			prefix[r+1][c+1] = Math.max(prefix[r][c] + triangle[r+1][c+1], prefix[r+1][c+1]);
    		}
    	}
    	
    	int answer = -1;
    	for(int i = 0; i < n; i++) {
    		answer = Math.max(prefix[n-1][i], answer);
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    	
    	
    }
    
    public static boolean inRange(int r, int c, int n) {
    	return r >= 0 && r < n && c >= 0 && c < n;
    }
} 