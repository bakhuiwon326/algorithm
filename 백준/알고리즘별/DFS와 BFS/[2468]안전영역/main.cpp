#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 101

using namespace std;

int n;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
vector<int> limit;
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
	limit.clear();
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
		if(map[row][col] - range > 0 && !visited[row][col]) DFS(row, col, range);
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
			limit.push_back(input);
		}
	}
	
	// 중복제거 
	sort(limit.begin(), limit.end());
	limit.erase(unique(limit.begin(), limit.end()), limit.end());
	
	// dfs 실행 
	for(int t = 0 ; t < limit.size(); t++){
		initVisit();
		int cnt = 0;
		for(int i = 0 ; i < n ; i++){
			for(int j = 0 ; j < n ; j++){
				if(map[i][j] - limit[t] > 0 && !visited[i][j]){
					DFS(i , j, limit[t]);
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
