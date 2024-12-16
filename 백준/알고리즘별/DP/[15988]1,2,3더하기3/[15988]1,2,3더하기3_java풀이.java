import java.util.*;
import java.io.*;


public class Main {
	public static int T;
	public static long[] dp;
	public static List<Integer> nList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	T = Integer.parseInt(br.readLine());
    	
    	int maxN = -1;
    	for(int i = 0; i < T; i++) {
    		int n = Integer.parseInt(br.readLine());
    		nList.add(n);
    		maxN = Math.max(maxN, n);
    	}
    	
    	dp = new long[maxN + 1];
    	
    	dp[1] = 1;
    	dp[2] = 2;
    	dp[3] = 4;
    	
    	for(int i = 4; i <= maxN; i++) {
    		dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
    	}
    	
    	for(int n : nList) {
    		bw.write(dp[n] + "");
    		bw.newLine();
    	}

    	bw.flush();
    	bw.close();
    	
    }
}