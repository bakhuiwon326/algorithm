import java.util.*;
import java.io.*;

public class Main {

    public static int answer;
    public static int R;
    public static int C;
    public static int K;

    public static int[][] map;
    public static boolean[][] isExit;

    // 북동남서
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // R, C, K
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R + 3][C];
        isExit = new boolean[R + 3][C];

        // 골렘
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            down(1, c, d, i + 1);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    public static void down(int r, int c, int d, int id){
        // 남쪽
        if(movable(r, c, 0)){
            down(r + 1, c, d, id);
        }
        // 서쪽 -> 남쪽
        else if(movable(r, c, 1)){
            down(r + 1, c - 1, (d + 3) % 4, id);
        }
        // 동쪽 -> 남쪽
        else if(movable(r, c, 2)){
            down(r + 1, c + 1, (d + 1) % 4, id);
        }
        // 골렘이 움직일 수 없음.
        else{
            // 격자에 일부만 걸쳐있는지 확인
            if(!inRange(r - 1, c) || !inRange(r, c) || !inRange(r + 1, c)){
                map = new int[R + 3][C];
                isExit = new boolean[R + 3][C];
            }
            else{
                // 골렘 및 출구 위치 표시
                isExit[r + dr[d]][c + dc[d]] = true;
                markGolemPos(r, c, id);
                // 정령을 최대한 남쪽으로 움직이기
                answer += (bfs(r, c) - 3 + 1);
            }
        }
    }

    public static int bfs(int r, int c){
        int res = r;
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[R + 3][C];
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        while(!q.isEmpty()){
            int currentR = q.peek()[0];
            int currentC = q.peek()[1];
            int id = map[currentR][currentC];
            q.pop();
            for(int i = 0; i < 4; i++){
                int nr = currentR + dr[i];
                int nc = currentC + dc[i];
                if(inRange(nr, nc) && !visited[nr][nc] && (map[nr][nc] == id || (map[nr][nc] != 0 && isExit[currentR][currentC]))){
                    q.offer(new int[]{nr, nc});
                    visited[nr][nc] = true;
                    res = Math.max(res, nr);
                }
            }
        }
        return res;
    }

    public static boolean movable(int r, int c, int type){ // type: 0 남쪽 / 1 서쪽 남쪽 / 2 동쪽 남쪽
        if(type == 0){
            return inTotalRange(r + 1, c + 1) && inTotalRange(r + 1, c - 1) && inTotalRange(r + 2, c) 
            && map[r + 1][c + 1] == 0 && map[r + 1][c - 1] == 0 & map[r + 2][c] == 0;
        }
        else if(type == 1){
            return inTotalRange(r, c - 2) && inTotalRange(r + 1, c - 1) && inTotalRange(r - 1, c - 1) && inTotalRange(r + 1, c - 2) && inTotalRange(r + 2, c - 1)
            && map[r][c - 2] == 0 && map[r + 1][c - 1] == 0 && map[r - 1][c - 1] == 0 && map[r + 1][c - 2] == 0 && map[r + 2][c - 1] == 0;
        }
        else {
            return inTotalRange(r, c + 2) && inTotalRange(r + 1, c + 1) && inTotalRange(r - 1, c + 1) && inTotalRange(r + 1, c + 2) && inTotalRange(r + 2, c + 1)
            && map[r][c + 2] == 0 && map[r + 1][c + 1] == 0 && map[r - 1][c + 1] == 0 && map[r + 1][c + 2] == 0 && map[r + 2][c + 1] == 0;
        }
    }

    public static void markGolemPos(int r, int c, int id){
        map[r][c] = id;
        map[r - 1][c] = id;
        map[r + 1][c] = id;
        map[r][c - 1] = id;
        map[r][c + 1] = id;
        //printMap();
    }
    
    public static boolean inRange(int r, int c){ // 격자에 들어오는가 체크
        return r >= 3 && r < R + 3 && c >= 0 && c < C;
    }

    public static boolean inTotalRange(int r, int c){ // 격자 범위 밖(허용범위) 들어오는가 체크
        return r >= 0 && r < R + 3 && c >= 0 && c < C;
    }

    public static void printMap(){
        System.out.println("-------------------------------");
        for(int i = 0; i < R + 3; i++){
            for(int j = 0; j < C; j++){
                System.out.printf("%d ", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("*");
        for(int i = 0; i < R + 3; i++){
            for(int j = 0; j < C; j++){
                System.out.printf("%d ", isExit[i][j] ? map[i][j] : 0);
            }
            System.out.println();
        }
    }
}