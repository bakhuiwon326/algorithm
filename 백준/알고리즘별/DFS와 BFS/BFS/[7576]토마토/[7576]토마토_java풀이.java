import java.util.*;
import java.io.*;


public class Main {
	public static int[] dr = {0,0,1,-1};
	public static int[] dc = {1,-1,0,0};
	public static int n, m;
	public static int[][] box;
	public static Deque<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	
    	int cnt = 0;
    	box = new int[n][m];
    	
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < m; j++) {
    			box[i][j] = Integer.parseInt(st.nextToken());
    			if(box[i][j] == 1) {
    				q.offer(new int[] {i, j, 0});
    			}
    			else if(box[i][j] == 0) {
    				cnt++;
    			}
    		}
    	}
    	
    	int answer = 0;
    	
    	if(cnt > 0) {

    		while(!q.isEmpty()) {
    			int[] now = q.poll();
    			answer = now[2];
    			for(int i = 0; i < 4; i++) {
    				int nr = now[0] + dr[i];
    				int nc = now[1] + dc[i];
    				if(inRange(nr, nc) && box[nr][nc] == 0) {
    					q.offer(new int[] {nr, nc, now[2] + 1});
    					box[nr][nc] = 1;
    				}
    			}
    		}
    	
    		cnt = 0;
    		for(int i = 0; i < n; i++) {
        		for(int j = 0; j < m; j++) {
        			if(box[i][j] == 0) {
        				cnt++;
        			}
        		}
        	}
    		
    		if(cnt > 0) answer = -1;
    		
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < m;
    }
}