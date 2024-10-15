import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp = new int[2][100001];
	static int[][] score = new int[2][100001];
	static List<Integer> answer = new ArrayList<Integer>();
	static int t;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// t
		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			// 행0
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				score[0][c] = Integer.parseInt(st.nextToken());
			}
			// 행1
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < n; c++) {
				score[1][c] = Integer.parseInt(st.nextToken());
			}
			// dp 구하기
			dp[0][0] = score[0][0];
			dp[1][0] = score[1][0];
			dp[0][1] = score[0][1] + dp[1][0];
			dp[1][1] = score[1][1] + dp[0][0];
			for(int c = 2; c < n; c++) {
				dp[0][c] = Math.max(dp[1][c - 1], dp[1][c - 2]) + score[0][c];
				dp[1][c] = Math.max(dp[0][c - 1], dp[0][c - 2]) + score[1][c];
			}
			answer.add(Math.max(dp[0][n - 1], dp[1][n - 1]));
		}
		
		printRes();
	}
	
	static void printRes() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < t; i++) {
			bw.write(answer.get(i).toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}