import java.util.*;
import java.io.*;


public class Main {
	public static int n, answer;
	public static boolean[] isPrime;
	public static List<Integer> primeNumber = new ArrayList<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	n = Integer.parseInt(br.readLine());
    	
    	prime();
    	
    	for(int i = 2; i<= n; i++) {
    		if(isPrime[i]) {
    			primeNumber.add(i);
    		}
    	}
    	
    	
    	int lt = 0;
    	int rt = 0;
    	int sum = 2;
    	
    	while(lt < primeNumber.size() && rt < primeNumber.size()) {
    		if(sum > n) {
    			sum -= primeNumber.get(lt);
    			lt++;
    		}
    		else if(sum == n) {
    			answer++;
    			sum -= primeNumber.get(lt);
    			lt++;
    		}
    		else if(rt == primeNumber.size()-1){
    			rt++;
    		}
    		else {
    			rt++;
    			sum += primeNumber.get(rt);
    		}
    	}
    	
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void prime() {
    	
    	isPrime = new boolean[n+1];
    	Arrays.fill(isPrime, true);
    	
    	isPrime[0] = false;
    	isPrime[1] = false;
    	
    	for(int i = 2; i*i <= n; i++) {
    		if(isPrime[i]) {
    			for(int j = i*i; j<= n; j += i) {
    				isPrime[j] = false;
    			}
    		}
    	}
    	
    }
}