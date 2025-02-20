import java.util.*;
import java.io.*;

public class Main {
	public static int n, m;
	public static int[] bacon;
	public static int[] dist;
	public static List<Integer>[] friend;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	bacon = new int[n+1];
    	dist = new int[n+1];
    	friend = new List[n+1];
    	
    	for(int i = 0; i < n+1; i++) {
    		friend[i] = new ArrayList<>();
    		dist[i] = -1;
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	friend[a].add(b);
        	friend[b].add(a);
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		bfs(i);
    	}
    	
    	int answer = 0;
    	int minBacon = Integer.MAX_VALUE;
    	for(int i = 1; i <= n; i++) {
    		if(minBacon  > bacon[i]) {
    			minBacon = bacon[i];
    			answer = i;
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs(int start) {
    	
    	for(int i = 0; i < n+1; i++) {
    		dist[i] = -1;
    	}
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(start);
    	dist[start] = 0;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		for(int i = 0; i < friend[now].size(); i++) {
    			int next = friend[now].get(i);
    			if(dist[next] == -1) {
    				dist[next] = dist[now] + 1;
    				q.offer(next);
    			}
    		}
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		bacon[start] += dist[i];
    	}
    	
    }
}