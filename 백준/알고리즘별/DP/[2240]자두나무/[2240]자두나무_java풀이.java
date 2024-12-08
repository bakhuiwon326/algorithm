import java.util.*;
import java.io.*;


public class Main {
	
	public static int T;
	public static int W;
	public static int[][] dp;
	public static int[] drop;
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	T = Integer.parseInt(st.nextToken());
    	W = Integer.parseInt(st.nextToken());
    	
    	dp = new int[T + 1][W + 1];
    	drop = new int[T + 1];
    	
    	for(int t = 1; t <= T; t++) {
    		drop[t] = Integer.parseInt(br.readLine());
    	}
    	
    	if(drop[1] == 1) {
    		dp[1][0] = 1;
    	}
    	else {
    		dp[1][1] = 1;
    	}
    	
    	for(int t = 2;t <= T; t++) {
    		for(int cnt = 0; cnt <= W; cnt++) {
    			
    			if(cnt == 0) {
    				if(drop[t] == 1) {
    					dp[t][cnt] = dp[t-1][cnt] + 1;
    				}
    				else {
    					dp[t][cnt] = dp[t-1][cnt];
    				}
    				continue;
    			}
    			
    			if(cnt % 2 == 0) { // 동작 후 1번 나무에 위치
    				if(drop[t] == 1) {
    					dp[t][cnt] = Math.max(dp[t-1][cnt]+1, dp[t-1][cnt-1]+1);
    				}
    				else {
    					dp[t][cnt] = Math.max(dp[t-1][cnt], dp[t-1][cnt-1]);
    				}
    			}
    			else { // 동작 후 2번 나무에 위치
    				if(drop[t] == 1) {
    					dp[t][cnt] = Math.max(dp[t-1][cnt], dp[t-1][cnt-1]);
    				}
    				else {
    					dp[t][cnt] = Math.max(dp[t-1][cnt] + 1, dp[t-1][cnt-1] + 1);
    				}
    			}
    		}
    	}
    	
    	
    	int answer = -1;
    	for(int i = 0; i <= W; i++) {
    		answer = Math.max(answer, dp[T][i]);
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	   	   	
    	
    }
}