import java.util.*;
import java.io.*;


public class Main {
	public static int n, m, answer;
	public static List<Integer> A = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	for(int i = 0; i < n; i++) {
    		A.add(Integer.parseInt(br.readLine()));
    	}
    	
    	A.sort(Comparator.naturalOrder());
    	
    	answer = Integer.MAX_VALUE;
    	int lt = 0;
    	int rt = 1;
    	while(lt <= rt && rt < n) {
    		int diff = A.get(rt) - A.get(lt);
    		if(diff < m) {
    			rt++;
    		}
    		else {
    			lt++;
    			answer = Math.min(answer, diff);
    			if(answer == m) break;
    		}
    	}
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
}