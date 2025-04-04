import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	while(true) {
    		st = new StringTokenizer(br.readLine());
    		int n = Integer.parseInt(st.nextToken());
    		int m = (int) Math.round((Float.parseFloat(st.nextToken()) * 100));
    		
    		if(n == 0) break;
   
    		int[][] dp = new int[n+1][m+1];
    		int[] kcal = new int[n+1];
    		int[] price = new int[n+1];
    		
    		for(int i = 1; i <= n; i++) {
    			st = new StringTokenizer(br.readLine());
    			kcal[i] = Integer.parseInt(st.nextToken());
    			price[i] = (int) Math.round((Float.parseFloat(st.nextToken()) * 100));
    		}
    		
    		for(int i = 1; i <= n; i++) {
    			for(int j = 0; j <= m; j++) {
    				int p = price[i];
    				int k = kcal[i];
					if(j - p >= 0) {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-p] + k);
					}
					else {
						dp[i][j] = dp[i-1][j];
					}
    			}
    		}
    		
    		bw.write(dp[n][m] + "");
    		bw.newLine();
    	}
    	
    	
    	bw.flush();
    	bw.close();
    }
}