#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<queue>
#include<cstring>

using namespace std;


char map[51][51];
int d[51][51] = {-1, };
int n, m, cnt;
int maxValue = -1;
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };
queue<pair<int, int>> q;

void init() {
	memset(d, -1, sizeof(d));
	cnt = 0;
	queue<pair<int, int>> nq;
	q = nq;
}

void bfs(int i, int j) {
	
	init();

	q.push(make_pair(i, j));
	d[i][j] = 0;

	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		for (int k = 0; k < 4; k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			if (d[nr][nc] == -1 && map[nr][nc] == 'L' && nr >= 0 && nr < n && nc >= 0 && nc < m) {
				q.push(make_pair(nr, nc));
				d[nr][nc] = d[r][c] + 1;
				maxValue = max(maxValue, d[nr][nc]);
			}
		}
	}


}


int main() {

	init();

	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; i++) {
		cin.ignore();
		for (int j = 0; j < m; j++) {
			scanf("%1c", &map[i][j]);
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			if(map[i][j] == 'L') bfs(i, j);
		}
	}

	printf("%d", maxValue);

	return 0;
} 
  