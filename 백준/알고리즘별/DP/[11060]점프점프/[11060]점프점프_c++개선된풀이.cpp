#include<iostream>
#include<memory.h>
#include<algorithm>

#define MAX_INT 2147483647

using namespace std;

int n;
int maze[1001];
int dp[1001];

int main() {
	
	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &maze[i]);
	}

	for (int i = 0; i < 1001; i++) {
		dp[i] = MAX_INT;
	}

	dp[1] = 0;

	for (int pos = 1; pos <= n; pos++) {
		if (dp[pos] == MAX_INT) continue;
		for (int jump = 1; jump <= maze[pos]; jump++) {
			int next = pos + jump;
			if (next > n) break;
			dp[next] = min(dp[next], dp[pos] + 1);
		}
	}

	int answer = MAX_INT;
	
	answer = dp[n];
	if (answer == MAX_INT) answer = -1;

	printf("%d", answer);

}