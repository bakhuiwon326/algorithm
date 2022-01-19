#include<iostream>
#include<vector>
#include<algorithm>

# define MAXN 101

using namespace std;

int n , m, k, cnt, res;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
vector<int> area;

int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

void init(){
	for(int i = 0 ; i <= n; i++){
		for(int j = 0; j <= m; j++){
			map[i][j] = 0;
			visited[i][j] = false;
		}
	}
	area.clear();
	cnt = 0;
	res = 0;
}

void DFS(int i , int j){
	visited[i][j] = true;
	for(int i = 0 ; i < 4; i++){
		int row = i + dr[i];
		int col = j + dc[i];
		if(!visited[row][col] && map[row][col] == 1){
			res++;
			DFS(row, col);
		}
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> n >> m >> k;
	
	for(int i = 0 ; i < k; i++){
		int a,b; // 왼쪽 아래 
		int c,d; // 오른쪽 위
		cin >> a >> b >> c >> d;
		int x = d - b;
		int y = c - a;
		for(int j = 0; j < x; j++){
			for(int p = 0 ; p < y ; p++){
				map[x + j][y + p] = 1;
			}
		}
	}
	
	cout << "***********************************************************" << endl;
	
	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < m ; j++){
			cout << map[i][j] << " ";
		}
		cout << endl;
	}
	
	// dfs
	for(int i = 0; i < n; i++) {
		for(int j = 0 ; j < m; j++){
			if(map[i][j] == 1 && !visited[i][j]){
				DFS(i,j);
				cnt++;
				area.push_back(res);
				res = 0;
			}
		}
	}
	
	cout << cnt << endl;
	
	for(int i = 0; i < area.size(); i++) cout << area[i] << " ";
	return 0;
}


