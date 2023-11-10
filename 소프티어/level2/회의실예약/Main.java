import java.io.*;
import java.util.*;

class Room{
  String name;
  int idx;
  public Room(String name, int idx){
    this.name = name;
    this.idx = idx;
  }
}

public class Main {
  static int n, m;
  static HashMap<String, Integer> map = new HashMap<>(); 
  static boolean[][] canBook = new boolean[50][19];
  static StringBuilder answer = new StringBuilder();
  static List<Map.Entry<String, Integer>> roomList;
  // 회의실 이름을 오름차순으로 정렬
  static Comparator cmp = new Comparator<Map.Entry<String, Integer>>(){
    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b){
      return a.getKey().compareTo(b.getKey());
    }
  };
  
  public static void main(String[] args) throws IOException {
    init();
    input();
    solve();
    printRes();
  }
  static void init(){
    for(int i = 0; i < 50; i++) Arrays.fill(canBook[i], true);
  }
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    // n, m
    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    // 회의실 정보
    for(int i = 0;i < n ; i++){
      map.put(br.readLine(), i);
    }
    roomList = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
    // 방 정렬
    roomList.sort(cmp);
    // 예약 정보
    for(int i = 0; i < m; i++){
      st = new StringTokenizer(br.readLine());
      String name = st.nextToken();
      int idx = map.get(name);
      int s = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      for(int j = s; j < t; j++){
        canBook[idx][j] = false;
      }
      if(t == 18) canBook[idx][18] = false;
    }
  }
  static void printRes() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(answer.toString());
    bw.flush();
    bw.close();
  }
  static void solve() {    
    for(int i = 0; i < n; i++){
      answer.append("Room " + roomList.get(i).getKey() + ":\n");
      int idx = roomList.get(i).getValue();
      int cnt = 0; // 예약 가능한 시간 개수
      StringBuilder timeInfo = new StringBuilder();
      int startTime = 0, endTime = 0;
      boolean flag = false;
      for(int time = 9; time <= 18 ; time++){
        if(!flag && canBook[idx][time]){
          cnt++;
          flag = true;
          startTime = time;
        }else if(flag && !canBook[idx][time]){
          flag = false;
          endTime = time;
          if(startTime == 9) timeInfo.append("09-" + endTime + "\n");
          else timeInfo.append(startTime + "-" + endTime + "\n");
        }
      }
      if(canBook[idx][18]) {
        if(startTime == 9) timeInfo.append("09-18\n");
          else timeInfo.append(startTime + "-18\n");
        }
      if(cnt == 0) answer.append("Not available\n");
      else {
        answer.append(cnt + " available:\n");
        answer.append(timeInfo.toString());
      }
      if(i < n - 1){
        answer.append("-----\n");
      }
    }
  }

}
