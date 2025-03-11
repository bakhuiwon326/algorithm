import java.util.*;
import java.io.*;


public class Main {
	public static int n, k, answer;
	public static int[] seq;
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
    	int oddCnt = 0;
    	
    	// [lt, rt] 홀수가 k+1개 될때까지 rt를 이동함.
    	// 홀수 k+1개가 되면, 훌수가 k개가 될 때 까지 lt를 이동한다.
    	while(rt < n) {
    		if(oddCnt <= k) {
    			if(seq[rt] % 2 == 1) oddCnt++;
    			rt++;
    		}
    		else {
    			if(seq[lt] % 2 == 1) oddCnt--;
    			lt++;
    		}
    		int evenCnt = rt - lt - oddCnt;
    		answer = Math.max(answer, evenCnt);
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}