import java.io.*;
import java.util.*;

public class Main {
	static int N, res;
	static List<Integer> heights = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			heights.add(Integer.parseInt(st.nextToken()));
		}
	}
	static void solve() {
		for(int a = 0; a < N; a++) {
			int leftCnt = 0;
			int rightCnt = 0;
			// a 기준으로 왼쪽 -> 기울기가 점점 작아져야한다.
			double preSlope = 0;
			for(int b = a - 1; b >= 0; b--) {
				double slope = (double) (heights.get(b) - heights.get(a)) / (b - a);
				if(b == a - 1 || preSlope > slope) {
					leftCnt++;
					preSlope = slope;
				}	
			}
			// a 기준으로 오른쪽 -> 기울기의 점점 커져야한다.
			preSlope = 0;
			for(int b = a + 1; b < N; b++) {
				double slope = (double) (heights.get(b) - heights.get(a)) / (b - a);
				if(b == a + 1 || preSlope < slope) {
					rightCnt++;
					preSlope = slope;
				}
			}
			res = Math.max(res, leftCnt + rightCnt);
		}
	}
	static boolean isCanSee(float slope, int preVal, int iHeight, boolean isLeft){
		if(isLeft) { // 왼쪽이니깐 slope을 빼본다.
			if(preVal - slope > iHeight) return true;
			else return false;
		}else {
			if(preVal + slope > iHeight) return true;
			else return false;
		}
	}
	static void output() throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(res));
		bw.flush();
		bw.close();
	}
}