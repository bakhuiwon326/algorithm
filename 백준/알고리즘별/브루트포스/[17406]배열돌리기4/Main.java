import java.io.*;
import java.util.*;

class Info{
	int r, c, s;
	public Info(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main {
	static boolean[] visited = new boolean[50];
	static int N, M, K;
	static int[][] originMap = new int[50][50];
	static int[] selected = new int[6];
	static List<Integer> answer = new ArrayList<>();
	static List<Info> rotateInfo = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		input();
		permutation(0);
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N,M,K
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// map
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				originMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 회전 연산
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			rotateInfo.add(new Info(r,c,s));
		}
	}
	static void solve() {
		int[][] map = new int[50][50];
		for(int i = 0; i < N; i++) map[i] = originMap[i].clone();
		for(int i = 0; i < rotateInfo.size(); i++) {
			Info info = rotateInfo.get(selected[i]);
			for(int j = 1; j <= info.s; j++) {
				int[][] tmpMap = new int[50][50];
				for(int k = 0; k < N; k++) tmpMap[k] = map[k].clone();
				int ltr = info.r - j;
				int ltc = info.c - j;
				int rdr = info.r + j;
				int rdc = info.c + j;
				// 윗면 회전
				for(int c = ltc ; c < rdc; c++) {
					map[ltr][c + 1] = tmpMap[ltr][c];
				}
				// 오른면 회전
				for(int r = ltr; r < rdr; r++) {
					map[r + 1][rdc] = tmpMap[r][rdc];
				}
				// 아랫면 회전
				for(int c = rdc; c > ltc; c--) {
					map[rdr][c - 1] = tmpMap[rdr][c];
				}
				// 왼쪽면 회전
				for(int r = rdr; r > ltr; r--) {
					map[r - 1][ltc] = tmpMap[r][ltc];
				}
			}
		}
		int res = getRowSum(map);
		answer.add(res);
	}
	static void permutation(int depth) {
		if(depth == rotateInfo.size()) {
			solve();
			return;
		}
		for(int i = 0; i < rotateInfo.size(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				permutation(depth + 1);
				visited[i] = false;
			}
		}
	}
	static int getRowSum(int[][] map) {
		int res = 2147483647;
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += map[i][j];
			}
			res = Math.min(res, sum);
		}
		return res;
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		answer.sort(Comparator.naturalOrder());
		bw.write(String.valueOf(answer.get(0)));
		bw.flush();
		bw.close();
	}
	static void printMap(int[][] map, String title) {
		System.out.printf("<< %s >>\n", title);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				System.out.printf("%d ", map[i][j]);
			}
			System.out.println();
		}
	}
}