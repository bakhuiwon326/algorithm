import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp = new int[10001][4];
	static int t;
	static int max;
	static List<Integer> n = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		input();
		solve();
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// t
		t = Integer.parseInt(br.readLine());
		// input
		for(int i = 0; i < t; i++) {
			n.add(Integer.parseInt(br.readLine()));
			max = Math.max(max, n.get(i));
		}
	}
	static void solve() {
		// dp 채우기
		dp[1][1] = 1;
		dp[2][1] = dp[1][1];
		dp[2][2] = 1;
		dp[3][1] = dp[2][1];
		dp[3][2] = dp[1][1] + dp[1][2];
		dp[3][3] = 1;
		for(int i = 4; i <= max; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1] + dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < t ; i++) {
			int num = n.get(i);
			int res = dp[num][1] + dp[num][2] + dp[num][3];
			bw.write(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}