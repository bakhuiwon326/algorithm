#include<iostream>
#include<vector>
#include<queue>

#define INF 987654321
#define MAXV 20001

using namespace std;

struct Data {
	int node, weight;
	Data() {};
	Data(int node, int weight) : node(node), weight(weight) {};
	bool operator <(const Data d) const {
		return weight > d.weight;
	}
};

int v, e, k;
vector<Data> map[MAXV];
bool visited[MAXV];
priority_queue<Data> pq;
int dist[MAXV];

// 초기화  
void init() {
	for (int i = 0; i <= v; i++) {
		map[i].clear();
		dist[i] = INF;
		visited[i] = false;
	}
	priority_queue<Data> emptyPQ;
	swap(pq, emptyPQ);
}

int main() {

	// 입력받기 
	scanf("%d %d", &v, &e);
	scanf("%d", &k);

	init();

	for (int i = 1; i <= e; i++) {
		int a, b, c;
		scanf("%d %d %d", &a, &b, &c);
		map[a].push_back(Data(b, c));
	}

	pq.push(Data(k, 0));
	dist[k] = 0;
	
	while (!pq.empty()) {
		Data now = pq.top();
		pq.pop();
		if (!visited[now.node]) {
			visited[now.node] = true;
			for (int i = 0; i < map[now.node].size(); i++) {
				Data next = map[now.node][i];
				if (dist[next.node] > dist[now.node] + next.node) {
					dist[next.node] = dist[now.node] + next.weight;
					pq.push(Data(next.node, dist[next.node]));
				}
			}
		}
	}

	for (int i = 1; i <= v; i++) {
		if (visited[i]) printf("%d ", dist[i]);
		else printf("INF");
	}


	return 0;
}
