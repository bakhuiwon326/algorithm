#include <iostream>

using namespace std;

int n;
int dp[1000001];

int main() {

	scanf("%d", &n);

	dp[1] = 0;
	dp[2] = 1;
	dp[3] = 1;

	for (int i = 4; i <= n; i++) {
		if (i % 3 == 0 && i % 2 == 0) {
			dp[i] = min(1 + dp[i / 3], 1 + dp[i / 2]);
			dp[i] = min(1 + dp[i - 1], dp[i]);
		}
		else if (i % 3 == 0) {
			dp[i] = min(1 + dp[i / 3], 1 + dp[i - 1]);
		}
		else if(i % 2 == 0){
			dp[i] = min(1 + dp[i / 2], 1 + dp[i - 1]);
		}
		else {
			dp[i] = 1 + dp[i - 1];
		}
	}

	printf("%d", dp[n]);

	return 0;
}