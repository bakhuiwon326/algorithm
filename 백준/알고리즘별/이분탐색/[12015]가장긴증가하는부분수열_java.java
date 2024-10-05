import java.io.*;
import java.util.*;

public class Main {
    public static List<Integer> A = new ArrayList<>();
    public static List<Integer> X = new ArrayList<>();
    public static int n;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// A
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		int a = Integer.parseInt(st.nextToken());
    		int len = X.size();
    		if(len == 0 || X.get(len - 1) < a) {
    			X.add(a);
    		}
    		else if(X.get(len - 1) > a) {
    			int idx = binarySearch(a);
    			X.set(idx, a);
    		}
    	}
    	
    	bw.write(String.valueOf(X.size()));
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int binarySearch(int a) {
    	int lt = 0;
    	int rt = X.size() - 1;
    	while(lt < rt){
    		int mid = (lt + rt) / 2;
    		if(a <= X.get(mid)) {
    			rt = mid;
    		}
    		else {
    			lt = mid + 1;
    		}
    	}
    	return lt;
    }
    
    
}