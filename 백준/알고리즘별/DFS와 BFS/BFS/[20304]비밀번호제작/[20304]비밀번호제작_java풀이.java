import java.util.*;
import java.io.*;

public class Main {
	public static boolean[] visited;
	public static int n, m, answer, k;
	public static List<Integer> pwList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	visited = new boolean[n+1];
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < m ;i++) {
    		int pw = Integer.parseInt(st.nextToken());
    		pwList.add(pw);
    	}
    	
    	k = Integer.toBinaryString(n).length();
    	
    	bfs();
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs() {

    	int[] dist = new int[n+1];
    	Deque<Integer> q = new ArrayDeque<>();
    	
    	// pwList의 모든 번호에 대해 가장 먼 숫자를 구해야한다.
    	// q.에 모두 넣어, 동시에 이동하면서 거리를 계산한 후 가장 큰 값을 찾는다!
    	for(int i = 0; i < pwList.size(); i++) {
    		q.offer(pwList.get(i));
    		visited[pwList.get(i)] = true;
    	}
    	
    	while(!q.isEmpty()) {
    		int now = q.poll();
    		
    		answer = Math.max(answer, dist[now]);
    		
    		// now의 이진수를 한 비트씩 0<->1 바꿈!
    		// 바꾼 결과는, now 숫자와 '안전거리'가 1인 숫자임.
    		for(int i = 0; i < k; i++) {
    			int next = now ^ (1 << i);
    			if(next <= n && !visited[next]) {
    				visited[next] = true;
    				dist[next] = dist[now] + 1;
    				q.offer(next);
    			}
    		}
    		
    	}
    }
}