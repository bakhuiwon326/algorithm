import java.io.*;
import java.util.*;

public class Main {
	static String S, T;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		S = br.readLine();
		T = br.readLine();
		int res = changeStr(T);
		if(res > 0) answer = 1;
		else answer = 0;
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();		
	}
	static int changeStr(String t) { // T를 점차적으로 줄여서 S와 동일한지 체크하기!
		if(S.length() == t.length()) {
			if(t.equals(S)) {
				return 1;
			}
			else return 0;
		}
		int res = 0;
		if(t.charAt(t.length() - 1) == 'A') res += changeStr(t.substring(0,t.length() - 1));
		if(t.charAt(0) == 'B') {
			StringBuilder revSB = new StringBuilder(t.substring(1));
			String revStr = revSB.reverse().toString();
			res += changeStr(revStr);
		}
		return res;
	}
}