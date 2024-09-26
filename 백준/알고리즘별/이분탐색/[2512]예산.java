import java.io.*;
import java.util.*;

public class Main {
	
	public static int answer;
	
	public static int n;
	public static int m;
	public static List<Integer> request = new ArrayList<>();
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// request
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		request.add(Integer.parseInt(st.nextToken()));
    	}
    	request.sort(Comparator.naturalOrder());
    	
    	// m
    	st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	
    	// search
    	int lt = 1;
    	int rt = request.get(n-1);
    	
    	while(lt < rt) {
    		
    		int mid = (lt + rt + 1) / 2;
    		int tmpM = 0;
    		
    		for(int i = 0; i < n; i++) {
    			if(request.get(i) >= mid) tmpM += mid;
    			else tmpM += request.get(i);
    		}
    		
    		if(tmpM <= m) {
    			lt = mid;
    		}
    		else rt = mid - 1;
    		
    	}    	
    	
    	answer = lt;
    	
    	bw.write(String.valueOf(answer));
    	bw.flush();
    	bw.close();   
    	
    }
}