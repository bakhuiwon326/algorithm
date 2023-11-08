import java.io.*;
import java.util.*;

public class Main {
  static int n, s;
  static final int INF = 2147483647;
  static int minLen = INF;
  static long partialSum = 0;
  static int[] seq = new int[100001];
  public static void main(String[] args) throws IOException{
	  input();
	  solve();
	  printRes();
  }
  static void input() throws IOException{
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st;
	  
	  // n, s
	  st = new StringTokenizer(br.readLine());
	  n = Integer.parseInt(st.nextToken());
	  s = Integer.parseInt(st.nextToken());
	  
	  // seq
	  st = new StringTokenizer(br.readLine());
	  for(int i = 0; i < n; i++) {
		  seq[i] = Integer.parseInt(st.nextToken());
	  }
	 
  }
  static void solve() {
	  // 연속, 길이 가변적 => 투포인터
	  int start = 0;
	  int end = 0;
	  partialSum += seq[start];
	  while(true) {
		  if(partialSum >= s) {
			  minLen = Math.min(end - start + 1, minLen);
			  partialSum -= seq[start];
			  start++; 
		  }else{
			  end++;
			  if(end == n) break;
			  partialSum += seq[end];
		  }
	  }
	  if(minLen == INF) minLen = 0;
  }
  static void printRes() throws IOException{
	  BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	  bw.write(Integer.toString(minLen));
	  bw.flush();
	  bw.close();
  }
}
