import java.util.*;
import java.io.*;

class Info{
	int node; int dist;
	public Info(int node, int dist) {
		this.node= node;
		this.dist = dist;
	}
}

public class Main {
	public static int n, k, m, answer;
	public static List<Integer>[] graph;
	public static boolean[] visited;
	public static int[] dist;
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	graph = new List[n+m+1]; // graph[n]부터는 더미노드(하이퍼튜브)
    	for(int i = 0; i < n+m+1; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < k; j++) {
    			int station = Integer.parseInt(st.nextToken());
    			graph[n+i+1].add(station);
    			graph[station].add(n+i+1);
    		}
    	}
    	
    	visited = new boolean[n+m+1];
    	
    	int answer = bfs(1, n);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs(int start, int target) {
    	Deque<Info> q = new ArrayDeque<>();
    	q.offer(new Info(start, 1));
    	visited[1] = true;
    	
    	while(!q.isEmpty()) {
    		Info now = q.poll();
    		if(now.node == target) {
    			return now.dist;
    		}
    		for(int i = 0; i < graph[now.node].size(); i++) {
    			int next = graph[now.node].get(i);
    			if(!visited[next]) {
    				visited[next] = true;
    				q.offer(new Info(next, next > n ? now.dist : now.dist+1));
    			}
    		}
    	}
    	
    	return -1;
    }
}