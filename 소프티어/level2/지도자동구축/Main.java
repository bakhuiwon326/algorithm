import java.io.*;
import java.util.*;

public class Main {
  static int n;
  static int[] dp = new int[16]; // i 단계에서 한 줄 위에 놓인 점 개수
  public static void main(String[] args) throws IOException{
    input();
    dp[0] = 2;
    for(int i = 1; i <= n; i++){
      dp[i] = 2 * dp[i - 1] - 1;
    }
    printRes();
  }

  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
  }

  static void printRes() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(Integer.toString(dp[n] * dp[n]));
    bw.flush();
    bw.close();
  }
  
}
