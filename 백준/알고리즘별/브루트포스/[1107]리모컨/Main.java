import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int answer;
	static boolean[] broken = new boolean[10];
	public static void main(String[] args) throws IOException{
		// [입력]
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// N
		N = Integer.parseInt(br.readLine());
		// M
		M = Integer.parseInt(br.readLine());
		// broken
		if(M > 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < M; i++) {
				int num = Integer.parseInt(st.nextToken());
				broken[num] = true;
			}
		}
		
		// [solve]
		answer = Math.abs(100 - N);
		if(answer > 0) {
			for(int i = 0; i <= 999999; i++) {
				boolean flag = false;
				char[] number = String.valueOf(i).toCharArray();
				for(int j = 0; j < number.length; j++) {
					if(broken[number[j] - '0']) {
						flag = true;
						break;
					}
				}
				if(!flag) {
					answer = Math.min(answer, number.length + Math.abs(N - i));
				}
			}
		}
		
		// [출력]
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}