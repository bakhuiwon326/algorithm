import java.util.*;
import java.io.*;


public class Main {
	public static int n, m;
	public static int[] arr = new int[2001];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= n; i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// dp
    	boolean[][] dp = new boolean[n+1][n+1];
    	// 초기화
    	for(int i = 1; i <= n; i++) {
    		dp[i][i] = true;
    	}
    	for(int i = 1; i < n; i++) {
   		 	if(arr[i] == arr[i+1]) dp[i][i+1] = true;
    	}
    	
    	for(int s = n - 1; s >= 1; s--) {
    		for(int e = s + 2; e <= n; e++) {
    			if(arr[s] == arr[e] && dp[s+1][e-1] == true) {
    				dp[s][e] = true;
    			}
    		}
    	}
    	
    	m = Integer.parseInt(br.readLine());
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		if(dp[a][b]) {
    			bw.write("1");
    		}
    		else {
    			bw.write("0");
    		}
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
}