import java.util.*;
import java.io.*;


public class Main {
	public static int n, k;
	public static int[] dist;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	dist = new int[200000];
    	for(int i = 0; i < 200000; i++) {
    		dist[i] = -1;
    	}
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(n);
    	dist[n] = 0;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		
    		if(now == k) break;
    		
    		int next = 2 * now;
    		if(inRange(next) && dist[next] == -1) {
    			dist[next] = dist[now] + 1;
    			q.offer(next);
    		}
    		
    		next = now + 1;
    		if(inRange(next) && dist[next] == -1) {
    			dist[next] = dist[now] + 1;
    			q.offer(next);
    		}
    		
    		next = now - 1;
    		if(inRange(next) && dist[next] == -1) {
    			dist[next] = dist[now] + 1;
    			q.offer(next);
    		}
    		
    	}
    	
    	bw.write(dist[k] + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
    
    public static boolean inRange(int i) {
    	return i >= 0 && i < 200000;
    }
}