import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int k;
    public static long answer;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	//k
    	st = new StringTokenizer(br.readLine());
    	k = Integer.parseInt(st.nextToken());
    	
    	long lt = 1;
    	long rt = k;
    	
    	while(lt < rt) {
    		long mid = (lt + rt) / 2;
    		int cnt = 0;
    		for(int i = 1; i <= n ;i++) {
    			long tmp = mid / i;
    			if(tmp > n) cnt += n;
    			else cnt += tmp;
    		}
    		if(cnt >= k) rt = mid;
    		else lt = mid + 1;
    	}
    	bw.write(String.valueOf(lt));
    	bw.flush();
    	bw.close();
    }
    
}