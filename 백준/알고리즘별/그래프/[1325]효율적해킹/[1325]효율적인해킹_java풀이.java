import java.util.*;
import java.io.*;


public class Main {
	public static int n, m, maxVal;
	public static boolean[] visited;
	public static int[] res;
	public static List<Integer>[] adj;
	public static List<Integer> count = new ArrayList<>();
	public static Deque<Integer> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	visited = new boolean[n+1];
    	adj = new List[n+1];
    	res = new int[n+1];
    	
    	for(int i = 0; i <= n; i++) {
    		adj[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		adj[b].add(a);
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		res[i] = bfs(i);
    	}
    	
    	for(int i = 1; i<= n; i++) {
    		if(res[i] == maxVal) {
    			bw.write(i + " ");
    		}
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs(int start) {
    	
    	visited = new boolean[n+1];
    	
    	q.offer(start);
    	visited[start] = true;
    	int cnt = 1;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		for(int i = 0; i < adj[now].size(); i++) {
    			int next = adj[now].get(i);
    			if(!visited[next]) {
    				cnt++;
    				visited[next] = true;
    				q.offer(next);
    			}
    		}
    	}
    	
    	if(cnt > maxVal) maxVal = cnt;
    	
    	return cnt;
    	
    }
}