import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int max = -1;
	static int[] arr = new int[1001]; 
	static int[] dp = new int[1001];
	public static void main(String[] args) throws IOException {
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
		// 수열
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}		
	}
	
	static void solve() {
		dp[0] = arr[0];
		max = dp[0];
		for(int i = 1; i < n; i++) {
			int maxDP = -1; // arr[i]보다 작은 값 arr[j] 중에 가장 dp[j]가 큰놈
			for(int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && maxDP < dp[j]) {
					maxDP = dp[j];
				}
			}
			if(maxDP == -1) dp[i] = arr[i];
			else dp[i] = maxDP + arr[i];
			max = Math.max(max, dp[i]);
		}
	}
	
	static void printRes() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(Integer.toString(max));
		bw.flush();
		bw.close();
	}
}
