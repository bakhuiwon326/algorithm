#include<iostream>
#include<vector>

#define MAXN 101

using namespace std;

int n, a, b, m, cnt;
vector<int> family[MAXN];
bool visited[MAXN];
bool isFind;

void DFS(int start, int count) {
	visited[start] = true;
	if (start == b) {
		cnt = count;
		isFind = true;
	}

	for (int i = 0; i < family[start].size(); i++) {
		int next = family[start][i];
		if (!visited[next]) {
			DFS(next, count + 1);
			if (isFind) return;
		}
	}

}


int main() {

	scanf("%d", &n);
	scanf("%d %d", &a, &b);
	scanf("%d", &m);

	for (int i = 1; i <= m; i++) {
		int x, y;
		scanf("%d %d", &x, &y);
		family[x].push_back(y);
		family[y].push_back(x);
	}

	DFS(a, 0);

	if (!isFind) printf("\n-1");
	else printf("\n%d", cnt);

	return 0;

}