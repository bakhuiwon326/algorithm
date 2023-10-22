#include <iostream>

using namespace std;

int dp[1001]; // 돌이 n개일 때 게임 중 턴이 돈 횟수
int n;
int main() {
	// 문제에서, "완벽하게 게임을 했다"는 "서로 가져간 돌의 횟수가 최소"임을 의미
	string res = "";

	scanf("%d", &n);

	dp[1] = 1;
	dp[2] = 2;
	dp[3] = 1;

	for (int i = 4; i <= n; i++) {
		dp[i] = min(dp[i - 1] + 1, dp[i - 3] + 1);
	}

	// n개의 돌이 있을 때 돌을 돌아가며 가져간 turn 횟수가 짝수면 창영이가 이김. 홀수면 상근이가 이김
	res = dp[n] % 2 == 0 ? "CY" : "SK";

	printf("%s", res.c_str());

	return 0;
}