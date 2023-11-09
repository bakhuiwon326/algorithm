import java.io.*;
import java.util.*;

public class Main {
  static int t;
  static int[] fromNum = new int[1000];
  static int[] toNum = new int[1000];
  static int[] answer = new int[1000];
  static int[][] binary = {
    {1,1,1,0,1,1,1}, // 0
    {0,0,1,0,0,1,0}, // 1
    {1,0,1,1,1,0,1}, // 2
    {1,0,1,1,0,1,1}, // 3
    {0,1,1,1,0,1,0}, // 4
    {1,1,0,1,0,1,1}, // 5
    {1,1,0,1,1,1,1}, // 6
    {1,1,1,0,0,1,0}, // 7
    {1,1,1,1,1,1,1}, // 8
    {1,1,1,1,0,1,1}, // 9
    {0,0,0,0,0,0,0} // off
  };
  public static void main(String[] args) throws IOException{
    input();
    solve();
    printRes();
  }

  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    // t
    st = new StringTokenizer(br.readLine());
    t = Integer.parseInt(st.nextToken());
    // test
    int a, b;
    for(int i = 0; i < t; i++){
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      fromNum[i] = a;
      toNum[i] = b;
    }
  }

  static void solve(){
    for(int i = 0; i < t; i++){
      int mod = 10;
      int cnt = 0;
      for(int j = 0; j < 5; j++){
        int fromDigit = fromNum[i] != 0 ? fromNum[i] % mod : 10;
        int toDigit = toNum[i] != 0 ? toNum[i] % mod : 10;
        for(int place = 0; place < 7; place++){
          if(binary[fromDigit][place] != binary[toDigit][place]) cnt++;
        }
        fromNum[i] = fromNum[i] / mod;
        toNum[i] = toNum[i] / mod; 
      }
      answer[i] = cnt;
    }
  }
  
  static void printRes() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    for(int i = 0; i < t; i++){
      bw.write(Integer.toString(answer[i]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }
}
