import java.io.*;
import java.util.*;

class Student {
    int idx;
    int score;
    public Student(int idx, int score){
        this.idx = idx;
        this.score = score;
    }
}

public class Main {
    public static int n;
    public static List<Student>[] score = new ArrayList[4];
    public static int[][] rank;
    public static void main(String[] args) throws IOException {

        Comparator<Student> cmp = new Comparator<>(){
            public int compare(Student s1, Student s2){
                return s2.score - s1.score;
            }
        };
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // n
        n = Integer.parseInt(br.readLine());
    
        // scoreArr - 1~3대회
        for(int i = 0; i < 4; i++) score[i] = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                int val = Integer.parseInt(st.nextToken());
                score[i].add(new Student(j, val));
            }
        }
        
        // 총합
        for(int i = 0; i < n; i++){
            int sum = 0;
            sum += score[0].get(i).score; // 첫번째 대회 점수
            sum += score[1].get(i).score; // 두번째 대회 점수
            sum += score[2].get(i).score; // 세번째 대회 점수
            score[3].add(new Student(i, sum));
        }

        // sort
        for(int i = 0; i < 4; i++){ // i번째 대회
            score[i].sort(cmp);
        }

        // rank
        rank = new int[4][n];
        for(int i = 0; i < 4; i++){
            int currentRank = 1;
            int rankedCnt = 1; // 상위 랭킹 몇명 - 기본 1명. 1등
            updateRank(i, score[i].get(0).idx, currentRank);
            for(int j = 1; j < n; j++){
                if(score[i].get(j-1).score == score[i].get(j).score){
                    updateRank(i, score[i].get(j).idx, currentRank);
                    rankedCnt++;
                }
                else{
                    currentRank = rankedCnt + 1;
                    updateRank(i, score[i].get(j).idx, currentRank);
                    rankedCnt++;
                }
            }
        }

        for(int i = 0; i < 4; i++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++){
                sb.append(String.valueOf(rank[i][j])).append(" ");
            }
            bw.write(sb.toString());
            bw.newLine();
        }

        bw.flush();
        bw.close();
        
    }

    public static void updateRank(int no, int studentIdx, int rankNum){
        rank[no][studentIdx] = rankNum;
    }
}
