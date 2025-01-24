import java.util.*;
import java.io.*;
import java.util.Map.Entry;


public class Main {
	public static int maxLen = -1;
	public static String S, T;
	public static int n;
	public static List<String> wordList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < n; i++) {
    		wordList.add(br.readLine());
    	}
    	
    	for(int i = 0; i < n - 1; i++) {
    		String word = wordList.get(i);
    		for(int j = i + 1; j < n ; j++) {
    			String next = wordList.get(j);
    			if(next.length() <= maxLen) continue;
    			int len = commonLen(word, next);
    			if(len > maxLen) {
    				maxLen = len;
    				S = word;
    				T = next;
    			}
    		}
    	}
    	
    	bw.write(S);
    	bw.newLine();
    	bw.write(T);
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int commonLen(String a, String b) {
    	int m = Math.min(a.length(), b.length());
    	int len = 0;
    	for(int i = 0; i < m ; i++) {
    		if(a.charAt(i) == b.charAt(i)) {
    			len++;
    		}
    		else {
    			break;
    		}
    	}
    	return len;
    }
    
}