import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

class Node{
    int e;
    int cost;
    public Node(int e, int cost){
        this.e = e;
        this.cost = cost;
    }
}

public class Main {
    public static ArrayList<Node>[] adj;
    public static boolean[] visited;
    static int max = 0;
    static int node;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];
        adj = new ArrayList[n + 1];
        for(int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while(true){
                int e = Integer.parseInt(st.nextToken());
                if(e == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                adj[s].add(new Node(e, cost));
            }
        }

        visited = new boolean[n+1];
        dfs(1, 0);

        visited = new boolean[n+1];
        dfs(node, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }

    public static void dfs(int x, int len){
        if(len > max){
            max = len;
            node = x;
        }
        visited[x] = true;
        for(int i = 0; i < adj[x].size(); i++){
            Node n = adj[x].get(i);
            if(!visited[n.e]){
                dfs(n.e, n.cost + len);
                visited[n.e] = false;
            }
        }
    }

}