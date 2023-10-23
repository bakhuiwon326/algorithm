#include <iostream>
#include <vector>

using namespace std;

int n;
long long dp[1000001];


int main() {
	scanf("%d", &n);
	// 바로 이전의 이진수 맨 오른쪽 끝에 1을 붙인 것.
	// 바로 이전이전의 이진수 맨 오른쪽 끝에 0을 붙인것.
	// 이 두개를 모두 더한것!
	dp[1] = 1;
	dp[2] = 2;

	for (int i = 3; i<= n; i++) {
		dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
	}

	printf("%lld", dp[n]);

	return 0;
}