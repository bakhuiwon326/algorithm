import java.util.*;
import java.io.*;


public class Main {
	
	public static int n, m, answer;
	public static List<Integer>[] asc;
	public static List<Integer>[] desc;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	asc = new List[n+1];
    	desc = new List[n+1];
    	
    	for(int i = 0; i <= n; i++){
    		asc[i] = new ArrayList<>();
    		desc[i] = new ArrayList<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	desc[a].add(b);
        	asc[b].add(a);
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		boolean isTrivial = bfs(i);
    		if(isTrivial) {
    			answer++;
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean bfs(int start) {
    	
    	int ascCnt = 0;
    	int descCnt = 0;
    	boolean[] visited = new boolean[n+1];
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(start);
    	visited[start] = true;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		for(int i = 0; i < asc[now].size(); i++) {
    			int next = asc[now].get(i);
    			if(!visited[next]) {
    				ascCnt++;
    				visited[next] = true;
    				q.offer(next);
    			}
    		}
    	}
    	
    	visited = new boolean[n+1];
    	q.offer(start);
    	visited[start] = true;
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		for(int i = 0; i < desc[now].size(); i++) {
    			int next = desc[now].get(i);
    			if(!visited[next]) {
    				descCnt++;
    				visited[next] = true;
    				q.offer(next);
    			}
    		}
    	}
    	//System.out.printf("%d번 노드 ascCnt: %d / descCnt: %d\n", start, ascCnt, descCnt);
    	return (ascCnt > n/2 && descCnt < n/2) || (ascCnt < n/2 && descCnt > n/2);
    	
    }
}