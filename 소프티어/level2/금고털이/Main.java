import java.io.*;
import java.util.*;

class Jewel{
	int weight, price;
	public Jewel(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}
}

public class Main {

	static int n, w, res;
	static List<Jewel> jewelList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
    	init();
    	input();
    	solution();
    	//printRes();
    	System.out.println(res);
    }
    
    static void init() {
    	res = 0;
    	jewelList.clear();
    }
    
    static void input()throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	st = new StringTokenizer(br.readLine());
    	w = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int m = Integer.parseInt(st.nextToken());
    		int p = Integer.parseInt(st.nextToken());
    		jewelList.add(new Jewel(m, p));
    	}
    }
    
    static void solution() {
    	// 무게당 가격이 높은 순으로 정렬
    	Comparator<Jewel> cmp = new Comparator<Jewel>() {
    		public int compare(Jewel a, Jewel b) {
    			return b.price - a.price;
    		}
    	};
    	
    	// 정렬
    	jewelList.sort(cmp);
    	
    	// 비싼 보석부터 가방에 넣는다.
    	for(Jewel jewel : jewelList) {
    		// 남은 공간에 jewel 전체를 넣을 수 있는지 확인하기
    		if(w >= jewel.weight) {
    			res += (jewel.price * jewel.weight);
    			w -= jewel.weight;
    		}
    		// 남은 공간에 jewel 전체를 못 넣는다면? jewel 쪼개서 넣는다.
    		else {
    			res += (jewel.price * w);
    			break;
    		}
    	}
    	
    	
    }
    
    static void printRes() throws IOException {
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	bw.write(Integer.toString(res));
    	bw.flush();
    	bw.close();
    }
}
