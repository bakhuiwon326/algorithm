import java.util.*;
import java.io.*;


public class Main {
	public static int n, k;
	public static int maxPos;
	public static int[][] range;
	public static int a, b;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	range = new int[n][2];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	range[i][0] = a;
        	range[i][1] = b;
        	maxPos = Math.max(maxPos, b);
    	}
    	
    	int lt = 0; 
    	int rt = 1;
    	int sum = 0;
    	
    	for(lt = 0 ; lt < maxPos; lt++) {
    		while(rt <= maxPos && sum < k) {
    			for(int i = 0; i < n; i++) {
    				if(range[i][0] < rt && rt <= range[i][1]) {
    					sum++;
    				}
    			}
    			rt++;
    		}
    		// [lt, rt-1]의 sum이 k보다 크거나 같음
    		if(k == sum) {
    			a = lt;
    			b = rt-1;
    			break;
    		}else {
    			for(int i = 0; i < n; i++) {
    				if(range[i][0] <= lt && lt < range[i][1]) {
    					sum--;
    				}
    			}
    		}
    		
    	}
    	
    	bw.write(a + " " + b);
    	bw.flush();
    	bw.close();
    	
    }
}