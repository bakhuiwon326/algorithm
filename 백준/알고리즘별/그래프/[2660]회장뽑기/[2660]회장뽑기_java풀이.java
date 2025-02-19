import java.util.*;
import java.io.*;

class Info implements Comparable<Info>{
	int node; int sum;
	public Info(int node, int sum) {
		this.node = node;
		this.sum = sum;
	}
	public int compareTo(Info other) {
		if(this.sum == other.sum) return this.node - other.node;
		else return this.sum - other.sum;
	}
}

public class Main {
	public static int n;
	public static List<Integer>[] friends;
	public static List<Info> res = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	friends = new List[n+1];
    	
    	for(int i = 0; i <= n; i++) {
    		friends[i] = new ArrayList<>();
    	}
    	
    	while(true) {
    		
    		st = new StringTokenizer(br.readLine());
    		int a = Integer.parseInt(st.nextToken());
    		int b = Integer.parseInt(st.nextToken());
    		
    		if(a == -1 && b == -1) break;
    		
    		friends[a].add(b);
    		friends[b].add(a);
    		
    	}
    	
    	for(int i = 1; i <= n; i++) {
    		bfs(i);
    	}
    	
    	Collections.sort(res);
    	
    	int minSum = res.get(0).sum;
    	int cnt = 0; 
    	for(int i = 0; i < res.size(); i++) {
    		//System.out.printf("(%d,%d) -> ", res.get(i).node, res.get(i).sum);
    		if(res.get(i).sum > minSum) {
    			break;
    		}
    		cnt++;
    	}
    	//System.out.printf("%d명\n", rt+1);
    	
    	bw.write(minSum + " " + cnt);
    	bw.newLine();
    	
    	for(int i = 0; i < res.size(); i++) {
    		if(res.get(i).sum > minSum) {
    			break;
    		}
    		bw.write(res.get(i).node + " ");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs(int start) {
    	
    	int sum = -1;
    	int[] dist = new int[n+1];
    	for(int i = 0; i <= n; i++) dist[i] = -1;
    	Deque<Integer> q = new ArrayDeque<>();
    	
    	q.offer(start);
    	dist[start] = 0;
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		
    		sum = Math.max(sum, dist[now]);
    		
    		for(int i = 0; i < friends[now].size(); i++) {
    			int next = friends[now].get(i);
    			if(dist[next] == -1) {
    				dist[next] = dist[now] + 1;
    				q.offer(next);
    			}
    		}
    	}
    
    	
    	res.add(new Info(start, sum));
    	
    }
}