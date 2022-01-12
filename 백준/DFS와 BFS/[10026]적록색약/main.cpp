#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 101

enum RGB{
	r,
	g,
	b
};

int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
int n;
int RGBcnt[3]; // 빨강, 초록, 파랑 그룹 개수 
int dr[4] = {0,0,-1,1};
int dc[4] = {1,-1,0,0};

RGB R(r);
RGB G(g);
RGB B(b);

using namespace std;


void DFS(int i, int j, int color){
	visited[i][j] = true;
	int row, col;
	for(int k = 0; k < 4; k++){
		row = i + dr[k];
		col = j + dc[k];
		if(row < 0 || row >= n || col < 0 || col >=n) continue;
		else if(!visited[row][col] && map[row][col] == color){
			DFS(row, col, color);
		}
	}	
}


int main(){
	
	std::ios::sync_with_stdio(false);
	cin >> n;
	
	// 초기화
	for(int i = 0; i < n; i++){
		for(int j = 1; j <= n; j++){
			map[i][j] = -1;
			visited[i][j] = false;
		}
	} 
	fill_n(RGBcnt, 3, 0);

	// 입력 
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n; j++){
			char tmp;
			cin >> tmp;
			if(tmp == 'R') map[i][j] = R;
			else if (tmp == 'G') map[i][j] = G;
			else if (tmp == 'B') map[i][j] = B;
		}
	}

	// map의 각 칸마다 조건 확인 후, DFS 호출
	for(int i = 0; i < n; i++){
		for(int j = 0 ; j < n; j++){
			int color = map[i][j];
			if(!visited[i][j]){
				RGBcnt[color]++;
				DFS(i,j,color);
			}
		}
	} 
	
	// 결과 출력
	int normal = RGBcnt[0] + RGBcnt[1] + RGBcnt[2];
	int wrong = RGBcnt[0] > RGBcnt[1] ? RGBcnt[0] + RGBcnt[2] : RGBcnt[1] + RGBcnt[2];

	cout << normal << " " << wrong << endl;
		
	return 0;
}
