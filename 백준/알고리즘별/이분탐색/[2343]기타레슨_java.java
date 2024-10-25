import java.io.*;
import java.util.*;

public class Main {
	
    public static int n;
    public static int m;
    public static List<Integer> time;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	// time, prefix
    	time = new ArrayList<>();
    	st = new StringTokenizer(br.readLine());
    	int timeSum = 0;
    	int maxTime = -1;
    	for(int i = 0; i < n; i++) {
    		time.add(Integer.parseInt(st.nextToken()));
    		timeSum += time.get(i);
    		maxTime = Math.max(time.get(i), maxTime);
    	}
    	
    	int answer = Integer.MAX_VALUE;
    	int lt = maxTime;
    	int rt = timeSum;
    	while(lt < rt) {
    		int mid = (lt + rt) / 2; // 블루레이 한개당 녹음할 수 있는 길이
    		int cnt = 0;
    		int sum = 0;
    		for(int i = 0; i < n; i++) {
    			int t = time.get(i);
    			if(sum + t > mid) {
    				sum = 0;
    				cnt++;
    			}
    			sum += t;
    		}
    		if(sum > 0) cnt++;
    		
    		if(cnt <= m) {
    			rt = mid;
    		}
    		else {
    			lt = mid + 1;
    		}
    		
    	}
    	
    	answer = lt;
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}