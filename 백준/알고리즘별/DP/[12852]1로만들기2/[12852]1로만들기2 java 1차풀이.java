import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[] dp = new int[1000001];
	static List<Integer> come = new ArrayList<>();
	
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
	}
	
	static void solve() {
		dp[1] = 0;
		for(int i = 2; i <= n; i++) {
			if(i % 2 == 0 && i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3], dp[i / 2]);
				dp[i] = Math.min(dp[i], dp[i - 1]) + 1;
			}else if(i % 2 == 0) {
				dp[i] = Math.min(dp[i - 1], dp[i / 2]) + 1;
			}else if(i % 3 == 0) {
				dp[i] = Math.min(dp[i - 1], dp[i / 3]) + 1;
			}else {
				dp[i] = dp[i - 1] + 1;
			}
		}
		int tmp_n = n;
		come.add(tmp_n);
		while(tmp_n != 1) {
			if(tmp_n % 2 == 0 && tmp_n % 3 == 0) {
				if(dp[tmp_n] == dp[tmp_n / 2] + 1) {
					tmp_n /= 2;
					come.add(tmp_n);
				}else if(dp[tmp_n] == dp[tmp_n / 3] + 1) {
					tmp_n /= 3;
					come.add(tmp_n);
				}else if(dp[tmp_n] == dp[tmp_n - 1] + 1) {
					tmp_n--;
					come.add(tmp_n);
				}
			}else if(tmp_n % 2 == 0) {
				if(dp[tmp_n] == dp[tmp_n / 2] + 1) {
					tmp_n /= 2;
					come.add(tmp_n);
				}else if(dp[tmp_n] == dp[tmp_n - 1] + 1) {
					tmp_n--;
					come.add(tmp_n);
				}
			}else if(tmp_n % 3 == 0) {
				if(dp[tmp_n] == dp[tmp_n / 3] + 1) {
					tmp_n /= 3;
					come.add(tmp_n);
				}else if(dp[tmp_n] == dp[tmp_n - 1] + 1) {
					tmp_n--;
					come.add(tmp_n);
				}
			}else {
				if(dp[tmp_n] == dp[tmp_n - 1] + 1) {
					tmp_n--;
					come.add(tmp_n);
				}
			}
		}
	}
	
	static void printRes() throws IOException{
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < come.size(); i++) sb.append(come.get(i).toString() + " ");
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(dp[n]));
		bw.newLine();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}