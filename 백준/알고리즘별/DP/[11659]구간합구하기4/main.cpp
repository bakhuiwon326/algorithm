#include<iostream>

using namespace std;

int dp[100004], val[100004];
int n, m;

int main() {

	scanf("%d %d", &n, &m);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &val[i]);
	}

	// dpÃ¤¿ì±â
	dp[1] = val[1];
	for (int i = 2; i <= n; i++) {
		dp[i] = dp[i - 1] + val[i];
	}

	for (int i = 0; i < m; i++) {
		int a, b;
		scanf("%d %d", &a, &b);
		printf("%d\n", dp[b] - dp[a-1]);
	}

	return 0;
}