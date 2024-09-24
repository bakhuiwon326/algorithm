import java.io.*;
import java.util.*;

public class Main {
	
	public static int n;
	public static int m;
	public static List<Integer> card = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// card
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		card.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	card.sort(Comparator.naturalOrder());    		
    	
    	// m
    	st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < m; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		boolean res = binarySearch(num);
    		if(res) bw.write("1 ");
    		else bw.write("0 ");
    	}
    	bw.flush();
    	bw.close();
    }
    
    public static boolean binarySearch(int num) {
    	int lt = 0;
    	int rt = n - 1;
    	while(lt <= rt) {
    		int mid = (lt + rt) / 2;
    		if(card.get(mid) == num) {
    			return true;
    		}
    		else if(card.get(mid) > num) {
    			rt = mid - 1;
    		}
    		else {
    			lt = mid + 1;
    		}
    	}
    	return false;
    }
    
    
}