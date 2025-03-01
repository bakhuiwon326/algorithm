import java.util.*;
import java.io.*;


public class Main {
	public static int n, m;
	public static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	
    	graph = new List[n+1];
    	
    	for(int i = 0 ; i <= n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		graph[a].add(b);
    		graph[b].add(a);
    	}
    	
    	// bfs
    	int[] dist = new int[n+1];
    	for(int i = 0; i <= n; i++) {
    		dist[i] = -1;
    	}
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(1);
    	dist[1] = 0;
    	
    	int maxDist = -1;
    	int node = -1;
    	
    	while(!q.isEmpty()) {
    		
    		int now = q.poll();
    		
    		if(dist[now] > maxDist) {
    			maxDist = dist[now];
    			node = now;
    		}
    		else if(dist[now] == maxDist && 1 != now && now < node) {
    			node = now;
    		}
    		
    		for(int i = 0; i < graph[now].size(); i++) {
    			int next = graph[now].get(i);
    			if(dist[next] == -1) {
    				dist[next] = dist[now] + 1;
    				q.offer(next);
    			}
    		}
    	}
    	
    	int cnt = 0;
    	for(int i = 1; i <= n; i++) {
    		if(dist[i] == maxDist) cnt++;
    	}
    	
    	bw.write(node + " " + maxDist + " " + cnt);
    	bw.flush();
    	bw.close();
    	
    }
}