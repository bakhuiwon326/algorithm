import java.io.*;
import java.util.*;

public class Main {
  static int m, n, k;
  static int[] secret = new int[101];
  static int[] user = new int[101];
  static StringBuilder answer = new StringBuilder("normal");
  public static void main(String[] args) throws IOException{
    input();
    solve();
    printRes();
  }
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    // m, n, k
    st = new StringTokenizer(br.readLine());
    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());
    // secret
    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < m; i++){
      secret[i] = Integer.parseInt(st.nextToken());
    }
    // user
    st = new StringTokenizer(br.readLine());
    for(int i = 0 ; i < n; i++){
      user[i] = Integer.parseInt(st.nextToken());
    }
  }
  static void solve(){
    for(int left = 0; left <= n - m; left++){
      boolean isSecret = true;
      for(int right = left; right < left + m; right++){
        int secretIdx = right - left;
        if(secret[secretIdx] != user[right]) isSecret = false;
      }
      if(isSecret) {
        answer.setLength(0);
        answer.append("secret");
        break;
      }
    }
  }
  static void printRes() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(answer.toString());
    bw.flush();
    bw.close();
  }
}
