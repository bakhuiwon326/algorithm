#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>

#define MAXI 301

using namespace std;

int t, n, a, b, c, d;
bool visited[MAXI][MAXI];
int dist[MAXI][MAXI];
int dr[8] = { 2, 1, -1, -2, -2, -1, 1, 2 };
int dc[8] = { 1, 2, 2, 1, -1, -2, -2, -1 };
queue<pair<int,int> > q;
vector<int> res;

void init(){
	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < n ; j++){
			visited[i][j] = false;
			dist[i][j] = 0;
		}
	}
	queue<pair<int,int> > qq;
	swap(q,qq);
}

void BFS(){
	visited[a][b] = true;
	q.push(make_pair(a,b));
	while(!q.empty()){
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		if(x == c && y == d) break;
		for(int i = 0; i < 8; i++){
			int row = x + dr[i];
			int col = y + dc[i];
			if(row < 0 || row >= n || col < 0 || col >= n) continue;
			if(!visited[row][col]){
				visited[row][col] = true;
				dist[row][col] = dist[x][y] + 1;
				q.push(make_pair(row,col));
			}
		}
	}
} 

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> t;
	
	while(t--){
		cin >> n;
		cin >> a >> b; // 시작점 
		cin >> c >> d; // 도착점 
		init();
		BFS(); // 도착지점만 넘긴다 
		res.push_back(dist[c][d]);
	}
	
	for(int i = 0 ; i < res.size(); i++){
		cout << res[i] << endl;
	}
	
}
