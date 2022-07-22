#include<iostream>

using namespace std;

int dp[1000001], pre[1000001];
int n;

int main() {

	scanf("%d", &n);

	dp[1] = 0;

	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + 1;
		pre[i] = i - 1;
		if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
			dp[i] = dp[i / 3] + 1;
			pre[i] = i / 3;
		}
		if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
			dp[i] = dp[i / 2] + 1;
			pre[i] = i / 2;
		}
	}


	printf("%d\n", dp[n]);
	
	int index = n;
	printf("%d ", index);
	while (index > 1) {
		printf("%d ", pre[index]);
		index = pre[index];
	}

	return 0;
}