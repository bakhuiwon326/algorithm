#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 51

using namespace std;

int t,m,n,k,x,y, cnt;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};
vector<int> res;

void init(){
	cnt = 0;
	for(int i = 0; i <= n; i++){
		for(int j = 0; j <= n; j++){
			map[i][j] = 0;
			visited[i][j] = false;
		}
	}
}

void DFS(int i, int j){
	visited[i][j] = true;
	for(int p = 0; p < 4; p++){
		int row = i + dr[p];
		int col = j + dc[p];
		if(map[row][col] == 1 && !visited[row][col]){
			DFS(row, col);
		}
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> t;
	
	for(int i = 0; i < t; i++){
		cin >> m >> n >> k;
		init();
		for(int j = 0; j < k ; j++){
			cin >> x >> y;
			map[y][x] = 1;	
		}
	
		for(int j = 0 ; j < n; j++){
			for(int k = 0; k < m; k++){
				if(map[j][k] == 1 && !visited[j][k]){
					DFS(j, k);
					cnt++;
				}
			}
		}
		res.push_back(cnt);
	}
	
	// Ãâ·Â
	for(int i = 0; i < res.size(); i++){
		cout << res[i] << endl;
	} 
	
	return 0;
}
