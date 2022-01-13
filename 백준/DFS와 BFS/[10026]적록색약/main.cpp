#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 101

enum RGB{
	r,
	g,
	b
}; 

RGB R(r);
RGB G(g);
RGB B(b);

int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
int n;
int dr[4] = {0,0,-1,1};
int dc[4] = {1,-1,0,0};


using namespace std;


void DFS(int i, int j, int color){
	visited[i][j] = true;
	for(int k = 0; k < 4; k++){
		int row = i + dr[k];
		int col = j + dc[k];
		if(row <= 0 || row > n || col <= 0 || col > n) continue;
		else if(!visited[row][col] && map[row][col] == color){
			DFS(row, col, color);
		}
	}	
}


int main(){
	
	std::ios::sync_with_stdio(false);
	cin >> n;
	
	// 초기화
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= n; j++){
			map[i][j] = -1;
			visited[i][j] = false;
		}
	} 

	// 입력 
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= n; j++){
			char tmp;
			cin >> tmp;
			if(tmp == 'R') map[i][j] = R;
			else if(tmp == 'G') map[i][j] = G;
			else if (tmp == 'B') map[i][j] = B;
		}
	}

	int normal = 0;
	// map의 각 칸마다 조건 확인 후, DFS 호출
	for(int i = 1; i <= n; i++){
		for(int j = 1 ; j <= n; j++){
			if(!visited[i][j]){
				int color = map[i][j];
				DFS(i,j,color);
				normal++;
			}
		}
	} 


	// 초기화, R을 G로 바꾸기 
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= n; j++){
			visited[i][j] = false;
			if(map[i][j] == R) map[i][j]  = G;
		}
	}
	
	int wrong = 0;
	// map의 각 칸마다 조건 확인 후, DFS 호출
	for(int i = 1; i <= n; i++){
		for(int j = 1; j <= n; j++){
			if(!visited[i][j]){
				int color = map[i][j];
				DFS(i,j, color);
				wrong++;
			}
		}
	} 
	
	cout << normal << " " << wrong << endl;
		
	return 0;
}
