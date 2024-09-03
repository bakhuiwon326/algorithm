import java.io.*;
import java.util.*;

class Pos{
    int r;
    int c;
    public Pos(int r, int c){
        this.r = r;
        this.c = c;
    }
}

public class Main {
    
    public static int n;
    public static int m;
    public static int[][] board;
    public static List<Pos> targetList = new ArrayList<>();
    
    public static int[] dr = {0,0,1,-1};
    public static int[] dc = {1,-1,0,0};
    public static boolean[][] visited;
    
    public static int res = 0;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // n, m
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][n];

        // board
        board = new int[n][n];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // target
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            targetList.add(new Pos(r - 1, c - 1));
        }

        // execute dfs
        visited[targetList.get(0).r][targetList.get(0).c] = true;
        dfs(1, targetList.get(0).r, targetList.get(0).c);

        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
        
    }

    public static void dfs(int idx, int r, int c){
       if(targetList.get(idx).r == r & targetList.get(idx).c == c){
           if(idx == m - 1){
               res++;
               return;
           }
           idx++;
       }
        for(int d = 0; d < 4; d++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(inRange(nr, nc) && board[nr][nc] == 0 && !visited[nr][nc]){
                visited[nr][nc] = true;
                dfs(idx, nr, nc);
                visited[nr][nc] = false;
            }
        }
    }

    public static boolean inRange(int r, int c){
        return r >= 0 && r < n && c>= 0 && c < n;
    }
    
}