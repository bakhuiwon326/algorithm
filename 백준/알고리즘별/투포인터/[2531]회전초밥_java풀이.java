import java.util.*;
import java.io.*;


public class Main {
	public static int n, d, k, c;
	public static int maxCnt = -1;
	public static int[] susi;
	public static Map<Integer, Integer> sequence = new HashMap<>();
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	d = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	c = Integer.parseInt(st.nextToken());
    	
    	susi = new int[n];
    	for(int i = 0 ; i < n; i++) {
    		susi[i] = Integer.parseInt(br.readLine());
    	}
    	
    	// sequence 초기화
    	for(int i = 0; i < k; i++) {
    		sequence.put(susi[i], sequence.getOrDefault(susi[i], 0) + 1);
    	}
    	if(sequence.containsKey(c)) {
    		maxCnt = sequence.size();    		
    	}
    	else {
    		maxCnt = sequence.size() + 1;
    	}
    	
    	// [lt, rt]를 sequence에 저장
    	for(int lt = 1; lt < n; lt++) {
    		int rt = (lt + k - 1) % n;
    		// lt-1 빼기
    		sequence.put(susi[lt-1], sequence.get(susi[lt-1]) - 1);
    		// rt 추가
    		sequence.put(susi[rt], sequence.getOrDefault(susi[rt], 0) + 1);
    		// lt-1 초밥 개수 0이면 아예 제거!
    		if(sequence.get(susi[lt-1]) == 0) {
    			sequence.remove(susi[lt-1]);
    		}
    		// maxCnt 업뎃
    		if(sequence.containsKey(c)) {
    			maxCnt = Math.max(maxCnt, sequence.size());
    		}
    		else {
    			maxCnt = Math.max(maxCnt, sequence.size() + 1);
    		}
    	}
    	
    	bw.write(maxCnt + "");
    	bw.flush();
    	bw.close();
    	
    }
}