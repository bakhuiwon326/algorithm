#include<iostream>
#include<vector>
#include<queue>

#define MAXN 101

using namespace std;

int n, m, cnt;
int start = 1;
bool visited[MAXN];
vector<int> adj[MAXN];
queue<int> q;

void init(){
	
	n = 0;
	m = 0;
	cnt = 0;
	
	for(int i = 0; i < MAXN ; i++){
		visited[i] = false;
	}
	
	for(int i = 0; i < MAXN; i++){
		adj[i].clear();	
	}
	
	queue<int> next_q;
	
	q = next_q;
	
}

void BFS(int start){
	
	visited[start] = true;
	q.push(start);
	
	while(!q.empty()){
		int now = q.front();
		q.pop();
		for(int i = 0; i < adj[now].size() ; i++){
			int next = adj[now][i];
			if(!visited[next]){
				visited[next] = true;
				q.push(next);
				cnt++;
			}
		} 
	}
	
}


int main(){
	
	init();
	
	scanf("%d %d", &n, &m);
	
	for(int i = 0; i < m; i++){
		int start, end;
		scanf("%d %d", &start, &end);
		adj[start].push_back(end);
		adj[end].push_back(start);
	}
	
	// bfs
	BFS(start);
	
	printf("%d", cnt);
	return 0;
	
}
