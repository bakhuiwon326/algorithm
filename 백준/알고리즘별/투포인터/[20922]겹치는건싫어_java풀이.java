import java.util.*;
import java.io.*;


public class Main {
	
	public static int n, k;
	public static int[] seq;
	public static int[] count = new int[100001];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	seq = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		seq[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int lt = 0;
    	int rt = 0;
    	int answer = 0;
    	
    	for(lt = 0; lt < n; lt++) {
    		while(rt < n && count[seq[rt]] < k) {
    			count[seq[rt]]++;
    			rt++;
    		}
    		answer = Math.max(answer, rt - lt);
    		count[seq[lt]]--;
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}