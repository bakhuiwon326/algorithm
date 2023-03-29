#define _CRT_SECURE_NO_WARNINGS
#define MAXV 20001
#define INF 2147483647

#include<iostream>
#include<vector>
#include<cstring>
#include<queue>

using namespace std;

typedef struct graph {
	int vertex;
	int weight;
	graph(int vertex, int weight) : vertex(vertex), weight(weight) {}
	bool operator<(const graph g) const {
		return this->weight > g.weight;
	}
};

int v, e, k, startV, endV, weight, res;
vector<graph> adj[MAXV];
int d[MAXV];
priority_queue<graph> pq;

void init() {
	priority_queue<graph> new_pq;
	pq = new_pq;
	for (int i = 0; i < MAXV; i++) {
		adj[i].clear();
		d[i] = INF;
	}
}

void dijkstra() {

	pq.push(graph(k, 0));
	d[k] = 0;

	while (!pq.empty()) {
		int nowVertex = pq.top().vertex;
		pq.pop();
		for (int i = 0; i < adj[nowVertex].size(); i++) {
			int adjVertex = adj[nowVertex][i].vertex;
			int adjWeight = adj[nowVertex][i].weight;
			if (adjWeight == d[adjVertex]) {
				continue;
			}
			else if (d[adjVertex] > d[nowVertex] + adjWeight) {
				d[adjVertex] = d[nowVertex] + adjWeight;
				pq.push(graph(adjVertex, d[adjVertex]));
			}
		}
	}
}

int main() {

	init();

	scanf("%d %d", &v, &e);
	scanf("%d", &k);

	for (int i = 0; i < e; i++) {
		scanf("%d %d %d", &startV, &endV, &weight);
		adj[startV].push_back(graph(endV, weight));
	}

	dijkstra();

	for (int i = 1; i <= v; i++) {
		if (d[i] == INF) printf("INF\n");
		else printf("%d\n", d[i]);
	}

	return 0;
} 
  