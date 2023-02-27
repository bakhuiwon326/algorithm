#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<queue>

using namespace std;

queue<pair<int, int>> q;
int f, s, g, u, d;
bool visited[1000001];

void init() {
	f, s, g, u, d = 0;
	q = queue<pair<int, int>>();
	for (int i = 0; i < 1000001; i++) visited[i] = false;
}

void bfs() {

	while (!q.empty()) {

		int stair = q.front().first;
		int cnt = q.front().second;

		q.pop();

		if (stair == g) {
			printf("%d", cnt);
			return;
		}

		if (stair + u >= 1 && stair + u <= f && !visited[stair + u]) {
			q.push({ stair + u, cnt + 1 });
			visited[stair + u] = true;
		}

		if (stair - d >= 1 && stair - d <= f && !visited[stair - d]) {
			q.push({ stair - d, cnt + 1 });
			visited[stair - d] = true;
		}

	}

	if (q.empty()) {
		printf("use the stairs");
		return;
	}

}

int main() {

	init();

	scanf("%d %d %d %d %d", &f, &s, &g, &u, &d);

	q.push({ s, 0 });
	visited[s] = true;

	bfs();

	return 0;
}