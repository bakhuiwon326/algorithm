#include<iostream>

using namespace std;

int score[301];
int dp[301][2];
int n;

int main() {

	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &score[i]);
	}

	// ÃÊ±âÈ­
	dp[1][1] = score[1];
	dp[2][1] = score[2];
	dp[2][2] = score[1] + score[2];

	if (n >= 3) {
		for (int i = 3; i <= n; i++) {
			dp[i][1] = max(dp[i - 2][1], dp[i-2][2]) + score[i];
			dp[i][2] = dp[i - 1][1] + score[i];
		}
	}

	printf("%d\n", max(dp[n][1], dp[n][2]));

	return 0;
}