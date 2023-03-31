#define _CRT_SECURE_NO_WARNINGS
#define INF 2147483647

#include<iostream>
#include<vector>
#include<cstring>
#include<queue>

using namespace std;

int n;
int map[125][125];
int dist[125][125];
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };
queue<pair<int, int>> q;
vector<int> res;

void startInit() {
	res.clear();
}

void init() {
	for (int i = 0; i < 125; i++) {
		for (int j = 0; j < 125; j++) {
			dist[i][j] = INF;
			map[i][j] = 0;
		}
	}
	queue<pair<int, int>> nq;
	q = nq;
}

void bfs() {
	q.push(make_pair(0, 0));
	dist[0][0] = map[0][0];
	while (!q.empty()) {
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
				if (dist[nr][nc] > dist[r][c] + map[nr][nc]) {
					dist[nr][nc] = dist[r][c] + map[nr][nc];
					q.push(make_pair(nr, nc));
				}
			}
		}
	}
	
	res.push_back(dist[n - 1][n - 1]);

}

int main() {

	startInit();

	while (true) {

		scanf("%d", &n);
		
		if (n == 0) break;
		
		init();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				scanf("%d", &map[i][j]);
			}
		}

		bfs();

	}

	for (int i = 0; i < res.size(); i++) {
		printf("Problem %d: %d\n", i + 1, res[i]);
	}

	return 0;
} 
  