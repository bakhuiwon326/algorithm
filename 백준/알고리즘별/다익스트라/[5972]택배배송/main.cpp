#define _CRT_SECURE_NO_WARNINGS
#define INFI 2147483647

#include<iostream>
#include<queue>
#include<cstring>
#include<vector>

using namespace std;

typedef struct road {
	int vertexNum;
	long long weight;
	road(int vertexNum, long long weight) : vertexNum(vertexNum), weight(weight) {}
	bool operator<(const road r) const {
		return this->weight > r.weight;
	}
};

int n, m, a, b, c = 0;
int startVertex = 1;
long long d[50001] = {INFI, };
vector<pair<int, int>> adj[50001]; // <adjVertex, weight>
priority_queue<road> pq;

void init() {
	startVertex = 1;
	n, m, a, b, c = 0;
	priority_queue<road> new_pq;
	pq = new_pq;
	for (int i = 0; i < 50001; i++) {
		adj[i].clear();
		d[i] = INFI;
	}
}

void dijkstra() {
	pq.push(road(startVertex,0));
	d[startVertex] = 0;
	while (!pq.empty()) {
		road now = pq.top();
		pq.pop();
		for (int i = 0; i < adj[now.vertexNum].size(); i++) {
			int adjVertex = adj[now.vertexNum][i].first;
			int cost = adj[now.vertexNum][i].second;
			if (d[adjVertex] > d[now.vertexNum] + cost) {
				d[adjVertex] = d[now.vertexNum] + cost;
				pq.push(road(adjVertex, d[adjVertex]));
			}
		}
	}
}

int main() {

	init();

	scanf("%d %d", &n, &m);

	for (int i = 0; i < m; i++) {
		scanf("%d %d %d", &a, &b, &c);
		adj[a].push_back(make_pair(b, c));
		adj[b].push_back(make_pair(a, c));
	}

	dijkstra();

	printf("%d", d[n]);

	return 0;
} 
  