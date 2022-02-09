#include<iostream>
#include<vector>

#define MAXN 100001

using namespace std;

int n, node1, node2, root;
int parents[MAXN];
vector<int> map[MAXN];
bool visited[MAXN];

void init(){
	for(int i = 0; i <= n; i++){
		map[i].clear();
		visited[i] = false;
	}
}

void DFS(int root){
	visited[root] = true;
	for(int i = 0 ; i < (int)map[root].size() ; i++){
		if(!visited[map[root][i]]){
			parents[map[root][i]] = root;
			DFS(map[root][i]);
		}		
	}
}

int main(){
	
	scanf("%d", &n);
	
	init();
	
	// 입력 
	for(int i = 0 ; i < n-1; i++){
		scanf("%d %d", &node1, &node2);
		map[node1].push_back(node2);
		map[node2].push_back(node1);
	}
	
	root = 1;

	DFS(root);
	
	// 출력
	for(int i = 2 ; i <= n; i++){
		printf("%d\n", parents[i]);
	} 
	
	return 0;
}
