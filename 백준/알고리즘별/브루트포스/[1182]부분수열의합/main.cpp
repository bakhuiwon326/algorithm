#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>

using namespace std;

vector<int> vec;
int n, s, cnt;

void init() {
	vec.clear();
	n = 0;
	s = 0;
	cnt = 0;
}

void dfs(int depth, int sum) {
	if (depth == n) {
		if (sum == s) cnt++;
		return;
	}
	else {
		dfs(depth + 1, sum);
		dfs(depth + 1, sum + vec[depth]);
	}
}

int main() {

	init();

	scanf("%d %d", &n, &s);
	for (int i = 0; i < n; i++) {
		int tmp;
		scanf("%d", &tmp);
		vec.push_back(tmp);
	}

	dfs(0, 0);
	if (s == 0) cnt--; //s가 0이면 공집합을 제외시킨다.

	printf("%d", cnt);
	return 0;
}