import java.io.*;
import java.util.*;

public class Main {
	static int L, C;
	static List<Character> alphabet = new ArrayList<>();
	static List<String> answer = new ArrayList<>();
	public static void main(String[] args) throws IOException{
		input();
		makeSecretKey(0,0,"");
		output();
	}
	static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// L, C
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// alphabet
		String str = br.readLine().replaceAll(" ", "");
		char[] strList = str.toCharArray();
		for(int i = 0; i < C; i++) alphabet.add(strList[i]);
		alphabet.sort(Comparator.naturalOrder());
	}
	static void makeSecretKey(int depth, int next, String word) {
		if(word.length() == L) {
			//System.out.println(word);
			int a = 0; // 모음 개수
			int b = 0; // 자음개수
			for(char c : word.toCharArray()) {
				if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') a++;
				else b++;
			}
			if(a >= 1 && b >= 2) {
				answer.add(word);
			}
			return;
		}
		for(int i = next; i < C; i++) {
			makeSecretKey(depth + 1, i + 1, word + alphabet.get(i));
		}
	}
	static void output() throws IOException{
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 0; i < answer.size(); i++) {
			bw.write(answer.get(i));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
}