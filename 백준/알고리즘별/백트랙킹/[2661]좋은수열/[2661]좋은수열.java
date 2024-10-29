import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static String answer; 
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// n
    	n = Integer.parseInt(br.readLine());
    	
    	backtracking("");
    	
    }
    
    public static boolean backtracking(String numStr) {
    	
    	if(numStr.length() == n) {
    		if(isGood(numStr)) {
    			System.out.println(numStr);
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	
    	// n자리 숫자 만들기
    	for(int i = 1; i <= 3; i++) {
    		StringBuilder sb = new StringBuilder(numStr);
    		sb.append(i);
    		if(isGood(sb.toString())) {
    			boolean res = backtracking(sb.toString());
    			if(res) return true;
    		}
    	}  
    	
    	return false;
    	
    }
    
    public static boolean isGood(String numStr) {
    	int s = numStr.length();
    	for(int i = 1; i <= s / 2; i++) {
    		String str1 = numStr.substring(s - 2 * i, s - i);
    		String str2 = numStr.substring(s - i, s);
    		if(str1.equals(str2)) return false;
    	}
    	return true;
    }
}