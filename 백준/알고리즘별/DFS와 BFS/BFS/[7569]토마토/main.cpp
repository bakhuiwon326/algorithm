#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<queue>

using namespace std;

typedef struct {
	int m, n, h;
	int depth;
} tomato;

int m, n, h, totalTomato, cnt, depth, result;
queue<tomato> q;
int map[101][101][101];
bool visited[101][101][101];
int dist[101][101][101];
int dm[6] = {0,0,0,0,1,-1};
int dn[6] = {0,0,1,-1,0,0};
int dh[6] = {1,-1,0,0,0,0};

void init() {
	for (int i = 0; i < 101; i++) {
		for (int j = 0; j < 101; j++) {
			for (int k = 0; k < 101; k++) {
				visited[i][j][k] = false;
				map[i][j][k] = 0;
			}
		}
	}
	q = queue<tomato>();
	totalTomato = 0;
	cnt = 0;
	depth = 0;
	result = 0;
}

void bfs() {
	
	while (!q.empty()) {

		tomato now = q.front();
		q.pop();
		result = max(result, now.depth);

		for (int i = 0; i < 6; i++) {
			
			int next_h = now.h + dh[i];
			int next_n = now.n + dn[i];
			int next_m = now.m + dm[i];

			if (next_h >= 0 && next_h < h && next_n >= 0 && next_n < n && next_m >= 0 && next_m < m) {
				if (!visited[next_h][next_n][next_m] && map[next_h][next_n][next_m] == 0) {
					visited[next_h][next_n][next_m] = true;
					map[next_h][next_n][next_m] = 1; // 익은 토마토로 변경
					q.push({ next_m , next_n, next_h, now.depth + 1 });
				}
			}

		}
	}

	cnt = 0;
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				if (map[i][j][k] == 0) {
					printf("-1");
					return;
				}
			}
		}
	}
	
	printf("%d", result);

}

int main() {

	init();

	scanf("%d %d %d", &m, &n, &h);

	// 입력
	for (int i = 0; i < h; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k < m; k++) {
				scanf("%d", &map[i][j][k]);
				if (map[i][j][k] == 1) {
					q.push({ k,j,i,0});
					visited[i][j][k] = true;
				}
			}
		}
	}

	if (q.empty()) {
		printf("-1");
		return 0;
	}

	bfs();

	return 0;
}