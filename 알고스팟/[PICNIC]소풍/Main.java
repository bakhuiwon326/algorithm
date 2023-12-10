import java.io.*;
import java.util.*;
import java.util.Map.Entry;


public class Main {
	static int c, n, m;
	static List<Integer> answer = new ArrayList<Integer>();
	static boolean[] isPaired; // 이미 짝 지어짐
	static boolean[][] isFriend;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		// c
		c = Integer.parseInt(br.readLine());
		while(c-- > 0) {
			// n, m;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			// 초기화
			isPaired= new boolean[n];
			isFriend = new boolean[n][n];
			// 친구 쌍 기록
			for(int i = 0; i < m; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				isFriend[a][b] = true;
				isFriend[b][a] = true;
			}
			boolean[] taken = new boolean[n];
			int res = findPair(taken);
			bw.write(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	static int findPair(boolean[] taken) {
		int a = -1;
		// 짝이 지어지지 않은 친구 중 가장 작은 id 선택
		for(int i = 0; i < n; i++) {
			if(!taken[i]) {
				a = i;
				break;
			}
		}
		if(a == -1) return 1;
		int res = 0;
		for(int b = a + 1; b < n; b++) {
			if(!taken[b] && isFriend[a][b]) {
				taken[a] = true;
				taken[b] = true;
				res += findPair(taken);
				taken[a] = false;
				taken[b] = false;
			}
		}
		return res;
	}
}