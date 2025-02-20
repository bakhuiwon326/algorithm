import java.util.*;
import java.io.*;

class Info{
	int subin; int bro; int time;
	public Info(int subin, int bro, int time) {
		this.subin = subin;
		this.time = time;
		this.bro = bro;
	}
}

public class Main {
	public static int n, k;
	public static boolean[][] visited = new boolean[500001][2];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	if(n == k) System.out.println(0);
    	else System.out.println(bfs());
    	
    }
    public static int bfs() {
    	
    	Deque<Info> q = new ArrayDeque<>();
    	q.offer(new Info(n, k, 0));
    	visited[n][0] = true; // visited는 수빈이의 방문 여부를 기록.
    	
    	while(!q.isEmpty()) {
    		
    		Info now = q.poll();
    		int bro = now.bro + now.time;
    		
    		if(bro > 500000) return -1;
    		if(visited[bro][now.time % 2]) return now.time;
    		
    		int[] next = {now.subin - 1, now.subin + 1, now.subin * 2};
    		for(int i = 0; i < 3; i++) {
    			if(0 <= next[i] && next[i] <= 500000 && !visited[next[i]][(now.time + 1) % 2]) {
    				visited[next[i]][(now.time + 1) % 2] = true;
    				q.offer(new Info(next[i], bro, now.time + 1));
    			}
    		}
    		
    	}
    	
    	return -1;
    	
    }
}