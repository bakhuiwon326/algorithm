import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int tc = 0;
	public static int[][] graph;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	while(true) {
    		
    		n = Integer.parseInt(br.readLine());
    		
    		if(n == 0) break;
    		
    		tc++;
    		graph = new int[n][3];
    		for(int i = 0; i < n; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < 3; j++) {
    				graph[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		// (i,j)로 이동하는 최소비용
    		int[][] dp = new int[n][3];
    		// dp 초기화
    		dp[0][0] = Integer.MAX_VALUE;
    		dp[0][1] = graph[0][1];
    		dp[0][2] = graph[0][1] + graph[0][2];
    		
    		for(int i = 1; i < n; i++) {
    			dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + graph[i][0];
    			dp[i][1] = Math.min(dp[i-1][0], Math.min(dp[i-1][1], Math.min(dp[i-1][2], dp[i][0]))) + graph[i][1];
    			dp[i][2] = Math.min(dp[i-1][1], Math.min(dp[i-1][2], dp[i][1])) + graph[i][2];
    		}
    		
    		bw.write(tc + ". " + dp[n-1][1]);
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < 3;
    }
    
}