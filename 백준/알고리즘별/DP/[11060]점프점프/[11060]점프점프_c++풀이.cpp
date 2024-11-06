#include<iostream>
#include<memory.h>
#include<algorithm>

#define MAX_INT 2147483647

using namespace std;

int n;
int maze[1001];
int dp[1001][101];

int main() {
	
	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &maze[i]);
	}

	for (int i = 0; i < 1001; i++) {
		for (int j = 0; j < 101; j++) {
			dp[i][j] = MAX_INT;
		}
	}

	for (int i = 0; i < 101; i++) {
		dp[1][i] = 0;
	}

	for (int pos = 1; pos <= n; pos++) {
		for (int prevJump = 1; prevJump < 101; prevJump++) {
			if (dp[pos][prevJump] == MAX_INT) continue;
			for (int jump = 1; jump <= maze[pos]; jump++) {
				int nextPos = pos + jump;
				if (nextPos > n) continue;
				dp[nextPos][jump] = min(dp[nextPos][jump], dp[pos][prevJump] + 1);
			}
		}
	}

	int answer = MAX_INT;
	for (int i = 1; i < 101; i++) {
		answer = min(dp[n][i], answer);
	}

	if (answer == MAX_INT) answer = -1;

	printf("%d", answer);

}