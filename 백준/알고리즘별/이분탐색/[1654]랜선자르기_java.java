import java.io.*;
import java.util.*;

public class Main {
    
	public static int n;
	public static int m;
	public static List<Integer> aList;
	public static List<Integer> answer;
	
    public static void main(String[] args) throws IOException{
    	
    	init();
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// aList
    	st = new StringTokenizer(br.readLine());
    	for(itn i = 0; i < n; i++) {
    		aList.add(Integer.parseInt(.nextToken()));
    	}
    	
    	// m
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < m; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		if(binarySearch(num)) {
    			answer.add(1);
    		}
    		else {
    			answer.add(0);
    		}
    	}
    	
    	// output
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	for(int i = 0; i < m; i++) {
    		bw.write(String.valueOf(answer.get(i)));
    		bw.newLine();
    	}
    	
    	bw.flush();
    	bw.close();
    
    }
    
    public boolean binarySearch(int num) {
    	// idx
    	int lt = 0;
    	int rt = n - 1;
    	
    	while(lt <= rt) {
    		int mid = (lt + rt) / 2;
    		int midValue = aList.get(mid);
    		if(midValue == num) {
    			return true;
    		}
    		else if(midValue > num) {
    			rt = mid - 1;
    		}
    		else {
    			lt = mid + 1;
    		}
    	}
    	
    	return false;
    }
	
	public static void init() {
		aList = new ArrayList<>();
		answer = new ArrayList<>();
	}
    
}