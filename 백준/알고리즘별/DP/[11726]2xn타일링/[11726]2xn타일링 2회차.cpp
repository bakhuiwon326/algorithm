#include <iostream>
#include <memory.h>

using namespace std;

int n;
int dp[1001];

int main() {

	memset(dp, 0, sizeof(dp));
	scanf("%d", &n);

	dp[1] = 1;
	dp[2] = 2;
	
	for (int i = 3; i <= n; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
	}

	printf("%d", dp[n]);

	return 0;
}