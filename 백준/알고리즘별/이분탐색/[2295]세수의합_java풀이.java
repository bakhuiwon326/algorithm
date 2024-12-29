import java.util.*;
import java.io.*;


public class Main {
	public static int n;
	public static List<Integer> list = new ArrayList<>();
	public static List<Integer> twoSum = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	for(int i = 0; i < n; i++) {
    		list.add(Integer.parseInt(br.readLine()));
    	}
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			twoSum.add(list.get(i) + list.get(j));
    		}
    	}
    	
    	list.sort(Comparator.naturalOrder());
    	twoSum.sort(Comparator.naturalOrder());
    	
    	int answer = -1;
    	for(int i = n-1 ; i >= 0; i--) {
    		for(int j = 0; j < n; j++) {
    			int target = list.get(i) - list.get(j);
    			if(binarySearch(target, twoSum) && list.get(i) > answer ) {
    				answer = list.get(i);
    			}
    		}
    	}

    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    
    }
    
    public static boolean binarySearch(int target, List<Integer> twoSum) {
    	int lt = 0;
    	int rt = twoSum.size() - 1;
    	while(lt < rt) {
    		int mid = (lt + rt) / 2;
    		if(twoSum.get(mid) > target) {
    			rt = mid - 1;
    		}
    		else if(twoSum.get(mid) < target) {
    			lt = mid + 1;
    		}
    		else {
    			return true;
    		}
    	}
    	return false;
    }
}