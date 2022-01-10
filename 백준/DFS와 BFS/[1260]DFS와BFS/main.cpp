#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

#define MAXN 1001

using namespace std;

int n, m , v;
vector<int> map[MAXN]; // 인접노드 정보 
bool visited[MAXN]; // 방문여부 
queue<int> q; // 큐

// 재귀를 이용한 DFS 
void DFS(int v){
	visited[v] = true; // v노드를 방문 
	cout << v << " "; // 방문한 노드를 출력 
	// v노드와 어떤 노드가 연결되어있는지 확인한다. 
	for(int i = 0 ; i < map[v].size(); i++){
		int node = map[v][i]; // v노드와 연결된 i번째 노드를 node에 저장한다 
		if(!visited[node]){ // node가 아직 방문하지 않은 노드라면, DFS를 실행한다. 
			DFS(node);
		}
	}
}

// Queue 자료구조를 활용한 BFS 
void BFS(int v){
	q.push(v); // 우선, 처음으로 방문한 v노드를 queue에 push한다.
	visited[v] = true; // 첫번째 노드를 방문했다고 표시한다 
	// 방문한 노드와 인접한 노드를  queue에 push하고, 인접한 노드를 알기 위해 기준이 되는 노드는 queue에서 pop 한다
	// 모든 노드를 pop하여 queue가 비었으면 종료 
	while(!q.empty()){ 
		int root = q.front(); // 기준이 되는 노드 정보를 저장 
		q.pop(); // queue에서 pop
		cout << root << " ";
		for(int i = 0; i < map[root].size(); i++){
			int node = map[root][i]; // 인접한 노드 정보를 node에 저장 
			if(!visited[node]){  
				visited[node] = true; // 방문했다고 표시하기 
				q.push(node); // 인접한 노드를 queue에 push 한다. 
			}
		}
	}
}

// 오름차순 정렬을 위한 compare 함수 
int compare(int a, int b){
	return a < b;
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> n >> m >> v;
	
	// 인접 노드 정보를 저장한다 
	for(int i = 0; i < m; i++){
		int a,b;
		cin >> a >> b;
		map[a].push_back(b);
		map[b].push_back(a);
	}
	
	// 각 인접리스트를 오름차순 정렬한다 
	for(int i = 0; i < n; i++){
		sort(map[i].begin(), map[i].end(), compare);
	}
	
	// DFS 실행 
	DFS(v);
	
	// visited배열이 전역변수이기 때문에, BFS실행을 위해 모든 원소를 false로 초기화 시킨다 
	fill_n(visited, sizeof(visited) / sizeof(bool), false);
	
	cout << endl;
	
	// BFS 실행
	BFS(v); 
	
	return 0;
	
}
