#include <iostream>
#include<memory.h>
#include<queue>

using namespace std;

int n, m, cnt, res;
int map[301][301];// 오리지널
int tmp_map[301][301];
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };
bool visited[301][301];

void init() {
	memset(map, 0, sizeof(map));
	cnt = 1;
}

void input() {
	scanf("%d %d", &n, &m);
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}
}

bool isInRange(int x, int y) {
	return x >= 0 && x < n&& y >= 0 && y < m;
}

bool isAllMelt() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if (map[i][j] != 0) return false;
		}
	}
	return true;
}


void copy(int(*origin)[301], int(*tmp)[301]) {
	for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) tmp[i][j] = origin[i][j];
}

void decrease(int r, int c) {

	int empty = 0;

	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if (isInRange(nr, nc) && map[nr][nc] == 0) {
			empty++;
		}
	}
	tmp_map[r][c] -= empty;
	if (tmp_map[r][c] < 0) tmp_map[r][c] = 0;
}

void dfs(int r, int c) {
	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if (isInRange(nr, nc) && map[nr][nc] != 0 && !visited[nr][nc]) {
			visited[nr][nc] = true;
			dfs(nr, nc);
		}
	}
}

int main() {

	init();

	input();

	while (true) {
	
		// 빙하 감소시키기
		copy(map, tmp_map);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) continue;
				decrease(i, j);
			}
		}

		copy(tmp_map, map);

		cnt = 0;
		memset(visited, false, sizeof(visited));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] || map[i][j] == 0) continue;
				dfs(i, j);
				cnt++;
			}
		}
		
		res++;

		// 모두 녹았으면 0 출력
		if (cnt == 0) {
			res = 0;
			break;
		}
		else if (cnt >= 2) break;

	}

	printf("%d\n", res);

	return 0;

}