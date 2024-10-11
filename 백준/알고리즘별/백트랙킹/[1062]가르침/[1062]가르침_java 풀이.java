import java.io.*;
import java.util.*;

public class Main {
	public static int answer = -1;
    public static int n;
    public static int k;
    public static List<String> wordList = new ArrayList<>();
    public static boolean[] selected = new boolean[26];
    public static boolean[] check = new boolean[21];
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, k
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	// word
    	for(int i = 0; i < n; i++) {
    		String origin = br.readLine();
    		StringBuilder sb = new StringBuilder();
    		sb.append(origin.substring(0, 5));
    		sb.append(origin.substring(sb.length() - 4));
    		wordList.add(sb.toString()); // 시간초과 방지를 위해 중간 단어만 저장한다.
    	}
    	
    	selected['a' - 'a'] = true;
    	selected['c' - 'a'] = true;
    	selected['i' - 'a'] = true;
    	selected['n' - 'a'] = true;
    	selected['t' - 'a'] = true;
    	
    	if(k < 5) answer = 0;
    	else if(k == 5) answer = getAvailWordCount();
    	else if(k == 26) answer = n;
    	else answer = combination(0, 5);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    }
    
    public static int combination(int idx, int depth) {
    	if(depth == k) {
    		return getAvailWordCount();
    	}
    	int res = -1;
    	for(int i = idx; i < 26; i++) {
    		if(selected[i]) continue;
    		selected[i] = true;
    		int tmp = combination(i + 1, depth + 1);
    		if(tmp > res) res = tmp;
    		selected[i] = false;
    	}
    	return res;
    }
    
    public static int getAvailWordCount() {
    	// selected 문자로 몇 개의 단어를 읽을 수 있는가?
    	int cnt = 0;
    	for(String word : wordList) {
    		boolean canLearn = true;
    		for(int i = 0; i < word.length(); i++) {
    			char c = word.charAt(i);
    			if(!selected[c - 'a']) {
    				canLearn = false;
    				break;
    			}
    		}
    		if(canLearn) cnt++;
    	}
    	return cnt;
    }
}