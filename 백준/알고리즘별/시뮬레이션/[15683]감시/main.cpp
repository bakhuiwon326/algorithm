#include <iostream>
#include <vector>
#include <memory.h>

using namespace std;

struct CCTV {
	int r, c;
	int type;
	CCTV(int r, int c, int type) : r(r), c(c), type(type) {};
};

int n, m, quaternary;
int dr[4] = { 1,0,-1,0 };
int dc[4] = { 0,1,0,-1 };
vector<CCTV > cctvPos;
int origin_map[10][10];
int map[10][10];
int minRes = 2147486347;

bool isInRange(int x, int y) {
	return x >= 0 && x < n && y >= 0 && y <= m;
}

int getEmptyCnt() {
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 0) cnt++;
		}
	}
	return cnt;
}

void copyOrigin() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			map[i][j] = origin_map[i][j];
		}
	}
}

void init() {
	quaternary = 1;
	cctvPos.clear();
	minRes = 2147483647;
	memset(origin_map, 0, sizeof(origin_map));
	memset(map, 0, sizeof(map));
}

void input() {
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for(int j = 0; j < m; j++){
			scanf("%d", &origin_map[i][j]);
			map[i][j] = origin_map[i][j];
			if (1 <= origin_map[i][j] && origin_map[i][j] <= 5) {
				cctvPos.push_back(CCTV(i, j, map[i][j]));
			}
		}
	}
}

void updateMap(int x, int y, int dir) {

	int nx = x, ny = y;

	while (true) {
		nx = nx + dr[dir];
		ny = ny + dc[dir];
		if (!isInRange(nx, ny) || map[nx][ny] == 6) break;
		else if (map[nx][ny] == 0) map[nx][ny] = 7;
	}

}

void simulation() {

	int cctvCnt = cctvPos.size();

	for (int i = 0; i < cctvCnt; i++) quaternary *= 4;
	
	vector<int> caseVec; // 4진수 각 자리가 담김
	
	for (int tmp = 0; tmp < quaternary; tmp++) {
		
		caseVec.clear();
		copyOrigin();
		int tmpNumber = tmp;
		
		while (caseVec.size() < cctvCnt) {
			caseVec.push_back(tmpNumber % 4);
			tmpNumber /= 4;
		}
		
		for (int i = 0; i < cctvCnt; i++) {
			int type = cctvPos[i].type;
			if (type == 1) {
				updateMap(cctvPos[i].r, cctvPos[i].c, caseVec[i] % 4);
			}
			else if (type == 2) {
				updateMap(cctvPos[i].r, cctvPos[i].c, caseVec[i]);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 2) % 4);
			}
			else if (type == 3) {
				updateMap(cctvPos[i].r, cctvPos[i].c, caseVec[i]);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 1) % 4);
			}
			else if (type == 4) {
				updateMap(cctvPos[i].r, cctvPos[i].c, caseVec[i]);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 1) % 4);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 2) % 4);
			}
			else if (type == 5) {
				updateMap(cctvPos[i].r, cctvPos[i].c, caseVec[i]);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 1) % 4);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 2) % 4);
				updateMap(cctvPos[i].r, cctvPos[i].c, (caseVec[i] + 3) % 4);
			}


		}
	
		minRes = min(minRes, getEmptyCnt());

	}

}

int main() {

	init();

	input();

	simulation();

	printf("%d", minRes);

	return 0;
}