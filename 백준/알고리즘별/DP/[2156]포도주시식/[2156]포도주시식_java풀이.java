import java.util.*;
import java.io.*;


public class Main {
	
	public static int n;
	public static List<Integer> wine = new ArrayList<>();
	public static int[] dp;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	n = Integer.parseInt(br.readLine());
    	wine.add(-1);
    	for(int i = 0; i < n; i++) {
    		wine.add(Integer.parseInt(br.readLine()));
    	}
    	
    	dp = new int[n + 1];
    	
    	dp[0] = 0;
    	dp[1] = wine.get(1);
    	if(n > 1) dp[2] = wine.get(1) + wine.get(2);
    	
    	for(int i =  3; i <= n; i++) {
    		dp[i] = Math.max(Math.max(dp[i - 1], dp[i - 2] + wine.get(i)), dp[i - 3] + wine.get(i - 1) + wine.get(i));
    	}
    	
    	bw.write(dp[n] + "");
    	bw.flush();
    	bw.close();
    	   	
    	
    }
}