#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

using namespace std;

int n, m, maxSafeZone;
int originMap[9][9];
int tmpMap[9][9];
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };
vector<pair<int, int>> virPos;
vector<int> res;

void init() {
	maxSafeZone = -1;
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			originMap[i][j] = 0;
			tmpMap[i][j] = 0;
		}
	}

}

void copyMap(int origin[9][9], int tmp[9][9]) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			tmp[i][j] = origin[i][j];
		}
	}
}


int safeZone(int map[9][9]) {
	int cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] == 0) {
				cnt++;
			}
		}
	}
	return cnt;
}

void bfs() {
	int spread[9][9];
	
	copyMap(tmpMap, spread);

	queue<pair<int, int>> q;
	for (int i = 0; i < virPos.size(); i++) {
		q.push(virPos[i]);
	}
	
	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		for (int j = 0; j < 4; j++) {
			int nr = r + dr[j];
			int nc = c + dc[j];
			if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
				if (spread[nr][nc] == 0) {
					spread[nr][nc] = 2;
					q.push(make_pair(nr, nc));
				}
			}
		}
	}
	maxSafeZone = max(maxSafeZone, safeZone(spread));
}

void combination(int cnt) {
	if (cnt == 3) {
		bfs();
		return;
	}
	else {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (tmpMap[i][j] == 0) {
					tmpMap[i][j] = 1;
					combination(cnt + 1);
					tmpMap[i][j] = 0;
				}
			}
		}
	}
}


int main() {

	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &originMap[i][j]);
			if (originMap[i][j] == 2) virPos.push_back(make_pair(i, j));
		}
	}
	
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (originMap[i][j] == 0) {
				copyMap(originMap, tmpMap);
				tmpMap[i][j] = 1;
				combination(1);
				tmpMap[i][j] = 0;
			}
		}
	}

	printf("%d", maxSafeZone);

	return 0;
}