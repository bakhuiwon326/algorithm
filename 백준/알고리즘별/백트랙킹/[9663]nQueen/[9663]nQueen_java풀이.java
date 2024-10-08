import java.io.*;
import java.util.*;

public class Main {
    
	public static int n;
    public static boolean col[];
    public static boolean increaseCross[];
    public static boolean decreaseCross[];
	public static int answer;
    
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	col = new boolean[n];
    	increaseCross = new boolean[2 * n - 1];
    	decreaseCross = new boolean[2 * n - 1];
    	
    	answer = backtracking(0, 0);
    
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int backtracking(int qCnt, int row) {
    	if(qCnt == n) return 1;
    	int res = 0;
    	for(int i = 0; i < n; i++) {
    		if(col[i] || increaseCross[i + row] || decreaseCross[row - i + n - 1]) {
    			continue;
    		}
    		
    		col[i] = true;
    		increaseCross[i + row] = true;
    		decreaseCross[row - i + n - 1] = true;
    		
    		res += backtracking(qCnt + 1, row + 1);
    		
    		col[i] = false;
    		increaseCross[i + row] = false;
    		decreaseCross[row - i + n - 1] = false;
    		
    	}
    	
    	return res;
    }
    
}