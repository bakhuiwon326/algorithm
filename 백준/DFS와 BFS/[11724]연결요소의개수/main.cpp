#include<iostream>
#include<vector>

#define MAXN 1001

using namespace std;

vector<int> map[MAXN];
bool visited[MAXN];
int cnt;
int n, m;

void DFS(int v){
	for(int i = 0 ; i < map[v].size(); i++){
		int next = map[v][i];
		if(!visited[next]){
			visited[next] = true;
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
		visited[i] = false;
	}
	cnt = 0;
	
	// 입력
	for(int i = 0; i < m ; i ++) {
		int a, b;
		cin >> a >> b;
		map[a].push_back(b);
		map[b].push_back(a);
	}
	
	// 방문하지 않은 노드를 시작으로 DFS 탐색해서 연결요소 개수를 구한다 
	for(int i = 1 ; i <= n ; i++){
		if(!visited[i]){
			visited[i] = true;
			DFS(i);
			cnt++;
		}	
	}
	
	cout << cnt << endl;
	
	return 0;
	
}
