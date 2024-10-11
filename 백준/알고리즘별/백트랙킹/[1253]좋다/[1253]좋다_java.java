import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int answer;
    public static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		list.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	list.sort(Comparator.naturalOrder());
    	
    	for(int i = 0; i < n; i++) {
    		int target = list.get(i);
    		int lt = 0;
    		int rt = n - 1;
    		while(lt < rt) {
    			if(lt == i) {
    				lt++;
    				continue;
    			}
    			if(rt == i){
    				rt--;
    				continue;
    			}
    			
    			int sum = list.get(lt) + list.get(rt);
    			if(sum < target) {
    				lt++;
    			}
    			else if(sum > target) {
    				rt--;
    			}
    			else {
    				answer++;
    				break;
    			}
    		}
    	}
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}