import java.util.*;
import java.io.*;


public class Main {
	public static   int mod = 1000000003;
	public static int N;
	public static int K;
	public static int[][] dp;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	N = Integer.parseInt(br.readLine());
    	K = Integer.parseInt(br.readLine());
    	
    	if(K > N/2) {
    		bw.write("0");
        	bw.flush();
        	bw.close();
        	return;
    	}
    	
    	dp = new int[N+1][K+1];
    	
    	// dp 초기화
    	for(int i = 1; i <= N; i++) {
    		dp[i][1] = i;
    		dp[i][0] = 1;
    	}
    	
    	for(int n = 3; n <= N ; n++) {
    		for(int k = 2; k <= (n+1)/2; k++) {
    			// n개의 색깔이 있을 때, n번째를 선택 or 선택안함 후의 총 선택 개수가 k개
    			// 안했을때 + 했을때
    			dp[n][k] = (dp[n-1][k] + dp[n-2][k-1]) % mod;
    		}
    	}
    	
    	int answer = 0;
    	// n번째를 선택했을 때, 1번째 선택하면 안됨 => 2 ~ n-2 범위(n-3개) 경우에 n을 색칙하니깐.
    	// dp[N-3][K-1]
    	answer += dp[N-3][K-1];
    	// 1번째를 선택했을 때, n번째 선택하면 안됨  => n번째가 없다 생각
    	// dp[N-1][K]
    	answer += dp[N-1][K];
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    
    }
}
