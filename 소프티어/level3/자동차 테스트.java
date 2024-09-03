import java.io.*;
import java.util.*;

public class Main {
    
    public static int n;
    public static int q;
    public static List<Integer> list;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        //n, q
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        // list - fill, asc sort
        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            int val =  Integer.parseInt(st.nextToken());
            list.add(val);
        }
        list.sort(Comparator.naturalOrder());

        // km/L
        for(int i = 0; i < q; i++){
            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            int cnt = binarySearch(val);
            bw.write(String.valueOf(cnt));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        
    }

    public static int binarySearch(int val){
        int lt = 0;
        int rt = n - 1;

        while(lt <= rt){
            int mid = (lt + rt) / 2;
            if(list.get(mid) < val){
                lt = mid + 1;
            }
            else if(list.get(mid) > val){
                rt = mid - 1;
            }
            else{
                return mid * (n - mid - 1);
            }
        }
        return 0;
    }
}