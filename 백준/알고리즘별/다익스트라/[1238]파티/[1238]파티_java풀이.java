import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	
	int no; int dist;
	
	public Node(int no, int dist) {
		this.no = no;
		this.dist = dist;
	}
	
	public int compareTo(Node other) {
		return this.dist - other.dist;
	}
	
}

public class Main {
	public static int n, m, x;
	public static int[] dist1;
	public static int[] dist2;
	public static List<Integer>[] adj;
	public static int[][] cost;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	x = Integer.parseInt(st.nextToken());
    	
    	dist1 = new int[n+1];
    	dist2 = new int[n+1];
    	adj = new List[n+1];
    	cost = new int[n+1][n+1];
    	for(int i = 0; i <= n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		
    		adj[a].add(b);
    		cost[a][b] = c;
    		
    	}
    	
    	// x로 갈 때
    	for(int i = 1; i <= n; i++) {
    		if(i == x) continue;
    		int dist = dijkstra(i, x);
    		dist1[i] = dist;
    	}
    	
    	// x에서 올 때
    	for(int i = 1; i <= n; i++) {
    		if(i == x) continue;
    		int dist = dijkstra(x, i);
    		dist2[i] = dist;
    	}
    	
    	int maxDist = 0;
    	for(int i = 1; i <= n; i++) {
    		if(i == x) continue;
    		if(maxDist < dist1[i] + dist2[i]) {
    			maxDist = dist1[i] + dist2[i];
    		}
    	}
    	
    	bw.write(maxDist + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int dijkstra(int src, int dest) {
    	
    	int[] dist = new int[n+1];
    	for(int i = 0; i <= n; i++) {
    		dist[i] = Integer.MAX_VALUE;
    	}
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.offer(new Node(src, 0));
    	dist[src] = 0;
    	
    	while(!pq.isEmpty()) {
    		Node now = pq.poll();
    		if(now.no == dest) return now.dist;
    		for(int i = 0; i < adj[now.no].size(); i++) {
    			int next = adj[now.no].get(i);
    			int c = cost[now.no][next];
    			if(dist[next] > dist[now.no] + c) {
    				dist[next] = dist[now.no] + c;
    				pq.offer(new Node(next, dist[next]));
    			}
    		}
    	}
    	return -1;
    }
   
}