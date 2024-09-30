import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static List<Integer> liquid = new ArrayList<>();
	static int res1, res2;
	static int minDiff = 2147483647;
	public static void main(String[] args) throws IOException{
		input();
		solve();
		output();
	}
	public static void input() throws IOException{
		BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			liquid.add(Integer.parseInt(st.nextToken()));
		}
	}
	public static void solve() {
		liquid.sort(Comparator.naturalOrder());
		int lt = 0;
		int rt = n - 1;
		while(lt < rt) {
			int diff = liquid.get(lt) + liquid.get(rt);
			int diffAbs = Math.abs(diff);
			if(diffAbs < minDiff) {
				minDiff = diffAbs;
				res1 = liquid.get(lt);
				res2 = liquid.get(rt);
			}
			// 포인터 옮기기
			if(diff == 0) break;
			else if(diff < 0) lt++;
			else if(diff > 0) rt--;
		}
	}
	public static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(res1 + " " + res2);
		bw.flush();
		bw.close();
	}
}