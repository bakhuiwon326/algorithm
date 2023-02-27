#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<queue>

using namespace std;

queue<pair<int, int>> q;
int n, k, result;
bool visited[100001];

void init() {
	queue<pair<int, int>> tmpQ;
	q = tmpQ;
	n, k, result = 0;
	for (int i = 0; i < 100001; i++) visited[i] = false;
}

void bfs() {

	while (!q.empty()) {

		int node = q.front().first;
		int time = q.front().second;
		q.pop();

		if (node == k) {
			result = time;
			break;
		}

		if (!visited[node + 1] && node + 1 >= 0 && node + 1 <= 100000) {
			q.push({ node + 1, time + 1 });
			visited[node + 1] = true;
		}

		if (!visited[node - 1] && node + 1 >= 0 && node - 1 <= 100000) {
			q.push({ node - 1, time + 1 });
			visited[node - 1] = true;
		}

		if (!visited[node * 2] && node + 1 >= 0 && node * 2 <= 100000) {
			q.push({ node * 2, time + 1 });
			visited[node * 2] = true;
		}
	}

}

int main() {

	init();

	scanf("%d %d", &n, &k);

	q.push({ n, 0 });
	visited[n] = true;
	
	bfs();

	printf("%d", result);

	return 0;
}