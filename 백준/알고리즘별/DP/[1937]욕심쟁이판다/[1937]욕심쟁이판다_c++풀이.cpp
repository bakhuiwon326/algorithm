#include<iostream>
#include<algorithm>
#include<memory.h>

using namespace std;

int n, answer;
int map[500][500];
int dp[500][500];
int dr[4] = { 1,-1,0,0 };
int dc[4] = { 0,0,1,-1 };

bool inRange(int r, int c) {
	return r >= 0 && r < n && c >= 0 && c < n;
}

int dfs(int r, int c) {
	if (dp[r][c] > 0) {
		return dp[r][c];
	}
	dp[r][c] = 1;
	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		if (inRange(nr, nc) && map[r][c] < map[nr][nc]) {
			dp[r][c] = max(dp[r][c], dfs(nr, nc) + 1);
		}
	}
	return dp[r][c];
}

int main() {

	scanf("%d", &n);
	memset(dp, -1, sizeof(dp));
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	answer = -1;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			answer = max(dfs(i, j), answer);
		}
	}

	printf("%d", answer);

}
