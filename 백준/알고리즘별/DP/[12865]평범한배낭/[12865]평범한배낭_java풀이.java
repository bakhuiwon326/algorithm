import java.util.*;
import java.io.*;

class Thing{
	int kg;
	int cost;
	public Thing(int kg, int cost) {
		this.kg = kg;
		this.cost = cost;
	}
}

public class Main {
	public static int n;
	public static int k;
	public static List<Thing> thingList = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n, k
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	// ThingList
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int kg = Integer.parseInt(st.nextToken());
    		int cost = Integer.parseInt(st.nextToken());
    		thingList.add(new Thing(kg, cost));
    	}
    	
    	int[] dp = new int[k + 1];
    	for(int i = 0; i < k + 1; i++) {
    		dp[i] = 0;
    	}
    	
    	for(int i = 0; i < n; i++) {
    		Thing thing = thingList.get(i);
    		for(int j = k; j >= 0; j--) {
    			if(j >= thing.kg) {
    				dp[j] = Math.max(dp[j], dp[j - thing.kg] + thing.cost);
    			}else {
    				break;
    			}
    		}
    	}
    	bw.write(dp[k] + "");
    	bw.flush();
    	bw.close();
    	
    }
}