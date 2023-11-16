import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static final int INF = 2147483647;
	static int[][] cost = new int[1001][3];
	static int[][] dp = new int[1001][3]; // [몇번집][마지막으로칠했을때의색깔]
	public static void main(String[] args) throws IOException{
		init();
		input();
		solve();
		output();
	}
	static void init() {
		for(int i = 0; i < 1001; i++) {
			dp[i][0] = INF;
			dp[i][1] = INF;
			dp[i][2] = INF;
		}
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// n
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		
		// 빨 초 파 비용
		int r, g, b = 0;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			cost[i][0] = r;
			cost[i][1] = g;
			cost[i][2] = b;
		}
	}
	static void solve() {
		// 0번집 색칠
		dp[0][0] = cost[0][0];
		dp[0][1] = cost[0][1];
		dp[0][2] = cost[0][2];
		// 이후로
		for(int i = 1; i < n; i++) {
			// (i-1)번 집이 빨간색일때 => i는 G or B
			dp[i][1] = Math.min(dp[i-1][0] + cost[i][1], dp[i][1]);
			dp[i][2] = Math.min(dp[i-1][0] + cost[i][2], dp[i][2]);
			// (i-1)번 집이 초록색일때 => i는 R or B
			dp[i][0] = Math.min(dp[i-1][1] + cost[i][0], dp[i][0]);
			dp[i][2] = Math.min(dp[i-1][1] + cost[i][2], dp[i][2]);
			// (i-1)번 집이 파란색일때 => i는 R or G
			dp[i][0] = Math.min(dp[i-1][2] + cost[i][0], dp[i][0]);
			dp[i][1] = Math.min(dp[i-1][2] + cost[i][1], dp[i][1]);
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int min = INF;
		for(int i = 0; i < 3; i++) min = Math.min(min, dp[n-1][i]);
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
	}
}