import java.io.*;
import java.util.*;

public class Main {
  static List<Integer> arrayList = new ArrayList<>();
  static int dir;
  static StringBuilder answer = new StringBuilder();
  public static void main(String[] args) throws IOException {
    init();
    input();
    
    if(arrayList.get(0).equals(1)) dir = 1;
    else if(arrayList.get(0).equals(8)) dir = -1;
    else dir = 0;

    if(dir == 0) answer.append("mixed");
    else{
      for(int i = 0; i < 7; i++){
        if(arrayList.get(i + 1) - arrayList.get(i) != dir) {
          answer.append("mixed");
          break;
        }
      }
      if(answer.length() == 0 && dir == 1) answer.append("ascending");
      else if(answer.length() == 0 && dir == -1) answer.append("descending");
    }
    printAnswer();
  }

  static void init(){
    answer.setLength(0);
    arrayList.clear();
  }
  
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 0; i < 8; i++){
      arrayList.add(Integer.valueOf(st.nextToken()));
    }
  }

  static void printAnswer() throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(answer.toString());
    bw.flush();
    bw.close();
  }
  
}
