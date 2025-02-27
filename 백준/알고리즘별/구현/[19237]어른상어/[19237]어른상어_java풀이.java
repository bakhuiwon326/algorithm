import java.util.*;
import java.io.*;

class Shark{
	int no; int r; int c; int dir; boolean isOut;
	public Shark(int no, int r, int c, int dir, boolean isOut) {
		this.no = no;
		this.r = r;
		this.c = c;
		this.dir = dir;
		this.isOut = isOut;
	}
	public String toString() {
		return String.format("(%d번,%d,%d,%d)", no, r, c, dir);
	}
}

public class Main {
	
	public static int n, m, k, spendTime;
	public static int[][][] priorityDir;
	public static int[][] smellTime;
	public static int[][] whoSmell;
	
	public static int[][] sharkMap;
	public static Shark[] sharkArr;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	priorityDir = new int[m+1][4][4];
    	smellTime = new int[n][n];
    	whoSmell = new int[n][n];
    	sharkMap = new int[n][n];
    	sharkArr = new Shark[m+1];
    	
    	for(int i = 0; i < n ; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < n; j++) {
    			sharkMap[i][j] = Integer.parseInt(st.nextToken());
    			sharkArr[sharkMap[i][j]] = new Shark(sharkMap[i][j], i, j, -1, false);
    			smellTime[i][j] = k;
    			whoSmell[i][j] = sharkMap[i][j];
    		}
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 1; i <= m; i++) {
    		int dir = Integer.parseInt(st.nextToken()) - 1;
    		sharkArr[i].dir = dir;
    	}
    	
    	for(int i = 1; i <= m; i++) {
    		for(int j = 0; j < 4; j++) {
    			st = new StringTokenizer(br.readLine());
    			for(int p = 0; p < 4; p++) {
    				priorityDir[i][j][p] = Integer.parseInt(st.nextToken())-1;
    			}
    		}
    	}
    	
    	//printSharkMap();
    	while(!isAloneNo1()) {
    		if(spendTime >= 1000) {
    			spendTime = -1;
    			break;
    		}
    		moveShark();
    		decreaseSmellTime();
    		createSmell();
    		spendTime++;
    		//printSharkMap();
    	}
    	
    	bw.write(spendTime + "");
    	bw.flush();
    	bw.close();
    	
    	
    	
    }
    
    public static void moveShark() {
    	
    	int[][] log = new int[n][n];
    	
    	for(int i = 1; i <= m ; i++) {
    		
    		if(sharkArr[i].isOut) continue;
    		
    		Shark shark = sharkArr[i];
    		
    		int d = -1; int nr = -1; int nc = -1;
    		
    		// 상하좌우
    		boolean canGo = false;
    		for(int j = 0; j < 4; j++) {
    			d = priorityDir[shark.no][shark.dir][j];
    			nr = shark.r + dr[d];
    			nc = shark.c + dc[d];
    			if(inRange(nr, nc) && whoSmell[nr][nc] == 0) {
    				canGo = true;
    				break;
    			}
    		}
    		
    		if(!canGo) {
    			for(int j = 0; j < 4; j++) {
        			d = priorityDir[i][shark.dir][j];
        			nr = shark.r + dr[d];
        			nc = shark.c + dc[d];
        			if(inRange(nr, nc) && whoSmell[nr][nc] == shark.no) {
        				canGo = true;
        				break;
        			}
        		}
    		}
    		
    		if(canGo && (log[nr][nc] == 0 || log[nr][nc] > shark.no)) {
    			log[nr][nc] = shark.no;
    			sharkMap[shark.r][shark.c] = 0;
    			sharkMap[nr][nc] = shark.no;
    			shark.r = nr;
    			shark.c = nc;
    			shark.dir = d;
    		}
    		else if(canGo && sharkMap[nr][nc] < shark.no){
    			sharkMap[shark.r][shark.c] = 0;
    			shark.isOut = true;
    		}
    		
    	}
    	
    }
    
    
    public static void decreaseSmellTime() {
    	
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n; j++) {
    			if(smellTime[i][j] > 0) {
    				smellTime[i][j]--;
    			}
    			if(smellTime[i][j] == 0) {
    				whoSmell[i][j] = 0;
    			}
    		}
    	}
    	
    }
    
    public static void createSmell() {
    	for(int i = 1; i <= m; i++) {
    		if(sharkArr[i].isOut) continue;
    		Shark shark = sharkArr[i];
    		smellTime[shark.r][shark.c] = k;
    		whoSmell[shark.r][shark.c] = shark.no;
    	}
    }
    
    public static boolean isAloneNo1() {
    	for(int i = 2; i <= m; i++) {
    		if(!sharkArr[i].isOut) return false;
    	}
    	return !sharkArr[1].isOut;
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < n && c >= 0 && c < n;
    }
    
    public static void printSharkMap() {
    	//if(spendTime > 15) return;
    	System.out.printf("------------%d회-----------\n", spendTime);
    	System.out.println("sharks : " + Arrays.toString(sharkArr));
    	for(int i = 0; i < n; i++) {
    		for(int j = 0; j < n ;j++) {
    			System.out.printf("%6d ", sharkMap[i][j]);
    		}
    		System.out.println();
    	}
    }
}