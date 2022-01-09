#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAXN 1000

using namespace std;

int n, m , v;
vector<int> map[MAXN]; // 연결 정보 
bool visited[MAXN]; // 방문여부 
queue<int> q;

void DFS(int v){
	visited[v] = true;
	cout << v << " ";
	if(v == n) return;
	else {
		for(int i = 0 ; i < map[v].size(); i++){
			int node = map[v][i];
			if(!visited[node]){
				DFS(node);
			}
		}
	}
}

void BFS(int v){
	q.push(v);
	while(!q.empty()){
		int root = q.front();
		q.pop();
		cout << root << " ";
		for(int i = 0; i < map[root].size(); i++){
			int node = map[root][i];
			if(!visited[i]){
				visited[i] = true;
				q.push(node);
			}
		}
	}
}

int compare(int a, int b){
	return a < b;
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> n >> m >> v;
	
	for(int i = 0; i < m; i++){
		int a,b;
		cin >> a >> b;
		map[a].push_back(b);
		map[b].push_back(a);
	}
	
	// ascending sort 
	for(int i = 0; i < n; i++){
		sort(map[i].begin(), map[i].end(), compare);
	}
	
	DFS(v);
	
	// visited false로 초기화 
	fill_n(visited, sizeof(visited) / sizeof(bool), false);
	
	cout << endl;
	
	BFS(v); 
	
	
	return 0;
}
