import java.util.*;
import java.util.Map.*;
import java.io.*;

class Info implements Comparable<Info>{
	int num; int cnt;
	public Info(int num, int cnt) {
		this.num = num;
		this.cnt = cnt;
	}
	public int compareTo(Info other) {
		if(this.cnt == other.cnt) return this.num - other.num;
		return this.cnt - other.cnt;
	}
	public String toString() {
		return String.format("%d번 %d개", num ,cnt);
	}
}

public class Main {
	
	public static int r, c, k;
	public static int rN, cN;
	public static int[][] A;
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	r = Integer.parseInt(st.nextToken())-1;
    	c = Integer.parseInt(st.nextToken())-1;
    	k = Integer.parseInt(st.nextToken());
    	
    	A = new int[100][100];
    	for(int i = 0; i < 3; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 3; j++) {
    			A[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	int time = 0;
    	
    	rN = 3;
    	cN = 3;
    	//printA();
    	while(A[r][c] != k) {
    		if(time >= 100) {
    			time = -1;
    			break;
    		}
    		if(rN >= cN) rFunc();
    		else cFunc();
    		time++;
    		//printA();
    	}
    	
    	bw.write(time + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void rFunc() {
    	
    	int[][] nextA = new int[100][100];
    	int nextCN = -1;
    	
    	for(int i = 0; i < rN; i++) {
    		Map<Integer, Integer> map = new HashMap<>();
    		for(int j = 0; j < cN; j++) {
    			if(A[i][j] == 0) continue;
    			map.put(A[i][j], map.getOrDefault(A[i][j], 0) + 1);
    		}
    		List<Info> list = new ArrayList<>();
    		for(Entry<Integer, Integer> entry : map.entrySet()) {
    			list.add(new Info(entry.getKey(), entry.getValue()));
    		}
    		Collections.sort(list);
    		int offset = list.size() > 100 ? 100 : 0;
    		int a = 0;
    		int b = 1;
    		for(int j = 0; j < list.size() - offset; j++) {
    			nextA[i][a] = list.get(j + offset).num;
    			nextA[i][b] = list.get(j + offset).cnt;
    			a += 2;
    			b += 2;
    		}
    		if(nextCN < b - 2) {
    			nextCN = b - 2;    				
    		}
    	}
    	
    	cN = nextCN + 1;
    	A = nextA;
    	
    }
    
    public static void cFunc() {
    	
    	int[][] nextA = new int[100][100];
    	int nextRN = -1;
    	
    	for(int i = 0; i < cN; i++) {
    		Map<Integer, Integer> map = new HashMap<>();
    		for(int j = 0; j < rN; j++) {
    			if(A[j][i] == 0) continue;
    			map.put(A[j][i], map.getOrDefault(A[j][i], 0) + 1);
    		}
    		List<Info> list = new ArrayList<>();
    		for(Entry<Integer, Integer> entry : map.entrySet()) {
    			list.add(new Info(entry.getKey(), entry.getValue()));
    		}
    		Collections.sort(list);	
    		int offset = list.size() > 100 ? 100 : 0;
    		int a = 0;
    		int b = 1;
    		List<Integer> tmp = new ArrayList<>();
    		for(int j = 0; j < list.size() - offset; j++) {
    			nextA[a][i] = list.get(j + offset).num;
    			nextA[b][i] = list.get(j + offset).cnt;
    			tmp.add(nextA[a][i]);
    			tmp.add(nextA[b][i]);
    			a += 2;
    			b += 2;
    		}
    		if(nextRN < b - 2) {    				
    			nextRN = b - 2;
    		}
    	}
    	
    	rN = nextRN + 1;
    	A = nextA;
    	
    }
    
}