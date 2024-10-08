import java.io.*;
import java.util.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	public String toString() {
		return String.format("(%d, %d)", r, c);
	}
}

public class Main {
	
	public static int[][] board = new int[9][9];
	public static List<Pos> blank = new ArrayList<>();
	public static int[][] range = new int[9][9];
	public static List<Pos> leftTop = new ArrayList<>();
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// range
    	leftTop.add(new Pos(0,0));
    	leftTop.add(new Pos(0,3));
    	leftTop.add(new Pos(0,6));
    	leftTop.add(new Pos(3,0));
    	leftTop.add(new Pos(3,3));
    	leftTop.add(new Pos(3,6));
    	leftTop.add(new Pos(6,0));
    	leftTop.add(new Pos(6,3));
    	leftTop.add(new Pos(6,6));
    	
    	int cnt = 0;
    	for(int i = 0; i < 9; i += 3) {
    		for(int j = 0; j < 9; j +=3) {
    			// 왼쪽 위 좌표 (i, j)
    			 for(int a = i; a < i + 3; a++) {
    				 for(int b = j; b < j + 3; b++) {
    					 range[a][b] = cnt; 
    				 }
    			 }
    			 cnt++;
    		}
    	}

    	// board
    	for(int i = 0; i < 9; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < 9; j++) {
    			board[i][j] = Integer.parseInt(st.nextToken());
    			if(board[i][j] == 0) {
    				blank.add(new Pos(i, j));
    			}
    		}
    	}
    	
    	// backtracking
    	backtracking(0);
    	
    	printBoard(board);
    }
    
    public static boolean backtracking(int depth) { // depth번째 빈칸을 채운다.
    	if(depth == blank.size()) return true;
    	Pos pos = blank.get(depth);
    	List<Integer> availNum = getAvailNumber(pos.r, pos.c);
    	if(availNum.size() == 0) return false;
    	for(int num : availNum) {
    		board[pos.r][pos.c] = num;
    		//printBoard(board);
    		boolean res = backtracking(depth + 1);
    		//System.out.println(res ? "true" : "false");
    		if(res) return true;
    		board[pos.r][pos.c] = 0;
    	}
    	return false;
    }
    
    public static List<Integer> getAvailNumber(int r, int c){
    	int[] tmp = new int[10];
    	// 가로
    	for(int i = 0; i < 9; i++) {
    		if(i == c) continue;
    		tmp[board[r][i]]++;
    	}
    	// 세로
    	for(int i = 0; i < 9; i++) {
    		if(i == r) continue;
    		tmp[board[i][c]]++;
    	}
    	// 영역
    	Pos lt = leftTop.get(range[r][c]);
    	for(int i = lt.r; i < lt.r + 3; i++) {
    		for(int j = lt.c; j < lt.c + 3; j++) {
    			if(i == r && j == c) continue;
    			tmp[board[i][j]]++;
    		}
    	}
    	// 단 한번도 적히지 않은 숫자
    	List<Integer> res = new ArrayList<>();
    	for(int i = 1; i < 10; i++) {
    		if(tmp[i] == 0) {
    			res.add(i);
    		}
    	}
    	return res;
    }
    
    public static void printBoard(int[][] arr) {
    	for(int i = 0; i < 9; i++) {
    		for(int j = 0; j < 9; j++) {
    			System.out.printf("%d ", arr[i][j]);
    		}
    		System.out.println();
    	}
    }
    
}