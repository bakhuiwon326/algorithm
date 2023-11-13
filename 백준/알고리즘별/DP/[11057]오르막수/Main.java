import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int mod = 10007;
	static int[][] dp = new int[1001][10];
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	static void input()throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
	}
	static void solve() {
		int preSum = 0;
		// n이 1일때
		for(int i = 0 ; i <= 9; i++) dp[1][i] = 1;
		preSum = 10;
		// n이 2이상일 때
		for(int i = 2; i <= n; i++) {
			for(int j = 0; j <= 9 ; j++) {
				for(int k = j ; k <= 9; k++) {
					dp[i][j] += dp[i-1][k] % mod;
				}
			}
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int sum = 0;
		for(int i = 0; i <= 9; i++) sum += dp[n][i];
		sum %= mod;
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
	}
}