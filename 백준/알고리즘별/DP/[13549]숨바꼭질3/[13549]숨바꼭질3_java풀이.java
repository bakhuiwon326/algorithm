import java.util.*;
import java.io.*;


public class Main {
	
	public static int n, k;
	public static int[] dist = new int[100001];
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < 100001; i++) {
    		dist[i] = -1;
    	}
    		
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(n);
    	dist[n] = 0;
    	
    	int answer = 0;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		
    		if(now == k) {
    			answer = dist[now];
    			break;
    		}
    		
    		int walk1 = now + 1;
    		if(inRange(walk1) && (dist[walk1] == -1 || dist[walk1] > dist[now] + 1)) {
    			dist[walk1] = dist[now] + 1;
    			q.offer(walk1);
    		}
    		
    		int walk2 = now - 1;
    		if(inRange(walk2) && (dist[walk2] == -1 || dist[walk2] > dist[now] + 1)) {
    			dist[walk2] = dist[now] + 1;
    			q.offer(walk2);
    		}
    	
    		int superWalk = now * 2;
    		if(inRange(superWalk) && (dist[superWalk] == -1 || dist[superWalk] > dist[now])) {
    			dist[superWalk] = dist[now];
    			q.offer(superWalk);
    		}
    
    	}
    	
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    	
    }
    
    public static boolean inRange(int pos) {
    	return pos >= 0 && pos < 100001;
    }
}