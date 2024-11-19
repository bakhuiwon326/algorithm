#include<iostream>
#include<queue>

// dp 아님.. 왜냐? bfs의 경우, 가능한 모든 경로를 탐색해 목표에 도달한 경로가 최단 경로임을 보장한다.
// 하지만, dp는 보통 단방향에 사용된다. 해당 문제의 경우 양방향으로 움직일 수 있기 때문에 정답을 보장하지 않음
// 양방향으로 점프하는 경로를 찾아야하기 때문에 bfs를 사용한다.
// "배수만큼 떨어져 있는"은 양방향을 의미한다. 양의곱, 음의곱

using namespace std;

int n, a, b;
int bridge[10001];
int dist[10001];
bool visited[10001];

void bfs() {

	queue<int> q;

	q.push(a);
	visited[a] = true;

	while (!q.empty()) {
		
		int now = q.front();
		q.pop();

		if (now == b) {
			return;
		}

		// ->
		for (int next = now + bridge[now]; next <= n; next += bridge[now]) {
			if (!visited[next]) {
				q.push(next);
				visited[next] = true;
				dist[next] = dist[now] + 1;
			}
		}

		// <-
		for (int next = now - bridge[now]; next >= 1; next -= bridge[now]) {
			if (!visited[next]) {
				q.push(next);
				visited[next] = true;
				dist[next] = dist[now] + 1;
			}
		}
	}

}

int main() {
	
	scanf("%d", &n);

	for (int i = 1; i <= n; i++) {
		scanf("%d", &bridge[i]);
	}

	scanf("%d %d", &a, &b);

	bfs();
	if (dist[b] == 0) dist[b] = -1;

	
	printf("%d", dist[b]);
	
}