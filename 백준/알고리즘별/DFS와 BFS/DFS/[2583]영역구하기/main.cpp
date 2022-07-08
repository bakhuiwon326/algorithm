#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 101

using namespace std;

int n, m, k, cnt;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
vector<int> area;
int dr[4] = { 0,0,-1,1 };
int dc[4] = { 1,-1,0,0 };

void DFS(int row, int col) {

	cnt++;
	visited[row][col] = true;
	
	for (int i = 0; i < 4; i++) {
		int x = row + dr[i];
		int y = col + dc[i];
		if (x >= 0 && x < m && y >= 0 && y < n) {
			if (map[x][y] == 0 && !visited[x][y]) {
				DFS(x, y);
			}
		 }
	}


}

int main() {

	scanf("%d %d %d", &m, &n, &k);

	// 직사각형 채우기
	for (int i = 0; i < k; i++) {
		
		int a, b, c, d;
		scanf("%d %d %d %d", &a, &b, &c, &d);
		
		for (int j = b; j < d; j++) {
			for (int l = a; l < c; l++) {
				map[j][l] = 1;
			}
		}

	}

	// DFS 실행
	for (int i = 0; i < m; i++) {
		for (int j = 0; j < n; j++) {
			if (!visited[i][j] && map[i][j] == 0) {
				DFS(i, j);
				area.push_back(cnt);
				cnt = 0;
			}
		}
	}

	printf("%d\n", area.size());

	sort(area.begin(), area.end());

	for (int i = 0; i < area.size(); i++) {
		printf("%d ", area[i]);
	}



	return 0;
}