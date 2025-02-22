import java.util.*;
import java.io.*;

class Bus{
	int dest; int cost;
	public Bus(int dest, int cost) {
		this.dest = dest;
		this.cost = cost;
	}
}

class Node implements Comparable<Node>{
	int node; int sum;
	public Node(int node, int sum) {
		this.node = node;
		this.sum = sum;
	}
	public int compareTo(Node other) {
		return this.sum - other.sum;
	}
}

public class Main {
	public static int n, m;
	public static List<Bus>[] busLine;
	public static int[] weight;
	public static int[] come;
	public static List<Integer> path = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	busLine = new List[n+1];
    	for(int i = 0; i <= n; i++) {
    		busLine[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		int c = Integer.parseInt(st.nextToken());
    		busLine[a].add(new Bus(b, c));
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	
    	int s = Integer.parseInt(st.nextToken());
    	int e = Integer.parseInt(st.nextToken());
    	
    	weight = new int[n+1];
    	come = new int[n+1];
    	
    	for(int i = 0; i <= n; i++) {
    		weight[i] = Integer.MAX_VALUE;
    	}
    	
    	dijkstra(s, e);
    	
    	bw.write(weight[e] + "");
    	bw.newLine();
    	
    	int city = e;
    	path.add(city);
    	while(city != s) {
    		path.add(come[city]);
    		city = come[city];
    	}
    	
    	bw.write(path.size() + "");
    	bw.newLine();
    	
    	Collections.reverse(path);
    	
    	for(int i = 0;  i < path.size(); i++) {
    		bw.write(path.get(i) + " ");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void dijkstra(int start, int end) {
    	PriorityQueue<Node> pq = new PriorityQueue<>();
    	pq.offer(new Node(start, 0));
    	weight[start] = 0;
    	
    	while(!pq.isEmpty()) {
    		
    		Node now = pq.poll();
    		
    		if(weight[now.node] < now.sum) continue; // 이거 없으면 시간초과
    		
    		for(int i = 0; i < busLine[now.node].size(); i++) {
    			Bus bus = busLine[now.node].get(i);
    			int next = bus.dest;
    			int cost = bus.cost;
    			if(weight[next] > weight[now.node] + cost) {
    				weight[next] = weight[now.node] + cost;
    				come[next] = now.node;
    				pq.offer(new Node(next, weight[next]));
    			}
    		}
    	}
    }
}