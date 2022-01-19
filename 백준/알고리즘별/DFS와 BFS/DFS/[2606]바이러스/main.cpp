#include<iostream>
#include<vector>
using namespace std;

#define MAXN 101

// 2606 바이러스 
vector<int> map[MAXN]; // 연결 
bool visited[MAXN]; // 방문여부 
int n, m; // n은 노드 개수, m은 간선 개수 
int cnt; // 1번 컴퓨터에 의해 감염된 컴퓨터 수 

void DFS(int v){
	for(int i = 0; i < map[v].size(); i++){
		int next = map[v][i];
		if(!visited[next]){
			visited[next] = true;
			cnt++;
			DFS(next);
		}
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	cin >> n >> m;
	
	// 초기화 
	for(int i = 0; i <= n; i++){
		map[i].clear();
	}
	fill_n(visited,sizeof(visited) / sizeof(visited[0]), false);
	cnt = 0;
	
	for(int i = 0; i < m; i++){
		int a, b;
		cin >> a >> b;
		map[a].push_back(b);
		map[b].push_back(a);
	}
	
	int start = 1;
	
	visited[start] = true;
	DFS(start);
	
	cout << cnt << endl;
		
	return 0;
}
 
