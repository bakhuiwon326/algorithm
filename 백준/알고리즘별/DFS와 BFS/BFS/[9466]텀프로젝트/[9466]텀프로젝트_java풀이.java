import java.util.*;
import java.io.*;

class Info{
	int vertex;
	int number; // 팀 내 번호
	public Info(int vertex, int number) {
		this.vertex = vertex;
		this.number = number;
	}
}

public class Main {
	public static int[] student;
	public static int t;
	public static int teamMemberCnt = 0;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	t = Integer.parseInt(br.readLine());
    	
    	while(t-- > 0) {
    		// 초기화
    		teamMemberCnt = 0;
    		int n = Integer.parseInt(br.readLine());
    		st = new StringTokenizer(br.readLine());
    		boolean[] visited = new boolean[n + 1];
    		student = new int[n+1];
    		// 입력
    		for(int i = 1; i <= n; i++) {
    			student[i] = Integer.parseInt(st.nextToken());
    		}
    		
    		// bfs
    		for(int i = 1; i <= n; i++) {
    			if(!visited[i]) {
    				bfs(i, visited);
    			}
    		}
    		
    		int answer = n - teamMemberCnt;
    		bw.write(answer + "");
    		bw.newLine();
    		
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }

	public static void bfs(int start, boolean[] visited) {
		Set<Integer> nodes = new HashSet<>();
		nodes.add(start);

		int now = start;
		while(true) {
			visited[now] = true;
			int next = student[now];
			if(visited[next]) {
				// 싸이클 존재
				if(nodes.contains(next)) {
					int curr = next;
					while(true) {
						teamMemberCnt++;
						curr = student[curr];
						if(curr == next) break;
					}
				}
				break;
			}
			else {
				visited[next] = true;
				nodes.add(next);
				now = next;
			}
			
		}
	}
}

