#include <iostream>
#include<cstring>
#include<vector> 

using namespace std;

int n, m;
int map[501][501];
int maxValue;
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };
bool visit[501][501];

void init() {
	memset(map, 0, sizeof(map));
	memset(map, false, sizeof(visit));
	maxValue = -1;
}

bool isRange(int x, int y) {
	return x >= 0 && x < n&& y >= 0 && y < m;
}

void dfs(int depth, int r, int c, int sum) {
	if (depth == 4) {
		maxValue = max(maxValue, sum);
		return;
	}
	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if (isRange(nr, nc) && !visit[nr][nc]) {
			visit[nr][nc] = true;
			dfs(depth + 1, nr, nc, sum + map[nr][nc]);
			visit[nr][nc] = false;
		}
	}
	if (r - 1 >= 0 && c - 1 >= 0 && r + 1 <= n) { //っ
		maxValue = max(maxValue, (map[r - 1][c] + map[r][c - 1] + map[r][c] + map[r + 1][c]));
	}
	if (r - 1 >= 0 && c + 1 <= m && r + 1 <= n) { //た
		maxValue = max(maxValue, (map[r - 1][c] + map[r][c + 1] + map[r][c] + map[r + 1][c]));
	}
	if (c - 1 >= 0 && c + 1 <= m && r + 1 <= n) { //で
		maxValue = max(maxValue, (map[r][c] + map[r + 1][c] + map[r + 1][c - 1] + map[r + 1][c + 1]));
	}
	if (c - 1 >= 0 && c + 1 <= m && r + 1 <= n) { //ぬ
		maxValue = max(maxValue, (map[r][c - 1] + map[r][c] + map[r][c + 1] + map[r + 1][c]));
	}
}

int main() {

	init();

	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			visit[i][j] = true;
			dfs(1, i, j, map[i][j]);
			visit[i][j] = false;
		}
	}

	printf("%d", maxValue);

	return 0;

}