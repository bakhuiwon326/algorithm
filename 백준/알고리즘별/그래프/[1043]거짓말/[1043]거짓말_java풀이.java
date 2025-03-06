import java.util.*;
import java.io.*;


public class Main {
	
	public static int n, m, answer;
	public static List<Integer>[] participant;
	public static Set<Integer>[] party; // 각 사람마다 참석하는 파티 기록
	public static boolean[][] isMeet; // 서로가 파티에서 만날 수 있는 관계인지 확인
	public static boolean[] visited;
	public static List<Integer> friend; // 진실을 아는 사람
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	isMeet = new boolean[n+1][n+1];
    	party = new Set[n+1];
    	visited = new boolean[n+1];
    	friend = new ArrayList<>();
    	participant = new List[m];
    	
    	for(int i = 0; i <= n; i++) {
    		party[i] = new HashSet<>();
    	}
    	
    	for(int i = 0; i < m; i++) {
    		participant[i] = new ArrayList<>(); 
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	int friendCnt = Integer.parseInt(st.nextToken());
    	for(int i = 0; i < friendCnt; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		friend.add(num); // 진실을 아는 사람들
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int participantCnt = Integer.parseInt(st.nextToken());
    		for(int j = 0; j < participantCnt; j++) {
    			int node = Integer.parseInt(st.nextToken());
    			party[node].add(i);
    			participant[i].add(node);
    		}
    	}
    	
    	// 각자 만날 수 있는지 체크
    	for(int i = 1; i <= n; i++) {
    		for(int j = 1; j <= n; j++) {
    			if(i == j) continue;
    			Set<Integer> intersection = new HashSet<>(party[i]);
    			intersection.retainAll(party[j]);
    			int s = intersection.size();
    			if(s > 0) {
    				isMeet[i][j] = true;
    				isMeet[j][i] = true;
    			}
    		}
    	}
    	
    	// 진실을 아는 사람과 함께 파티를 참석하는 사람들을 알기 위해 bfs 수행. 결과를 dist에 기록
    	for(int i = 0; i < friend.size(); i++) {
    		bfs(friend.get(i));
    	}
    	
    	for(List<Integer> list : participant) {
    		boolean flag = true;
    		for(int node : list) {
    			if(visited[node]) {
    				flag = false;
    				break;
    			}
    		}
    		if(flag) answer++;
    	}
    	
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void bfs(int start) {
    	
    	Deque<Integer> q = new ArrayDeque<>();
    	q.offer(start);
    	visited[start] = true;
    	
    	while(!q.isEmpty()) {
    		
    		int now = q.poll();
    		
    		for(int i = 1; i <= n; i++){
    			if(i == now) continue;
    			if(isMeet[now][i] && !visited[i]) {
    				visited[i] = true;
    				q.offer(i);
    			}
    		}
    				
    	}  	
    	
    }
}