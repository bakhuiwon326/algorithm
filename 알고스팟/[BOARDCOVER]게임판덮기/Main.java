import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dir = {
			{
				{0,0,1},
				{0,1,1}
			},
			{
				{0,0,1},
				{0,1,0}
			},
			{
				{0,1,1},
				{0,0,-1}
			},
			{
				{0,01,1},
				{0,0,1}
			}
	};
	static int c;
	static int w, h;
	static int[][] board;
	static boolean[][] covered;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		// c
		c = Integer.parseInt(br.readLine());
		while(c-- > 0) {
			// [input]
			// w, h
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			// board
			int whiteCnt = 0;
			board = new int[h][w];
			for(int i = 0; i < h; i++) {
				char[] tmp = br.readLine().toCharArray();
				for(int j = 0; j < w; j++) {
					if(tmp[j] == '.') {
						board[i][j] = 0;
						whiteCnt++;
					}
					else board[i][j] = 1;
				}
			}
			// [solve]
			int res = 0;
			if(whiteCnt % 3 == 0) {
				res = solve();
			}
			bw.write(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	static int solve() {
		// 블럭이 안놓인 가장 왼쪽 가장 위에 위치한 흰색 칸을 찾는다.
		int r = -1, c = -1;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(board[i][j] == 0) {
					r = i;
					c = j;
					break;
				}
			}
			if(r > -1) break;
		}
		// 흰색칸이 더이상 없을 때
		if(r == -1) return 1;
		// 채울 칸이 더 남았을 때
		int res = 0;
		for(int type = 0; type < 4; type++) {
			if(check(r, c, type)) {
				set(r, c, type, 1); // 1로 채우기
				res += solve();
				set(r, c, type, 0); // 0으로 채우기
			}
		}
		return res;
	}
	static boolean check(int r, int c, int type) {
		for(int i = 0; i < 3; i++) {
			int nr = r + dir[type][0][i];
			int nc = c + dir[type][1][i];
			if(!inRange(nr, nc) || board[nr][nc] > 0) return false;
		}
		return true;
	}
	static void set(int r, int c, int type, int value) {
		for(int i = 0 ; i < 3; i++) {
			int nr = r + dir[type][0][i];
			int nc = c + dir[type][1][i];
			board[nr][nc] = value;
		}
	}
	static boolean inRange(int r, int c) {
		return r >= 0 && r < h && c >= 0 && c < w; 
	}
}