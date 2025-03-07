import java.util.*;
import java.io.*;

class Pos{
	int r; int c; int z;
	public Pos(int r, int c, int z) {
		this.r = r;
		this.c = c;
		this.z = z;
	}
}

public class Main {
	
	public static Pos target = new Pos(4,4,4);
	public static int answer = Integer.MAX_VALUE;
	public static int[][][] originMap = new int[5][5][5];
	public static int[][][] switchedMap = new int[5][5][5];
	public static int[][][] rotatedMap = new int[5][5][5];
	public static int[] dr = {-1,1,0,0,0,0};
	public static int[] dc = {0,0,-1,1,0,0};
	public static int[] dz = {0,0,0,0,-1,1};
	public static int[] floorPermRes = new int[5];
	public static int[] dirPermRes = new int[5];
	public static boolean[] floorPermVisit = new boolean[5];
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			st = new StringTokenizer(br.readLine());
    			for(int k = 0; k < 5; k++) {
    				originMap[i][j][k] = Integer.parseInt(st.nextToken()); 
    			}
    		}
    	}
   
    	floorPermutation(0);
    	
    	if(answer == Integer.MAX_VALUE) answer = -1;
    	
    	bw.write(answer + "");
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void floorPermutation(int depth) {
    	if(depth == 5) {
    		switchFloor();
    		dirDupPermutation(0);
    		return;
    	}
    	for(int i = 0; i < 5; i++) {
    		if(!floorPermVisit[i]) {
    			floorPermVisit[i] = true;
    			floorPermRes[depth] = i;
    			floorPermutation(depth + 1);
    			floorPermVisit[i] = false;
    		}
    	}
    }
    
    public static void switchFloor() {
    	switchedMap = new int[5][5][5];
    	//System.out.printf("층: %s\n", Arrays.toString(floorPermRes));
    	// i층을 floor층에
    	for(int i = 0; i < 5; i++) {
    		int floor = floorPermRes[i]; 
    		copy(originMap[i], switchedMap[floor]);
    	}
    }
    
    public static void dirDupPermutation(int depth) {
    	if(depth == 5) {
    		//System.out.printf("방향: %s\n", Arrays.toString(dirPermRes));
    		rotatedMap = new int[5][5][5];
    		for(int floor = 0; floor < 5; floor++) {
    			rotate(floor, dirPermRes[floor]);
    		}
//        	System.out.println("방향: " + Arrays.toString(dirPermRes));
//        	printMap(rotatedMap);
    		answer = Math.min(moveBFS(), answer);
    		return;
    	}
    	for(int i = 0; i < 4; i++) {
    		dirPermRes[depth] = i;
    		dirDupPermutation(depth + 1);
    	}
    }
    
    public static void rotate(int floor, int dir) {

    	int[][] tmp = new int[5][5];
    	copy(switchedMap[floor], tmp);
    	
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			if(dir == 0) {
    				rotatedMap[floor][i][j] = tmp[i][j];
    			}
    			else if(dir == 1) {
    				rotatedMap[floor][j][4-i] = tmp[i][j];
    			}
    			else if(dir == 2) {
    				rotatedMap[floor][4-i][4-j] = tmp[i][j];
    			}
    			else if(dir == 3) {
    				rotatedMap[floor][4-j][i] = tmp[i][j];
    			}
    		}
    	}
    	
    }
    
    public static int moveBFS() {
    	
    	if(rotatedMap[0][0][0] == 0 || rotatedMap[4][4][4] == 0) {
    		return Integer.MAX_VALUE;
    	}
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	int[][][] dist = new int[5][5][5];
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			for(int k = 0; k < 5; k++) {
    				dist[i][j][k] = -1;
    			}
    		}
    	}
    	
    	q.offer(new Pos(0,0,0));
    	dist[0][0][0] = 0;
    	
    	while(!q.isEmpty()) {
    		Pos now = q.poll();
    		
    		if(now.r == 4 && now.c == 4 && now.z == 4) {
    			return dist[4][4][4];
    		}
    		
    		for(int i = 0; i < 6; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			int nz = now.z + dz[i];
    			if(inRange(nr, nc, nz) && rotatedMap[nr][nc][nz] == 1 && dist[nr][nc][nz] == -1) {
    				dist[nr][nc][nz] = dist[now.r][now.c][now.z] + 1;
    				q.offer(new Pos(nr, nc, nz));
    			}
    		}
    	}
    	
    	return Integer.MAX_VALUE;
    }
    
    public static boolean inRange(int r, int c, int z) {
    	return r >= 0 && r < 5 && c >=0 && c < 5 && z >= 0 && z < 5;
    }
    
    public static void copy(int[][][] origin, int[][][] tmp) {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			for(int k = 0; k < 5; k++) {
    				tmp[i][j][k] = origin[i][j][k]; 
    			}
    		}
    	}
    }
    
    public static void copy(int[][] origin, int[][] tmp) {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			tmp[i][j] = origin[i][j];
    		}
    	}
    }
    
    public static void printMap(int[][][] map) {
    	for(int i = 0; i < 5; i++) {
    		for(int j = 0; j < 5; j++) {
    			for(int k = 0; k < 5; k++) {
    				System.out.printf("%d ", map[i][j][k]);
    			}
    			System.out.println();
    		}
    	}
    }
}