#define _CRT_SECURE_NO_WARNINGS

#include<iostream>

using namespace std;

int n;
bool visited[8];
int results[8];

void init() {
	for (int i = 0; i < 8; i++) {
		visited[i] = false;
		results[i] = 0;
	}
}

void dfs(int depth) {
	if (depth == n) {
		for (int i = 0; i < n; i++) {
			printf("%d ", results[i]);
		}
		printf("\n");
		return;
	}
	else {
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				results[depth] = i + 1;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
}


int main() {

	init();

	scanf("%d", &n);

	dfs(0);

	
	return 0;
}