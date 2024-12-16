import java.util.*;
import java.io.*;


public class Main {
	public static int[][] dp = new int[1001][1001];
	public static String A;
	public static String B;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	
    	A = br.readLine();
    	B = br.readLine();
    	
    	// dp[i][j] = i번째까지의 A prefix, j번째까지의 B prefix 에서의 LCS 길이
    	
    	
    	
    	for(int i = 1; i <= A.length(); i++) {
    		for(int j = 1; j <= B.length(); j++) {
    			if(A.charAt(i-1) == B.charAt(j-1)) {
    				dp[i][j] = dp[i - 1][j - 1] + 1;
    			}
    			else {
    				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    			}
    		}
    	}
    	
    	int answer = dp[A.length()][B.length()];
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    	
    	
    }
}