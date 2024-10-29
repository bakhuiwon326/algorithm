import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[] A;
    public static int[] idxArr;
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	n = Integer.parseInt(br.readLine());
    	
    	// A, 길이 찾기
    	A = new int[n];
    	idxArr = new int[n];
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		A[i] = Integer.parseInt(st.nextToken());
    		int s = list.size();
    		if(s == 0 || A[i] > list.get(s - 1)) {
    			list.add(A[i]);
    			idxArr[i] = list.size() - 1;
    		}
    		else {
    			int idx = binarySearch(A[i]);
    			list.set(idx, A[i]);
    			idxArr[i] = idx;
    		}
    	}
    	
    	bw.write(list.size() + "");
    	bw.newLine();
    	
    	// 가장 긴 증가하는 부분 수열 출력 - 역순으로 봐야 간단함
    	Deque<Integer> stk = new ArrayDeque<>();
    	int findIdx = list.size() - 1;
    	for(int i = n - 1; i >= 0; i--) {
    		if(idxArr[i] == findIdx) {
    			findIdx--;
    			stk.push(A[i]);
    		}
    	}
    	
    	while(!stk.isEmpty()) {
    		int a = stk.peek();
    		stk.pop();
    		bw.write(a + " ");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int binarySearch(int target) {
    	
    	int lt = 0; 
    	int rt = list.size() - 1;
    	
    	while(lt < rt) {
    		int mid  = (lt + rt) / 2;
    		if(list.get(mid) >= target) rt = mid;
    		else lt = mid + 1;
    	}
    	
    	return lt;
    	
    }
    
}