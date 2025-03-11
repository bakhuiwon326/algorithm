import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int[] number;
	public static int[] count;
	public static boolean[] visit;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	count = new int[n+1]; // 숫자(idx)가 [lt, rt] 범위에 몇개 존재하는지 확인하는 것
    	number = new int[n];
    	visit = new boolean[100001];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		number[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int lt = 0;
    	int rt = 0;
    	long cnt = 0;
    	
    	for(lt = 0; lt < n; lt++) {
    		while(rt < n && !visit[number[rt]]) {
    			visit[number[rt]] = true;
    			rt++;
    		}
    		// [lt, rt-1]까지 중복이 없는 연속순열
    		cnt += (rt - lt);
    		visit[number[lt]] = false;
    	}


//    	int lt = 0;
//    	int rt = 1;
//    	long cnt = 0;
//    	while(lt < n) {
//    		while(rt < n && number[lt] != number[rt]) {
//    			rt++;
//    		}
//    		// [lt, rt-1]까지 중복이 없는 연속 순열
//    		cnt += (rt - lt);
//    		lt++;
//    	}
    	
    	bw.write(cnt + "");
    	bw.flush();
    	bw.close();
    	
    }
}