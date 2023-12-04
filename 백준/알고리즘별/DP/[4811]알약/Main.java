import java.io.*;
import java.util.*;

public class Main {
	static int t = 0;
	static long [][] dp = new long[31][31];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = 0;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			bw.write(String.valueOf(recursive(n, 0)));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	public static long recursive(int w, int h) { // w, h를 쓸 수 있는 개수
		if(w == 0) return 1;
		if(dp[w][h] > 0) return dp[w][h];
		dp[w][h] = recursive(w - 1, h + 1);
		if(h > 0) dp[w][h] += recursive(w, h -1);
		return dp[w][h];
	}
}