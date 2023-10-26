#include <iostream>

using namespace std;

int n;
int dp[100001];

int main() {

	scanf("%d", &n);

	dp[1] = 1;

	for (int i = 2; i <= n; i++) {
		dp[i] = i;
		for (int j = 1; j * j <= i; j++) {
			dp[i] = min(dp[i], dp[i - j * j] + 1);
		}
	}
	
	printf("%d", dp[n]);

	return 0;
}