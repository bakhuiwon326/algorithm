import java.util.*;
import java.io.*;


public class Main {
	public static int n, m, answer;
	public static List<Integer>[] friends;
	public static boolean[] visited;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	
    	friends = new List[n+1];
    	for(int i = 0; i < n + 1; i++) {
    		friends[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		friends[a].add(b);
    		friends[b].add(a);
    	}
    	
    	visited = new boolean[n+1];
    	
    	answer = bfs();
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int bfs() {
    	
    	int cnt = 0;
    	int[] dist = new int[n+1];
    	for(int i = 0; i < n+1; i++) {
    		dist[i] = -1;
    	}
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(1);
    	visited[1] = true;
    	dist[1] = 0;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		if(dist[now] == 2) continue;
    		for(int i = 0; i < friends[now].size(); i++) {
    			int next = friends[now].get(i);
    			if(!visited[next]) {
    				visited[next] = true;
    				dist[next] = dist[now] + 1;
    				cnt++;
    				q.offer(next);
    			}
    		}
    	}
    	
    	return cnt;
    }
}