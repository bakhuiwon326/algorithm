import java.io.*;
import java.util.*;

public class Main {
    public static int answer;
    public static int n;
    public static int[][] board;
    public static int[] dr = {-1,1,0,0};
    public static int[] dc = {0,0,-1,0};
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	// n
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	
    	// board
    	board = new int[n][n];
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
	    	for(int j = 0; j < n; j++) {
	    		board[i][j] = Integer.parseInt(st.nextToken());
	    	}
    	}
    	
    	answer = play(0);
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static int play(int depth) {
    	if(depth == 5) return getMaxVal();
    	int maxVal = -1;
    	// 상하좌우 이동 전 원본 보드 상태값 지님
    	int[][] tmp = new int[n][n];
    	copyBoard(board, tmp);
    	for(int dir = 0; dir < 4; dir++) {
    		moveBoard(dir); // 이동 및 board값 변경
    		int res = play(depth + 1);
    		if(res > maxVal) maxVal = res;
    		copyBoard(tmp, board); // board값 원위치
    	}
    	return maxVal;
    }
    
    public static int getMaxVal() {
    	int res = -1;
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(board[i][j] > res) res = board[i][j];
    		}
    	}
    	return res;
    }
    
    public static void copyBoard(int[][] origin, int[][] tmp) {
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			tmp[i][j] = origin[i][j];
    		}
    	}
    }
    
    public static void moveBoard(int dir) {
    	// 상
    	if(dir == 0) {
    		up();
    	}
    	// 하
    	else if(dir == 1) {
    		down();
    	}
    	// 좌
    	else if(dir == 2) {
    		left();
    	}
    	// 우
    	else {
    		right();
    	}
    }
    
    public static void up() {
    	int[][] nextBoard = new int[n][n];
    	// 합치기
    	for(int c = 0; c < n; c++) {
    		boolean flag = false;
    		int currentNum = -1;
    		int currentIdx = -1;
    		for(int r = 0; r < n; r++) {
    			if(board[r][c] == 0) continue;
    			if(!flag) {
    				currentNum = board[r][c];
    				currentIdx = r;
    				flag = true;
    			}
    			else if(board[r][c] == currentNum){
    				board[currentIdx][c] = currentNum * 2;
    				board[r][c] = 0;
    				flag = false;
    			}
    			else {
    				currentNum = board[r][c];
    				currentIdx = r;
    			}
    		}
    	}
    	// 올리기
    	for(int c = 0; c < n; c++) {
    		int idx = 0;
    		for(int r = 0; r < n; r++) {
    			if(board[r][c] > 0) {
    				nextBoard[idx++][c] = board[r][c];
    			}
    		}
    	}
    	copyBoard(nextBoard, board);
    }
    
    public static void down() {
    	int[][] nextBoard = new int[n][n];
    	// 합치기
    	for(int c = 0; c < n; c++) {
    		boolean flag = false;
    		int currentNum = -1;
    		int currentIdx = -1;
    		for(int r = n - 1; r >= 0; r--) {
    			if(board[r][c] == 0) continue;
    			if(!flag) {
    				currentNum = board[r][c];
    				currentIdx = r;
    				flag = true;
    			}
    			else if(board[r][c] == currentNum){
    				board[currentIdx][c] = currentNum * 2;
    				board[r][c] = 0;
    				flag = false;
    			}
    			else {
    				currentNum = board[r][c];
    				currentIdx = r;
    			}
    		}
    	}
    	// 올리기
    	for(int c = 0; c < n; c++) {
    		int idx = n - 1;
    		for(int r = n - 1; r >= 0; r--) {
    			if(board[r][c] > 0) {
    				nextBoard[idx--][c] = board[r][c];
    			}
    		}
    	}
    	copyBoard(nextBoard, board);
    }
    
    public static void left() {
    	int[][] nextBoard = new int[n][n];
    	// 합치기
    	for(int r = 0; r < n; r++) {
    		boolean flag = false;
    		int currentNum = -1;
    		int currentIdx = -1;
    		for(int c = 0; c < n; c++) {
    			if(board[r][c] == 0) continue;
    			if(!flag) {
    				currentNum = board[r][c];
    				currentIdx = c;
    				flag = true;
    			}
    			else if(board[r][c] == currentNum){
    				board[r][currentIdx] = currentNum * 2;
    				board[r][c] = 0;
    				flag = false;
    			}
    			else {
    				currentNum = board[r][c];
    				currentIdx = c;
    			}
    		}
    	}
    	// 올리기
    	for(int r = 0; r < n; r++) {
    		int idx = 0;
    		for(int c = 0; c < n; c++) {
    			if(board[r][c] > 0) {
    				nextBoard[r][idx++] = board[r][c];
    			}
    		}
    	}
    	copyBoard(nextBoard, board);
    }
    
    public static void right() {
    	int[][] nextBoard = new int[n][n];
    	// 합치기
    	for(int r = 0; r < n; r++) {
    		boolean flag = false;
    		int currentNum = -1;
    		int currentIdx = -1;
    		for(int c = n - 1; c >= 0; c--) {
    			if(board[r][c] == 0) continue;
    			if(!flag) {
    				currentNum = board[r][c];
    				currentIdx = c;
    				flag = true;
    			}
    			else if(board[r][c] == currentNum){
    				board[r][currentIdx] = currentNum * 2;
    				board[r][c] = 0;
    				flag = false;
    			}
    			else {
    				currentNum = board[r][c];
    				currentIdx = c;
    			}
    		}
    	}
    	// 올리기
    	for(int r = 0; r < n; r++) {
    		int idx = n - 1;
    		for(int c = n - 1; c >= 0; c--) {
    			if(board[r][c] > 0) {
    				nextBoard[r][idx--] = board[r][c];
    			}
    		}
    	}
    	copyBoard(nextBoard, board);
    }
    
}