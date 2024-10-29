import java.io.*;
import java.util.*;

public class Main {
	
    public static int n;
    public static long answer;
    public static List<Long> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 1; i < 10; i++) {
    		backtracking(String.valueOf(i));
    	}
    	
    	list.sort(Comparator.naturalOrder());
    	
    	if(n == 0) answer = 0;
    	else if(n > list.size()) answer = -1;
    	else answer = list.get(n - 1);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    }
    
    public static void backtracking(String numStr) {
    	list.add(Long.parseLong(numStr));
    	int s = numStr.length();
    	int lastNum = numStr.charAt(s - 1) - '0';
    	for(int i = 0; i < lastNum; i++) {
    		StringBuilder sb = new StringBuilder(numStr);
    		sb.append(i);
    		String nextStr = sb.toString();
    		backtracking(nextStr);
    	}
    }
    
}