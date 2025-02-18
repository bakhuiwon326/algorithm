import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static int[][] answer;
	public static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	answer = new int[n][n];
    	graph = new List[n];
    	for(int i = 0; i < n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < n; j++) {
    			int val = Integer.parseInt(st.nextToken());
    			if(val == 1) {
    				graph[i].add(j);
    			}
    		}
    	}
    	
    	for(int i = 0; i < n; i++) {
    		bfs(i);
    	}
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			bw.write(answer[i][j] + " ");
    		}
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    }
    
    public static void bfs(int start) {
    	//System.out.println("***");
    	boolean[] visited = new boolean[n];
    	Deque<Integer> q = new ArrayDeque<>();
    	
    	q.offer(start);
    	visited[start] = true;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		//System.out.println(now);
    		for(int i = 0; i < graph[now].size(); i++) {
    			int next = graph[now].get(i);
    			if(next == start) {
    				answer[start][start] = 1;
    			}
    			if(!visited[next]) {
    				visited[next] = true;
    				answer[start][next] = 1;
    				q.offer(next);
    			}
    		}
    	}
    }
}