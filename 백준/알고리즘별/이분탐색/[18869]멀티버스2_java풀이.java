import java.util.*;
import java.io.*;


public class Main {
	
	public static int m, n;
	public static List<Integer>[] universe = new List[100];
	public static List<Integer>[] compact = new List[100];
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		universe[i] = new ArrayList<>();
    		for(int j = 0; j < n ; j++) {
    			int size = Integer.parseInt(st.nextToken());
    			universe[i].add(size);
    		}
    	}
    	
    	// 압축 좌표 만들기
    	for(int i = 0; i < m; i++) {
    		compact[i] = new ArrayList<>();
    		Set<Integer> set = new HashSet<>(universe[i]);
    		List<Integer> sorted = new ArrayList<>(set);
    		sorted.sort(Comparator.naturalOrder());
    		for(int j = 0; j < n; j++) {
    			int target = universe[i].get(j);
    			int idx = binarySearch(sorted, target);
    			compact[i].add(idx);
    		}
    	}
    	
    	
    	int answer = 0;
    	
    	for(int a = 0; a < m-1; a++) {
    		for(int b = a+1; b < m; b++) {
    			if(Arrays.equals(compact[a].toArray(), compact[b].toArray())) answer++;
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int binarySearch(List<Integer> sorted, int target) {
    	int lt = 0;
    	int rt = sorted.size() - 1;
    	while(lt < rt) {
    		int mid = (lt + rt) / 2;
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