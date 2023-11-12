import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] price = new int[1001];
	static int[] dp = new int[1001];
	
	public static void main(String[] args) throws IOException{
		input();
		solve();
		printRes();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		// price
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
	}
	static void solve() {
		for(int i = 1; i <= n; i++) {
			int maxDP = -1;
			for(int j = 1; j <= i; j++) {
				maxDP = Math.max(maxDP, dp[i - j] + price[j - 1]);
			}
			dp[i] = maxDP;
		}
	}
	static void printRes() throws IOException{
		 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		 bw.write(String.valueOf(dp[n]));
		 bw.flush();
		 bw.close();
	}
}