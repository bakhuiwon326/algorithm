import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static List<Integer> liquid = new ArrayList<>();
    public static int minSum = 2147483647;
    public static int answerLt;
    public static int answerRt;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// liquid
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		liquid.add(Integer.parseInt(st.nextToken()));
    	}
    	
    	liquid.sort(Comparator.naturalOrder());
    	
    	int lt = 0;
    	int rt = n - 1;
    	while(lt < rt) {
    		int sum = liquid.get(lt) + liquid.get(rt);
    		int sumAbs = Math.abs(sum);
    		if(minSum > sumAbs) {
    			minSum = sumAbs;
    			answerLt = lt;
    			answerRt = rt;
    		}
    		if(sum == 0){
    			break;
    		}
    		else if(sum > 0) {
    			rt--;
    		}
    		else if(sum < 0) {
    			lt++;
    		}
    	}
    	
    	bw.write(String.valueOf(liquid.get(answerLt)));
    	bw.write(" ");
    	bw.write(String.valueOf(liquid.get(answerRt)));
    	bw.flush();
    	bw.close();
    	
    }
}