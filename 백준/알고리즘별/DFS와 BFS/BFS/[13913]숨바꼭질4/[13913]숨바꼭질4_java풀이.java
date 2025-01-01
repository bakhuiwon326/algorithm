import java.util.*;
import java.io.*;


public class Main {
	public static int n, k;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	int[] dist = new int[100001];
    	boolean[] visited = new boolean[100001];
    	int[] come = new int[100001];
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	
    	q.offer(n);
    	visited[n] = true;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		if(now == k) {
    			bw.write(dist[now] + "");
    			bw.newLine();
    			break;
    		}
    		
    		int next1 = now - 1;
    		if(inRange(next1) && !visited[next1]) {
    			//System.out.printf("%d -> %d\n", now, next1);
    			come[next1] = now;
    			q.offer(next1);
    			visited[next1] = true;
    			dist[next1] = dist[now] + 1;
    		}
    		
    		int next2 = now + 1;
    		if(inRange(next2) && !visited[next2]) {
    			//System.out.printf("%d -> %d\n", now, next2);
    			come[next2] = now;
    			q.offer(next2);
    			visited[next2] = true;
    			dist[next2] = dist[now] + 1;
    		}
    		
    		int next3 = now * 2;
    		if(inRange(next3) && !visited[next3]) {
    			//System.out.printf("%d -> %d\n", now, next3);
    			come[next3] = now;
    			q.offer(next3);
    			visited[next3] = true;
    			dist[next3] = dist[now] + 1;
    		}
    		
    	}
    	
    	int idx = k;
    	Deque<Integer> logStk = new ArrayDeque<>();
    	logStk.push(idx);
    	while(idx != n) {
    		int prev = come[idx];
    		logStk.addFirst(prev);
    		idx = prev;
    	}
    	
    	int s = logStk.size();
    	for(int i = 0; i < s; i++) {
    		bw.write(logStk.peek() + " ");
    		logStk.removeFirst();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int pos) {
    	return pos >= 0 && pos < 100001;
    }
    
}