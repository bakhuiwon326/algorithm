import java.io.*;
import java.util.*;

public class Main {
    
	public static int answer;
	public static int n;
	public static int c;
	public static List<Integer> pos = new ArrayList<>();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, c
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	// pos
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
        	int num = Integer.parseInt(st.nextToken()); 
        	pos.add(num);
    	}
    	
    	pos.sort(Comparator.naturalOrder());
    	
    	int lt = 1;
    	int rt = pos.get(n - 1) - pos.get(0);
    	
    	while(lt < rt) {
    		int mid = (lt + rt + 1) / 2;
    		
    		int tmpC = 1;
    		int currentPos = 0; // 가장 마지막에 설치한 위치
    		for(int i = 1; i < n; i++) {
    			int diff = pos.get(i)- pos.get(currentPos);
    			if(diff >= mid) {
    				tmpC++;
    				currentPos = i;
    			}
    		}
    		
    		if(tmpC >= c) lt = mid;
    		else rt = mid - 1;
    	}
    	
    	answer = lt;
    	
    	bw.write(String.valueOf(answer));
    	bw.flush();
    	bw.close();
    	 	
    }
}