import java.util.*;
import java.io.*;


public class Main {
	public static int n, w, l;
	public static Deque<Integer> truckQ = new ArrayDeque<>();
	public static Deque<Integer> bridgeQ = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	w = Integer.parseInt(st.nextToken());
    	l = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		int weight = Integer.parseInt(st.nextToken());
    		truckQ.offer(weight);
    	}
    	
    	for(int i = 0; i < w; i++) {
    		bridgeQ.offer(0);
    	}
    	
    	int time = 0;
    	int bridgeSum = 0;
    	while(!bridgeQ.isEmpty()) {
    		
    		time++;
    		
    		// 다리 위 맨 앞 트럭이 다리에서 내려온다
    		int front = bridgeQ.poll();
    		bridgeSum -= front;
    		
    		// 트럭이 모두 지날 때까지!
    		if(!truckQ.isEmpty()) {
    			// 트럭이 다리 위헤 올라갈 수 있는 경우
    			if(bridgeSum + truckQ.peek() <= l) {
    				bridgeSum += truckQ.peek();
    				bridgeQ.offer(truckQ.poll());
    			}
    			// 못 알라가는 경우 0을 올린다.
    			else {
    				bridgeQ.offer(0);
    			}
    		}
    		
    	}
    	
    	bw.write(time + "");
    	bw.flush();
    	bw.close();
    	
    	
    	
    	
    	
    	
    	
    	
    }
}