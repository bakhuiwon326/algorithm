import java.io.*;
import java.util.*;

class Info{
  int section;
  int speed;
  public Info(int section, int speed){
    this.section = section;
    this.speed = speed;
  }
}

public class Main {
  static int n, m;
  static int res;
  static List<Info> limit = new ArrayList<Info>();
  static List<Info> test= new ArrayList<Info>();
  
  public static void main(String[] args) throws IOException{
    init();
    input();
    solve();
    printRes();
  }
  
  static void init(){
    res = -1;
    limit.clear();
    test.clear();
  }
  
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    // n, m
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    // 각 구간의 길이 및 해당 구간 제한속도
    for(int i = 0; i < n; i++){
      st = new StringTokenizer(br.readLine());
      limit.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
    // 시범 운행 결과
    for(int i = 0; i < m; i++){
      st = new StringTokenizer(br.readLine());
      test.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
  }

  static void solve(){
    int nextLimit = 0; // test 구간보다 작으면 ++, 크면 그대로!
    int nextTest = 0; // limit 구간보다 초과하면 그대로. 작거나 같으면 ++
    while(nextLimit < n || nextTest < m){
      int speedDiff = test.get(nextTest).speed - limit.get(nextLimit).speed;
      if(speedDiff > 0) res = Math.max(res, speedDiff);
      if(limit.get(nextLimit).section < test.get(nextTest).section){
        test.get(nextTest).section -= limit.get(nextLimit).section;
        nextLimit++;
      }
      else if(limit.get(nextLimit).section > test.get(nextTest).section){
        limit.get(nextLimit).section -= test.get(nextTest).section;
        nextTest++;
      }
      else{
        nextLimit++;
        nextTest++;
      }
    }
    if(res < 0) res = 0;
  }

  static void printRes() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(Integer.toString(res));
    bw.flush();
    bw.close();
  }
  
}
