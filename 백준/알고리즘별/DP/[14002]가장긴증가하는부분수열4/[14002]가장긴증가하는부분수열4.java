import java.util.*;
import java.io.*;


public class Main {
	
	public static List<Integer> A = new ArrayList<>();
	public static List<Integer> sorted = new ArrayList<>();
	public static int[] insertPos;
	public static int n;
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		A.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	insertPos = new int[n];
    	
    	for(int i = 0; i < n; i++) {
    		int target = A.get(i);
    		if(sorted.size() == 0 || sorted.get(sorted.size() - 1) < target) {
    			sorted.add(target);
    			insertPos[i] = sorted.size() - 1;
    			continue;
    		}
    		
    		int pos = binarySearch(target);
    		sorted.set(pos, target);
    		insertPos[i] = pos;
    	}
    	
    	Deque<Integer> answer = new ArrayDeque<>();
    	
    	bw.write(sorted.size() + "");
    	bw.newLine();
    	
    	int curIdx = sorted.size() - 1;
    	for(int i = n - 1 ; i >= 0; i--) {
    		if(insertPos[i] == curIdx) {
    			curIdx--;
    			answer.push(A.get(i));
    		}
    	}
    	
    	while(!answer.isEmpty()) {
    		bw.write(answer.pop() + " ");
    	}
    	
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int binarySearch(int target) { // targer보다 이상인 값 중 가장 왼쪽 값.
    	int lt = 0;
    	int rt = sorted.size() - 1;
    	
    	while(lt < rt) {
    		int mid = (rt + lt) / 2;
    		if(sorted.get(mid) >= target) {
    			rt = mid;
    		}
    		else {
    			lt = mid + 1;
    		}
    	}
    	
    	return lt;
    }
    
}