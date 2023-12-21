import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 2147483647;
	static int C;
	static int[] time = {12,3,6,9};
	static int[] clockState;
	static int[][] switchState = {
			{0,1,2},
			{3,7,9,11},
			{4,10,14,15},
			{0,4,5,6,7},
			{6,7,8,10,12},
			{0,2,14,15},
			{3,14,15},
			{4,5,7,14,15},
			{1,2,3,4,5},
			{3,4,5,9,13}
	};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		// C 입력
		C = Integer.parseInt(br.readLine());
		while(C-- > 0) {
			// clockState 입력
			clockState = new int[16];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 16; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 12) clockState[i] = 0;
				else if(num == 3) clockState[i] = 1;
				else if(num == 6) clockState[i] = 2;
				else clockState[i] = 3;
			}
			// 0번 ~ 10번 스위치를 0회~3회 누르는 경우 체크
			int res = pushSwitch(0,0);
			if(res == INF) res = -1;
			bw.write(String.valueOf(res));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	static int pushSwitch(int depth, int cntRes) { // depth 번째 스위치 누르면
		if(isAllTwelve()) return cntRes;
		if(depth == 10) return INF;
		int minRes = INF;
		for(int cnt = 0; cnt <= 3; cnt++) { // depth 번째 스위치를 cnt만큼 누름
			for(int i = 0; i < switchState[depth].length; i++) {
				int idx = switchState[depth][i];
				clockState[idx] = (clockState[idx] + cnt) % 4;
			}
			minRes = Math.min(minRes, pushSwitch(depth + 1, cntRes + cnt));
			for(int i = 0; i < switchState[depth].length; i++) {
				int idx = switchState[depth][i];
				clockState[idx] = (clockState[idx] - cnt + 4) % 4;
			}
		}
		return minRes; 
	}
	static boolean isAllTwelve() {
		for(int i = 0; i < 16; i++) {
			if(clockState[i] != 0) return false;
		}
		return true;
	}
	static void printClockState() {
		for(int i = 0; i < 16; i++) {
			System.out.printf("%d ", clockState[i]);
		}
	}
}