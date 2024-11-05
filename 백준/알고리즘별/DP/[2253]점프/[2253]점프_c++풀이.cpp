#include<iostream>
#include<algorithm>
#include<vector>
#include<memory.h>

#define INT_MAX_VALUE 2147483647

using namespace std;

int n, m;
int dp[10001][150];
bool blocked[10001];

int main() {
	

	scanf("%d %d", &n, &m);

	for (int i = 0; i < m; i++) {
		int num;
		scanf("%d", &num);
		blocked[num] = true;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j < 150; j++) {
			dp[i][j] = INT_MAX_VALUE;
		}
	}

	dp[2][1] = 1;

	for (int now = 2; now < n; now++) {
		if (blocked[now]) continue;
		for (int prevJump = 1; prevJump < 150; prevJump++) {
			for (int jump = prevJump - 1; jump <= prevJump + 1; jump++) {
				int next = now + jump;
				if (next > n || blocked[next] || jump >= 150 || jump <= 0 || dp[now][prevJump] == INT_MAX_VALUE) continue;
				//printf("%d vs %d\n", dp[next][jump], dp[now][prevJump] + 1);
				dp[next][jump] = min(dp[next][jump], dp[now][prevJump] + 1);
			}
		}
	}

	int answer = INT_MAX_VALUE;

	for (int i = 1; i < 150; i++) {
		answer = min(answer, dp[n][i]);
	}

	if (answer == INT_MAX_VALUE) {
		answer = -1;
	}
	printf("%d", answer);


}