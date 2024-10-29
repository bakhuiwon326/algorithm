import java.io.*;
import java.util.*;

public class Main {
    public static int k;
    public static int v;
    public static int e;
    public static boolean[] visited;
    public static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// k
    	k = Integer.parseInt(br.readLine());
    	
    	while(k-- > 0) {
    		// v, e
    		st = new StringTokenizer(br.readLine());
    		v = Integer.parseInt(st.nextToken());
    		e = Integer.parseInt(st.nextToken());
    		
    		visited = new boolean[v + 1];
    	    int[] group = new int[v + 1];
    		
    		// adj
    		adj = new List[v + 1];
    		for(int i = 0; i < v + 1; i++) {
    			 adj[i] = new ArrayList<>();
    		}
    		for(int i = 0; i < e; i++) {
    			st = new StringTokenizer(br.readLine());
    			int a = Integer.parseInt(st.nextToken());
    			int b = Integer.parseInt(st.nextToken());
    			adj[a].add(b);
    			adj[b].add(a);
    		}	
    		// backtracking
    		boolean isBiepartiGraph = true;
    		for(int i = 1;i <= v; i++) {
    			if(!visited[i] && adj[i].size() > 0) {
    				if(!backtracking(i, 0, group)) {
    					isBiepartiGraph = false;
    					break;
    				}
    			}
    		}
    		
    		if(isBiepartiGraph) bw.write("YES");
    		else bw.write("NO");
 
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean backtracking(int node, int idx, int[] group) {
    	visited[node] = true;
    	group[node] = idx;
    	for(int next : adj[node]) {
    		if(!visited[next]) {
    			boolean res = backtracking(next, (idx + 1) % 2, group);
    			if(!res) {
    				return false;
    			}
    		}
    		else if(group[next] == group[node]){
    			return false;
    		}
    	}
    	return true;
    }
 
}