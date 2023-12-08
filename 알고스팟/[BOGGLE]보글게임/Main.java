import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	static int c, n;
	static char[][] board;
	static int maxLen;
	static int[] dr = {0,0,1,-1,1,1,-1,-1};
	static int[] dc = {1,-1,0,0,1,-1,1,-1};
	static Map<String, Integer> word; // word의 res 인덱스
	static int[] res;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		// c
		c = Integer.parseInt(br.readLine());
		while(c-- > 0) {
			// board 입력
			board = new char[5][5];
			for(int i = 0; i < 5; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j = 0; j < 5; j++) board[i][j] = tmp[j];
			}
			// word 입력
			word = new HashMap<>();
			maxLen = -1;
			n = Integer.parseInt(br.readLine());
			res = new int[n];
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				maxLen = Math.max(maxLen, str.length());
				word.put(str, i);
			}
			System.out.println(word);
			// solve
			solve();
			//System.out.println("------------");
			// output
			output();
		}
	}
	static void solve() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				findWord(i, j, 0, String.valueOf(board[i][j]));
			}
		}
	}
	static void findWord(int r, int c, int depth, String makedWord) {
		
		if(word.containsKey(makedWord)) {
			int idx = word.get(makedWord);
			res[idx]++;
		}
		
		if(depth == maxLen) {
			return;
		}
		
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(isInRange(nr, nc)) {
				findWord(nr, nc, depth + 1, makedWord + board[nr][nc]);
			}
		}
	}
	static boolean isInRange(int r, int c) {
		return r >= 0 && r < 5 && c >= 0 && c < 5;
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Entry<String, Integer>> list = new ArrayList<>(word.entrySet());
		Comparator<Entry<String, Integer>> cmp = new Comparator<>() {
			public int compare(Entry<String, Integer> a, Entry<String, Integer> b) {
				return Integer.compare(a.getValue(), b.getValue());
			}
		};
		list.sort(cmp);
		for(int i = 0; i < n; i++) {
			String str = list.get(i).getKey();
			int idx = list.get(i).getValue();
			String yn;
			if(res[idx] > 0) yn = "YES";
			else yn = "NO";
			bw.write(str + " " + yn);
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}