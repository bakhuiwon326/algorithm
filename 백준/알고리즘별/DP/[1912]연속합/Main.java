import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dp = new int[100001];
	static int res;
	static int[] arr = new int[100001];
	public static void main(String[] args) throws IOException{
		input();
		solve();
		printRes();
	}
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		// arr
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	static void solve() {
		dp[0] = arr[0];
		res = dp[0];
		for(int i = 1; i < n; i++) {
			dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
			res = Math.max(dp[i], res);
		}
	}
	static void printRes() throws IOException {
		BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));
		br.write(String.valueOf(res));
		br.flush();
		br.close();
	}
}