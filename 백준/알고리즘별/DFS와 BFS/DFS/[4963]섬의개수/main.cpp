#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

#define MAX 51

using namespace std;

int w, h, cnt;
vector<int> result;
int map[MAX][MAX];
bool visited[MAX][MAX];
int dr[8] = {0,0,-1,1,1,1,-1,-1};
int dc[8] = {1,-1,0,0,1,-1,1,-1};

void DFS(int i, int j){
	visited[i][j] = true;
	for(int k = 0; k < 8; k++){
		int row = i + dr[k];
		int col = j + dc[k];
		if(!visited[row][col] && map[row][col] == 1){
			DFS(row, col);
		}
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	while(true){
		// 초기화
		for(int i = 0; i <= h; i++){
			for(int j = 0; j <= w; j++){
				map[i][j] = -1;
				visited[i][j] = false;
			}
		} 
		cnt = 0;
		
		cin >> w >> h;
		if(w == 0 && h ==0) break;
		
		// 섬, 바다 입력 
		for(int i = 1; i <= h; i++){
			for(int j = 1; j <= w; j++){
				cin >> map[i][j];
			}
		}
		
		for(int i = 1; i <= h; i++){
			for(int j = 1; j <= w; j++){
				if(!visited[i][j] && map[i][j] == 1){
					DFS(i, j);
					cnt++;
				} 
			}
		}
		
		result.push_back(cnt);
		 
	}
	
	
	for(int i = 0; i < result.size(); i++){
		cout << result[i] << endl;
	}
	
	
	return 0;
}
