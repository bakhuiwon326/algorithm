import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int m;
	public static int[][] map;
	public static int[][] prefix;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// map
    	map = new int[n + 1][n + 1];
    	for(int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= n; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	// 누적합 구하기 - 가로
    	prefix = new int[n + 1][n + 1];
    	for(int r = 1; r <= n; r++) {
    		for(int c = 1; c <= n; c++) {
    			prefix[r][c] += prefix[r][c-1] + map[r][c]; 
    		}
    	}
    	
    	// 누적합 구하기 - 세로
    	for(int c = 1;c <= n; c++) {
    		for(int r = 1; r <= n; r++) {
    			prefix[r][c] = prefix[r - 1][c] + prefix[r][c];
    		}
    	}
    	
    	// 구간합 구하기
    	for(int i = 0; i < m ;i++) {
    		st = new StringTokenizer(br.readLine());
    		
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		
    		int answer = prefix[c][d] - prefix[a-1][d] - prefix[c][b-1] + prefix[a-1][b-1];
    		
    		bw.write(answer + "");
    		bw.newLine();  		
    		
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
}