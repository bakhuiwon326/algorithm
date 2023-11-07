import java.io.*;
import java.util.*;

public class Main {
  
  static int n;
  static int[][] map = new int[26][26];
  static boolean[][] visited = new boolean[26][26];
  static int[] dr = {0,0,1,-1};
  static int[] dc = {1,-1,0,0};
  static List<Integer> answerList = new ArrayList<Integer>();

  public static void main(String[] args) throws IOException{
    int blockCnt = 0;
    input();
    
    for(int i = 0; i < n; i++){
      for(int j = 0; j < n; j++){
        if(visited[i][j] || map[i][j] == 0) continue;
        blockCnt++;
        int cnt = dfs(i, j);
        answerList.add(cnt);
      }
    }
    answerList.sort(Comparator.naturalOrder());
    printAnswer(blockCnt);
   
  }
  
  static void input() throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    for(int i = 0; i < n; i++){
      String input = br.readLine();
      for(int j = 0; j < n; j++){
        map[i][j] = input.charAt(j) - '0';
      }
    }
  }

  static boolean isRange(int r ,int c){
    return r >= 0 && r < n && c >= 0 && c < n;
  }
  
  static int dfs(int r, int c){
    int cnt = 1;
    visited[r][c] = true;
    for(int d = 0;d < 4; d++){
      int nr = r + dr[d];
      int nc = c + dc[d];
      if(isRange(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1){
        cnt += dfs(nr, nc);
      }
    }
    return cnt;
  }

  static void printAnswer(int blockCnt) throws IOException{
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(Integer.toString(blockCnt));
    bw.newLine();

    for(int i = 0; i < answerList.size(); i++){
      bw.write(Integer.toString(answerList.get(i)));
      bw.newLine();
    }

    bw.flush();
    bw.close();
  }
}
