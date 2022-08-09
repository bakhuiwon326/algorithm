#include<iostream>

#define MAXN 16

using namespace std;

int n, nowDay, nextDay, last;
int t[MAXN], p[MAXN], dp[MAXN];

void init() {
	last = 0;
	n = 0;
	nowDay = 0;
	nextDay = 0;
	for (int i = 0; i < MAXN; i++) {
		t[i] = 0;
		p[i] = 0;
		dp[i] = 0;
	}
}

int main() {

	init();

	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d %d", &t[i], &p[i]);
	}

	for (int i = n; i > 0; i--) {
		if (i + t[i] > n + 1) {
			// 상담 ㅂㄱㄴ
			dp[i] = dp[i + 1];
		}
		else {
			// 상담 ㄱㄴ
			dp[i] = max(dp[i + 1], dp[i + t[i]] + p[i]);
		}
	}

	printf("%d\n", dp[1]);

	return 0;
}