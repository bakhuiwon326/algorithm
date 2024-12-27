import java.util.*;
import java.io.*;


public class Main {
	
	public static int n;
	public static int[] dp;
    public static void main(String[] args) throws IOException {
    	
    	// 완벽하게 플레이한다는 것은, '처음 시작한 선수'가 이기는 쪽으로 게임이 흘러간다는 것이다.
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	dp = new int[1001];
    	
    	dp[1] = 1;
    	dp[2] = 2;
    	dp[3] = 1;
    	dp[4] = 1;
    	
    	for(int i = 5; i <= n; i++) {
    		// 1 또는 3 또는 4개 선택 전의 상태가 '창영'이 이기는 상태라면, 이번 턴은 '상근'이가 이기게 된다
    		if(dp[i-1] % 2 == 0 || dp[i-3] % 2 == 0 || dp[i-4] % 2 == 0) {
    			dp[i] = 1;
    		}
    		else {
    			dp[i] = 2;
    		}
    	}
    	
    	String answer = dp[n] == 1 ? "SK" : "CY"; 
    	bw.write(answer);
    	bw.flush();
    	bw.close();
    	
    }
}