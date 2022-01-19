#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 101

using namespace std;

int n;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
int maxLimit; 
vector<int> res;
int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

void initAll(){
	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < n ; j++){
			map[i][j] = 0;
			visited[i][j] = false;
		}
	}
	maxLimit = -1;
}

void initVisit(){
	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < n ; j++){
			visited[i][j] = false;
		}
	}
}

void DFS(int i , int j, int range){
	visited[i][j] = true;
	for(int p = 0 ; p < 4; p++){
		int row = i + dr[p];
		int col = j + dc[p];
		if(row < 0 || row >= n || col < 0 || col >= n) continue;
		if(map[row][col] > range && !visited[row][col]) DFS(row, col, range);
	}
}

// 내림차순 정 
bool compare(int a, int b){
	return a > b;
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	initAll();
	
	cin >> n; 
	
	for(int i = 0 ; i < n ; i++){
		for(int j = 0 ; j < n ; j++){
			int input;
			cin >> input;
			map[i][j] = input;
			if(maxLimit < input) maxLimit = input;
		}
	}
	
	// dfs 실행 
	while(maxLimit--){
		initVisit();
		int cnt = 0;
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				if(maxLimit >= 0 && map[i][j] > maxLimit && !visited[i][j]){
					DFS(i , j, maxLimit);
					cnt++;
				}
			}
		}
		res.push_back(cnt);
	}
	
	sort(res.begin(), res.end(), compare);
	cout << res[0];
	
	return 0;
	
}
