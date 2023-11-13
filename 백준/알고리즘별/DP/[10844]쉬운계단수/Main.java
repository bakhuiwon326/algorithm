import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int mod = 1000000000;
	static int[][] dp = new int[101][10]; // [숫자몇자리][끝자리숫자]
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// N
		N = Integer.parseInt(br.readLine());
	}
	static void solve() {
		// n = 1 일때
		for(int c = 1; c <= 9; c++) dp[1][c] = 1;
		
		// n이 2 이상일 때
		for(int n = 2; n <= N; n++) {
			for(int c = 0; c <= 9; c++) {
				if(c == 0) dp[n][c] = dp[n - 1][1] % mod;
				else if(c == 9) dp[n][c] = dp[n - 1][8] % mod;
				else dp[n][c] = (dp[n - 1][c - 1] + dp[n - 1][c + 1]) % mod;
			}
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long sum = 0;
		for(int c = 0; c <= 9; c++) sum += dp[N][c];
		sum %= mod;
		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
	}
}