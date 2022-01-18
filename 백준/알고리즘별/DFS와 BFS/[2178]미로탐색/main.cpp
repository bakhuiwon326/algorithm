#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

#define MAXN 102

using namespace std;

int n, m;
int map[MAXN][MAXN];
int dist[MAXN][MAXN];
queue<pair<int,int> > q;

int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

void init(){
	for(int i = 0; i <= n; i++){
		for(int j = 0; j <= m; j++){
			map[i][j] = 0;
			dist[i][j] = 0;
		}
	}
	queue<pair<int,int> > qq;
	swap(q,qq);
}

void BFS(int a, int b){	
	while(!q.empty()){
		// 가장 먼저 방문한 노드임. 맨 처음 실행할 때에는 시작점(1,1) 노드이다. 
		// 인접한 노드들을 찾기 위해 기준이 되는 노드라 생각. 
		int r = q.front().first;
		int c = q.front().second;
		q.pop();
		
		for(int i = 0; i < 4; i++){
			int row = r + dr[i];
			int col = c + dc[i];
			if(row <= 0 || row > n || col <= 0 || col > m ) continue;
			if(dist[row][col] == 0 && map[row][col] == 1){
				q.push(make_pair(row,col));
				dist[row][col] = dist[r][c] + 1;
			}
		}	
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	init(); 
	
	// 입력 
	cin >> n >> m; 
	for(int i = 1 ; i <= n; i++){
		for(int j = 1 ; j <= m ; j++){
			char tmp;
			cin >> tmp;
			map[i][j] = tmp - '0';
		}
	}
	
	q.push(make_pair(1,1)); // 시작점 (1,1)을 큐에 넣는다. 
	dist[1][1] = 1; // 종료점까지 지난 노드의 개수를 출력해야하기 때문에 dist[1][1] = 1이다. 
	BFS(1,1); // BFS를 실행하여 (1,1) 노드와 인접한 노드들을 방문한다. 
	
	cout << dist[n][m] << endl;
	
	return 0;
}
