import java.util.*;
import java.io.*;


public class Main {
	public static int n, answer;
	public static String str;
	public static int[] dp = new int[5001];
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	str = br.readLine();
    	n = str.length();
    	
    	if(!isNumberStr(str) || str.charAt(0) == '0') {
    		answer = 0;
    	}
    	else {
    		
    		dp[0] = 1;
    		dp[1] = 1; // 본인 단독
    		
    		
    		for(int i = 2; i <= n; i++) {
    			
    			// 분리 가능
    			// 0이면 혼자 존재 불가능..
    			// 0이 아니면 혼자 존재 가능
    			if(str.charAt(i-1) != '0') {
    				dp[i] += dp[i-1];     				
    			}
    			
    			// 붙여
    			// 이전 dp 기록들에 대해, 0 혼자 분리된 경우는 포함하지 않아!
    			// 따라서 붙인 숫자는 10 이상인 것이 자명!
    			
    			StringBuilder sb = new StringBuilder();
    			sb.append(str.charAt(i-2)).append(str.charAt(i-1));
    			
    			int num = Integer.parseInt(sb.toString());
    			
    			if(10 <= num && num <= 26) {
    				dp[i] += dp[i-2];
    			}
    			
    			dp[i] %= 1000000;
    		}
    		answer = dp[n];
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean isNumberStr(String numberStr) {
    	for(int i = 0; i < numberStr.length(); i++) {
    		if(!Character.isDigit(numberStr.charAt(i))) {
    			return false;
    		}
    	}
    	return true;
    }
}