import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int m;
    public static int k;
    public static int[][] arr;
    public static int[][] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	arr = new int[n + 1][m + 1];
    	dp = new int[n + 1][m + 1];
    	
    	for(int i = 1; i <= n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 1; j <= m; j++) {
    			arr[i][j] = Integer.parseInt(st.nextToken()); 
    			dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i-1][j-1] + arr[i][j];
    		}
    	}
    	
    	
    	k = Integer.parseInt(br.readLine());
    	
    	while(k-- > 0) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		int d = Integer.parseInt(st.nextToken());
    		int sum = dp[c][d] - dp[a-1][d] - dp[c][b-1] + dp[a-1][b-1];
    		bw.write(sum + "");
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    }
    
   
}