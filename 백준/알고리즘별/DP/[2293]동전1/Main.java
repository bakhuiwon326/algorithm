import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static List<Integer> coin = new ArrayList<>();
	static int[] dp = new int[10001];
	public static void main(String[] args) throws IOException {
		input();
		solve();
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// n, k
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// coin
		for(int i = 0; i < n; i++) {
			coin.add(Integer.parseInt(br.readLine()));
		}
		coin.sort(Comparator.naturalOrder());
	}
	static void solve() {
		dp[0] = 1;
		for(int i = 0; i < n; i++) {
			for(int value = coin.get(i); value <= k; value++) {
				dp[value] += dp[value - coin.get(i)];
			}
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(dp[k]));
		bw.flush();
		bw.close();
	}
}