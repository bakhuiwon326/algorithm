import java.io.*;
import java.util.*;

public class Main {
	
    public static int t;
    public static int[][] card;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	t = Integer.parseInt(br.readLine());
    	
    	while(t-- > 0) {
    		int n = Integer.parseInt(br.readLine());
    		card = new int[2][n];
    		
    		// card
    		for(int i = 0; i < 2; i++) {
    			st = new StringTokenizer(br.readLine());
    			for(int j = 0; j < n; j++) {
    				card[i][j] = Integer.parseInt(st.nextToken());
    			}
    		}
    		
    		// dp
    		int[][] dp = new int[2][n];
    		dp[0][0] = card[0][0];
    		dp[1][0] = card[1][0];
    		if(n > 1) {
    			dp[0][1] = card[0][1] + card[1][0];
    			dp[1][1] = card[0][0] + card[1][1];    			
    		}
    		
    		for(int c = 2; c < n; c++) {
    			for(int r = 0; r < 2; r++) {
    				int maxVal = -1;
    				if(r == 0) {
    					maxVal = Math.max(dp[r][c - 2], dp[r + 1][c - 2]);
    					maxVal = Math.max(maxVal, dp[r + 1][c - 1]);
    				}
    				else {
    					maxVal = Math.max(dp[r][c - 2], dp[r - 1][c - 2]);
    					maxVal = Math.max(maxVal, dp[r - 1][c - 1]);
    				}
    	
    				dp[r][c] = maxVal + card[r][c];
    			}
    		}
    		
    		int answer = Math.max(dp[0][n - 1], dp[1][n - 1]);
    		
    		bw.write(answer + "");
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
   
}