import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static final int INF = 2147000000;
	static List<Integer> coin = new ArrayList<>();
	static int[] dp = new int[10001];
	static int answer;
	public static void main(String[] args) throws IOException{
		input();
		solve();
		//printDP();
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n, k
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			coin.add(Integer.parseInt(br.readLine()));
		}
	}
	static void solve() {
		coin.sort(Comparator.naturalOrder());
		initDP();
		dp[0] = 0;
		for(int i = 1; i <= k ; i++) {
			for(int j = 0; j < n ; j++) {
				if(i - coin.get(j)>= 0)
				dp[i] = Math.min(dp[i - coin.get(j)] + 1, dp[i]);
			}
		}
		if(dp[k] == INF) {
			answer = -1;
		}
		else answer = dp[k];
	}
	static void initDP() {
		for(int i = 0; i <= k; i++) {
			dp[i] = INF;
		}
	}
	static void printDP() {
		for(int i = 0; i <= k; i++) {
			System.out.println(dp[i] + " ");
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}