#include <iostream>

using namespace std;

int n, k;
int dp[1001][1001];

int main() {
	
	// nCr = n-1Cr + n-1Cr-1을 이용한다.

	scanf("%d %d", &n, &k);

	dp[1][1] = 1;
	dp[1][0] = 1;

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j <= k; j++) {
			if (j == 0 || i == j) dp[i][j] = 1;
			else dp[i][j] = (dp[i - 1][j] + dp[i - 1][ j - 1]) % 10007;
		}
	}
	
	printf("%d", dp[n][k]);

	return 0;
}