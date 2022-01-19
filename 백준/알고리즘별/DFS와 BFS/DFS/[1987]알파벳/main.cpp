#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 21

using namespace std;

int r,c,cnt;
char map[MAXN][MAXN];
bool visited[MAXN][MAXN];
bool alphabet[26];
int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

void init(){
	for(int i = 0 ; i < r; i++){
		for(int j = 0 ; j < c ; j++){
			map[i][j] = 'A';
			visited[i][j] = false;
		}
	}	
	for(int i = 0; i < 26; i++) alphabet[i] = false;
	cnt = 0;
}

void DFS(int i, int j){
	for(int p = 0; p < 4; p++){
		int row = i + dr[p];
		int col = j + dc[p];
		if(row < 0 || row >= r || col < 0 || col >= c) continue;
		int index = map[row][col] - 'A';
		if(!alphabet[index] && !visited[row][col]){
			visited[row][col] = true;
			alphabet[index] = true;
			cnt++;
			DFS(row, col);
		}
		
	}
	
}


int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> r >> c;
	
	for(int i = 0 ; i < r; i++){
		for(int j = 0 ; j < c ; j++){
			cin >> map[i][j];
		}
	}
	
	visited[0][0] = true;
	int index = map[0][0] - 'A';
	alphabet[index] = true;
	cnt++;
	DFS(0,0);
	
    int cnt = 0;
	for(int i = 0 ; i < 26; i++){
		if(alphabet[i]) cnt++;
	}
    
	cout << cnt;	
    
	return 0;
}
