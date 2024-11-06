#include<iostream>
#include<memory.h>

int n, m;
int dp[500][500];
int map[500][500];
int dr[4] = { 0,0,1,-1 };
int dc[4] = { 1,-1,0,0 };

bool inRange(int r, int c) {
	return r >= 0 && r < n && c >= 0 && c < m;
}

int dfs(int r, int c) {

	if (dp[r][c] != -1) {
		return dp[r][c];
	}

	if (r == n - 1 && c == m - 1) {
		dp[r][c] = 1;
		return dp[r][c];
	}

	dp[r][c] = 0;

	for (int i = 0; i < 4; i++) {
		int nr = r + dr[i];
		int nc = c + dc[i];
		
		if (inRange(nr, nc) && map[r][c] > map[nr][nc]) {
			if (dp[nr][nc] != -1) {
				dp[r][c] += dp[nr][nc];
			}
			else {
				dp[r][c] += dfs(nr, nc);
			}
		}
	}

	return dp[r][c];

}

int main() {
	
	memset(dp, -1, sizeof(dp));

	scanf("%d %d", &n, &m);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			scanf("%d", &map[i][j]);
		}
	}

	dfs(0, 0);

	printf("%d", dp[0][0]);

}