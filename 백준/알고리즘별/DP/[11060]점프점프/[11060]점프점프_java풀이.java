import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static int road[] = new int[1001];
	public static int dp[] = new int[1001];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	n = Integer.parseInt(br.readLine());
    	
    	// road
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= n; i++) {
    		road[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	// dp
    	for(int i = 0; i <= n; i++) {
    		dp[i] = Integer.MAX_VALUE;
    	}
    	dp[1] = 0;
    	
    	for(int pos = 1; pos < n; pos++) {
    		if(dp[pos] == Integer.MAX_VALUE) continue;
    		for(int jump = 1; jump <= road[pos]; jump++) {
    			int next = pos + jump;
    			if(next > n) break;
    			dp[next] = Math.min(dp[next], dp[pos] + 1);
    		}
    	}
    	
    	int answer = dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}