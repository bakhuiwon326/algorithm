import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int m;
    public static int answer;
    public static List<Integer>[] adj; 
    public static boolean[] visited;
    
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	visited = new boolean[n];
    	
    	adj = new ArrayList[n];
    	for(int i = 0; i < n; i++) adj[i] = new ArrayList<>();
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		adj[a].add(b);
    		adj[b].add(a);
    	}
    	
    	boolean res = false;
    	for(int idx = 0; idx < n; idx++) {
    		visited[idx] = true; 
    		res = dfs(0, idx);
    		visited[idx] = false;
    		if(res) break;
    	}
    	
    	answer = res ? 1 : 0;
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean dfs(int depth, int idx) {
    	if(depth == 4) return true;
    	for(int i = 0; i < adj[idx].size(); i++) {
    		int next = adj[idx].get(i);
    		if(visited[next]) continue;
    		visited[next] = true;
    		boolean res = dfs(depth + 1, next);
    		if(res) return true;
    		visited[next] = false;
    	}
    	return false;
    }
}