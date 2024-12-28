import java.util.*;
import java.io.*;

public class Main {
	public static int n;
	public static List<Integer> origin;
	public static Set<Integer> noDupli;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());
    	
    	origin = new ArrayList<>();
    	for(int i = 0; i < n; i++) {
    		int val = Integer.parseInt(st.nextToken());
    		origin.add(val);
    	}
    	
    	noDupli = new HashSet<>(origin);
    	
    	List<Integer> sorted = new ArrayList<>(noDupli);
    	sorted.sort(Comparator.naturalOrder()); // 인덱스값이 Xi > Xj를 만족하는 Xj 개수이다.

    	// origin 각각의 원소를, sorted 배열에서 찾아 그 인덱스를 기록한다.
    	// 즉 정렬된 sorted를 binary search 해야함
    	for(int i = 0; i < n; i++) {
    		int target = origin.get(i);
    		int idx = binarySearch(target, sorted);
    		bw.write(idx + " ");
    	}
    	
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int binarySearch(int target, List<Integer> sorted) {
    	
    	int lt = 0;
    	int rt = sorted.size();
    	
    	while(lt < rt) {
    		int mid = (lt + rt) / 2;
    		if(sorted.get(mid) >= target) {
    			rt = mid;
    		} else {
    			lt = mid + 1;
    		}
    	}
    	
    	return lt;
    }
    
}