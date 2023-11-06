import java.io.*;
import java.util.*;

public class Main {
  
  static int k, p, n;
  static long answer;
    
  public static void main(String[] args) throws IOException {
      input();
      answer = divideConquer(n) * k % 1000000007;
      printAnswer();
  }
  
  static long divideConquer(int expo){
    if(expo == 1) return p;
    long res = divideConquer(expo / 2);
    res = res * res % 1000000007;
    if(expo % 2 == 1) res = res * p % 1000000007;
    return res % 1000000007;
  }
  
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    k = Integer.parseInt(st.nextToken());
    p = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
  }

  static void printAnswer() throws IOException{
    BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));
    br.write(Long.toString(answer));
    br.flush();
    br.close();
  }
}