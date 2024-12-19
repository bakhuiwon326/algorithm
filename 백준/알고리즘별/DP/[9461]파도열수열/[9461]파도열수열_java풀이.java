import java.util.*;
import java.io.*;


public class Main {
	public static int t;
	public static long[] dp = new long[101];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	t = Integer.parseInt(br.readLine());
    	
    	dp[1] = 1;
    	dp[2] = 1;
    	dp[3] = 1;
    	dp[4] = 2;
    	dp[5] = 2;
    	
    	for(int i = 6; i <= 100; i++) {
    		dp[i] = dp[i - 1] + dp[i - 5];
    	}
    	
    	for(int i = 0; i < t; i++) {
    		int n = Integer.parseInt(br.readLine());
    		bw.write(dp[n] + "");
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
}