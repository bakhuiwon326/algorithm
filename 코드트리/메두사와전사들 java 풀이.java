import java.util.*;
import java.io.*;

class Pos{
	int r; int c;
	public Pos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	public boolean equals(Pos other) {
		return this.r == other.r && this.c == other.c;
	}
	public String toString() {
		return String.format("(%d,%d)", this.r, this.c);
	}
}

public class Main {
	// 출력
	public static int distSum, stoneCnt, attackCnt;
	// 입력
	public static int N, M, medusaDir;
	public static Pos medusa, park;
	public static Pos[] warrierList;
	public static int[][] map;
	public static int[][] distMap;
	public static boolean[] isDieWarrier;
	public static boolean[] isStoneWarrier;
	public static int[] dr = {-1,1,0,0};
	public static int[] dc = {0,0,-1,1};
	public static int[][][] medusaRange;
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
 
    	init();
    	
    	st = new StringTokenizer(br.readLine());
    	int a = Integer.parseInt(st.nextToken());
    	int b = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	int d = Integer.parseInt(st.nextToken());

    	medusa = new Pos(a, b);
    	park = new Pos(c, d);
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < M; i++) {
    		a = Integer.parseInt(st.nextToken());
    		b = Integer.parseInt(st.nextToken());
    		warrierList[i] = new Pos(a, b);
    	}
    	
    	for(int i = 0; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0; j < N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	boolean res = bfs();
    	int round = 0;
    	if(!res) {
    		bw.write("-1");
    	}
    	else {
    		while(true) {
    			//printMap(distMap, "공원 -> 메두사 거리");
    			//System.out.printf("****스텝%d****\n", round++);
    			distSum = 0; stoneCnt = 0; attackCnt = 0;
    			
    			step1_medusaMove();
    			if(medusa.equals(park)) {
    				bw.write("0");
    				break;
    			}
    			
    			step2_medusaRange();
    			step3_warrierMove();
    			//step4_warrierAttack();
    			
    			// 출력
    			bw.write(distSum + " " + stoneCnt + " " + attackCnt);
    			bw.newLine();
    			
    			Arrays.fill(isStoneWarrier, false); // 돌해지
    		}
    	    		
    	}
    	bw.flush();
    	bw.close();
    	
    }
    
    public static void step1_medusaMove() {
    	
    	int minDist = distMap[medusa.r][medusa.c], minR = -1, minC = -1;
    	for(int i = 0; i < 4; i++) {
    		int nr = medusa.r + dr[i];
    		int nc = medusa.c + dc[i];
    		if(inRange(nr, nc) && map[nr][nc] == 0 && minDist > distMap[nr][nc]) {
    			minDist = distMap[nr][nc];
    			minR = nr;
    			minC = nc;
    		}
    	}
    	
    	medusa.r = minR;
    	medusa.c = minC;
    
    	// 메두사가 이동한 위치에 전사가 있는지 파악.
    	for(int i = 0; i < M; i++) {
    		Pos warrier = warrierList[i];
    		if(!isDieWarrier[i] && medusa.equals(warrier)) {
    			isDieWarrier[i] = true;
    		}
    	}
    	
    }
    
    public static void step2_medusaRange() {
    	medusaRange = new int[4][N][N];
    	// 메두사 시선 모두 체크
    	medusaRangeFunc();
    	// 전사 발견하면 사각지대 위치에 메두사 시선 해제
    	findWarrierInMedusaRange();
    	// 돌로 변하는 전사 개수 세기 및 돌로 변신
    	checkStoneWarrier();
    }
    
    public static void checkStoneWarrier() {
    	int maxDir = -1;
    	int maxCnt = -1;
    	List<Integer> maxList = null;
    	for(int i = 0; i < 4; i++) {
    		List<Integer> list = new ArrayList<>();
    		for(int j = 0; j < M; j++) {
    			if(isDieWarrier[j]) continue;
    			int r = warrierList[j].r;
    			int c = warrierList[j].c;
    			if(medusaRange[i][r][c] == 1) {
    				list.add(j);
    			}
    		}
    		if(list.size() > maxCnt) {
    			maxDir = i;
    			maxList = list;
    			maxCnt = list.size();
    		}
    	}
    	//System.out.printf("메두사의 시선 방향 %d / 돌이될 전사: %s\n", maxDir, maxList.toString());
    	// 돌로 변신
    	for(int idx : maxList) {
    		isStoneWarrier[idx] = true;
    	}
    	medusaDir = maxDir;
    	stoneCnt += maxList.size();
    }
    
    public static void findWarrierInMedusaRange() {
    	// 메두사시선 안 전사 발견 후 사각지대는 해지.
    	//System.out.println("전사: " + Arrays.toString(warrierList));
    	//System.out.println("죽음: " + Arrays.toString(isDieWarrier));
    	// 상 메두시선 안 전사 발견
    	for(int idx = 0; idx < M; idx++) {
    		if(isDieWarrier[idx]) continue;
    		int r = warrierList[idx].r;
    		int c = warrierList[idx].c;
    		if(medusaRange[0][r][c] == 0) continue;
    		int vr = r - medusa.r; int vc = c - medusa.c;
    		// 직선
    		if(vc == 0 && vr < 0) {
    			for(int i = 1; r-i>=0; i++){
    				medusaRange[0][r-i][c] = 0;
    			}
    		}
    		// 왼쪽 위
    		else if(vr < 0 && vc < 0) {
    			for(int i = 1; r-i>=0; i++) {
    				int left = Math.max(0, c-i); 
    				int right = c;
    				for(int j = left; j<=right; j++) {
    					medusaRange[0][r-i][j] = 0;
    				}
    			}
    		}
    		// 오른쪽 위
    		else if(vr < 0 && vc > 0){
    			for(int i = 1; r-i>=0; i++) {
    				int left = c;
    				int right = Math.min(N-1, c+i);
    				for(int j = left; j<=right; j++) {
    					medusaRange[0][r-i][j] = 0;
    				}
    			}
    		}
    	}
    	//printMap(medusaRange[0], "전사 발견 후 (상)");
    	// 하 메두시선 안 전사 발견
    	for(int idx = 0; idx < M; idx++) {
    		if(isDieWarrier[idx]) continue;
    		int r = warrierList[idx].r;
    		int c = warrierList[idx].c;
    		if(medusaRange[1][r][c] == 0) continue;
    		int vr = r - medusa.r; int vc = c - medusa.c;
    		// 직선
    		if(vc == 0 && vr > 0) {
    			for(int i = 1; r+i<N; i++){
    				medusaRange[1][r+i][c] = 0;
    			}
    		}
    		// 왼쪽 아래
    		else if(vr > 0 && vc < 0) {
    			for(int i = 1; r+i<N; i++) {
    				int left = Math.max(0, c-i); 
    				int right = c;
    				for(int j = left; j<=right; j++) {
    					medusaRange[1][r+i][j] = 0;
    				}
    			}
    		}
    		// 오른쪽 아래
    		else if(vr > 0 && vc > 0){
    			for(int i = 1; r+i<N; i++) {
    				int left = c;
    				int right = Math.min(N-1, c+i);
    				for(int j = left; j<=right; j++) {
    					medusaRange[1][r+i][j] = 0;
    				}
    			}
    		}
    	}
    	//printMap(medusaRange[1], "전사 발견 후 (하)");
    	// 좌 메두시선 안 전사 발견
    	for(int idx = 0; idx < M; idx++) {
    		if(isDieWarrier[idx]) continue;
    		int r = warrierList[idx].r;
    		int c = warrierList[idx].c;
    		if(medusaRange[2][r][c] == 0) continue;
    		int vr = r - medusa.r; int vc = c - medusa.c;
    		// 직선
    		if(vr == 0 && vc < 0) {
    			for(int i = 1; c-i>=0; i++){
    				medusaRange[2][r][c-i] = 0;
    			}
    		}
    		// 왼쪽 위
    		else if(vr < 0 && vc < 0) {
    			for(int i = 1; c-i>=0; i++) {
    				int up = Math.max(0, r-i);
    				int down = r;
    				for(int j = up; j<=down; j++) {
    					medusaRange[2][j][c-i] = 0;
    				}
    			}
    		}
    		// 왼쪽 아래
    		else if(vr > 0 && vc < 0){
    			for(int i = 1; c-i>=0; i++) {
    				int up = r;
    				int down = Math.min(N-1, r+i);
    				for(int j = up; j<=down; j++) {
    					medusaRange[2][j][c-i] = 0;
    				}
    			}
    		}
    	}
    	//printMap(medusaRange[2], "전사 발견 후 (좌)");
    	// 우 메두시선 안 전사 발견
    	for(int idx = 0; idx < M; idx++) {
    		if(isDieWarrier[idx]) continue;
    		int r = warrierList[idx].r;
    		int c = warrierList[idx].c;
    		if(medusaRange[3][r][c] == 0) continue;
    		int vr = r - medusa.r; int vc = c - medusa.c;
    		// 직선
    		if(vr == 0 && vc > 0) {
    			for(int i = 1; c+i<N; i++){
    				medusaRange[3][r][c+i] = 0;
    			}
    		}
    		// 오른쪽 위
    		else if(vr < 0 && vc > 0) {
    			for(int i = 1; c+i<N; i++) {
    				int up = Math.max(0, r-i);
    				int down = r;
    				for(int j = up; j<=down; j++) {
    					medusaRange[3][j][c+i] = 0;
    				}
    			}
    		}
    		// 오른쪽 아래
    		else if(vr > 0 && vc > 0){
    			for(int i = 1; c+i<N; i++) {
    				int up = r;
    				int down = Math.min(N-1, r+i);
    				for(int j = up; j<=down; j++) {
    					medusaRange[3][j][c+i] = 0;
    				}
    			}
    		}
    	}
    	//printMap(medusaRange[3], "전사 발견 후 (우)");
    }
    
    public static void medusaRangeFunc() {
    	int r = medusa.r; int c = medusa.c;
    	//System.out.println("medusa: " + medusa.toString());
    	// 상 메두사시선
    	for(int i = 1; r-i>=0 ;i++) {
    		int left = Math.max(0, c-i);
    		int right = Math.min(N-1, c+i);
    		for(int j = left; j <= right; j++) {
    			medusaRange[0][r-i][j] = 1;
    		}
    	}
    	//printMap(medusaRange[0], "메두사 시선 (상)");
    	// 하 메두사시선
    	for(int i = 1; r+i<N ;i++) {
    		int left = Math.max(0, c-i);
    		int right = Math.min(N-1, c+i);
    		for(int j = left; j <= right; j++) {
    			medusaRange[1][r+i][j] = 1;
    		}
    	}
    	//printMap(medusaRange[1], "메두사 시선 (하)");
    	// 좌 메두사시선
    	for(int i = 1;c-i>=0; i++) {
    		int up = Math.max(0, r-i);
    		int down = Math.min(N-1, r+i);
    		for(int j = up; j <= down; j++) {
    			medusaRange[2][j][c-i] = 1;
    		}
    	}
    	//printMap(medusaRange[2], "메두사 시선 (좌)");
    	// 우 메두사시선
    	for(int i = 1;c+i<N; i++) {
    		int up = Math.max(0, r-i);
    		int down = Math.min(N-1, r+i);
    		for(int j = up; j <= down; j++) {
    			medusaRange[3][j][c+i] = 1;
    		}
    	}
    	//printMap(medusaRange[3], "메두사 시선 (우)");
    }
    
    public static void step3_warrierMove() {
    	//System.out.printf("메두사시선: %d\n", medusaDir);
    	//System.out.println("돌로 변한 전사: " + Arrays.toString(isStoneWarrier));
    	//printMap(medusaRange[medusaDir], "메두사 확정 레이더");
    	for(int i = 0; i < M; i++) {
    		Pos w = warrierList[i];
    		int prevR = w.r; int prevC = w.c;
    		if(isDieWarrier[i] || isStoneWarrier[i]) continue;
    		// 첫번째 이동
    		int minR1 = -1, minC1 = -1, minDist1 = getDist(medusa.r, medusa.c, w.r, w.c); 
    		for(int d = 0; d < 4; d++) {
    			int nr = w.r + dr[d];
    			int nc = w.c + dc[d];
    			if(inRange(nr, nc) && medusaRange[medusaDir][nr][nc] == 0) {
    				int dist = getDist(medusa.r, medusa.c, nr, nc);
    				//System.out.printf("방향: %d / 맨하튼거리: %d\n" , d, dist);
    				if(minDist1 > dist) {
    					minDist1 = dist;
    					minR1 = nr;
    					minC1 = nc;
    				}
    			}
    		}
    		
    		if(minR1 >= 0 && minC1 >= 0) {
    			// 이동
    			//System.out.printf("%d번 전사의 첫번째 이동 (%d,%d) -> (%d,%d) 맨하튼거리: %d\n", i, w.r, w.c, minR1, minC1, minDist1);
    			w.r = minR1;
    			w.c = minC1;
    			distSum++;
    			// 메두사와 만났는가?
    			if(w.equals(medusa)) {
    				//System.out.printf("메두사%s와 만났다\n", medusa);
    				isDieWarrier[i] = true;
        			attackCnt++;
        			continue;
    			}
    			
    			// 두번째 이동
    			int minR2 = -1, minC2 = -1, minDist2 = getDist(medusa.r, medusa.c, w.r, w.c);
    			for(int d = 0; d < 4; d++) {
        			int nr = w.r + dr[(d+2+4)%4];
        			int nc = w.c + dc[(d+2+4)%4];
        			if(nr == prevR && nc == prevC) continue;
        			if(inRange(nr, nc) && medusaRange[medusaDir][nr][nc] == 0) {
        				int dist = getDist(medusa.r, medusa.c, nr, nc);
        				//System.out.printf("방향: %d / 맨하튼거리: %d\n" , d, dist);
        				if(minDist2 > dist) {
        					minDist2 = dist;
        					minR2 = nr;
        					minC2 = nc;
        				}
        			}
        		}
    			
    			if(minR2 >= 0 && minC2 >= 0) {
    				// 이동
    				//System.out.printf("%d번 전사의 두번째 이동 (%d,%d) -> (%d,%d) 맨하튼거리: %d\n", i, w.r, w.c, minR2, minC2, minDist2);
        			w.r = minR2;
        			w.c = minC2;
        			distSum++;
        			// 메두사와 만났는가?
        			if(w.equals(medusa)) {
        				isDieWarrier[i] = true;
            			attackCnt++;
            			//System.out.printf("메두사%s와 만났다\n", medusa);
        			}
    			}
    		}
    		
    	}
    }
    
    public static void step4_warrierAttack() {
    	for(int i = 0; i < M; i++) {
    		if(isDieWarrier[i] && isStoneWarrier[i]) continue;
    		if(medusa.equals(warrierList[i])) {
    			isDieWarrier[i] = true;
    			attackCnt++;
    		}
    	}
    }
    
    public static boolean bfs() {
    	
    	Deque<Pos> q = new ArrayDeque<>();
    	q.offer(park);
    	distMap[park.r][park.c] = 0;
    	
    	while(!q.isEmpty()) {
    		
    		Pos now = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int nr = now.r + dr[i];
    			int nc = now.c + dc[i];
    			if(inRange(nr, nc) && map[nr][nc] == 0 && distMap[nr][nc] == -1) {
    				q.offer(new Pos(nr,nc));
    				distMap[nr][nc] = distMap[now.r][now.c] + 1;
    			}
    		}
    		
    	}

    	return distMap[medusa.r][medusa.c] != -1;
    }
    
    public static int getDist(int ar, int ac, int br, int bc) {
    	return Math.abs(ar - br) + Math.abs(ac - bc);
    }
    
    public static boolean inRange(int r, int c) {
    	return r >= 0 && r < N && c >=0 && c < N;
    }
    
    public static void init() {
    	map = new int[N][N];
    	distMap = new int[N][N];
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			distMap[i][j] = -1;
    		}
    	}
    	warrierList = new Pos[M];
    	isDieWarrier = new boolean[M];
    	isStoneWarrier = new boolean[M];
    }
    
    public static void printMap(int[][] tmp, String title) {
    	System.out.printf("-------%s------\n", title);
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			System.out.printf("%d ", tmp[i][j]);
    		}
    		System.out.println();
    	}
    }
}
