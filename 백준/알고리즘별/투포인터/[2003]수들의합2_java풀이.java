import java.util.*;
import java.io.*;


public class Main {
	public static int n, m, answer;
	public static int[] num;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	num = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		num[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int lt = 0;
    	int rt = 0;
    	int window = num[0];
    	
    	while(lt < n && rt < n) {
    		if(window == m) {
    			answer++;
    			window -= num[lt];
    			lt++;
    		}
    		else if(window > m) {
    			window -= num[lt];
    			lt++;
    		}
    		else if(rt == n-1) {
    			rt++;
    		}
    		else {
    			rt++;
    			window += num[rt];
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}