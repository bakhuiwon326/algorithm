#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

#define MAXN 26

using namespace std;

int n, cnt, danji;
int map[MAXN][MAXN];
bool visited[MAXN][MAXN];
queue<pair<int,int> > q;
vector<int> res;
int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

void BFS(){
	while(!q.empty()){
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for(int i = 0; i < 4; i++){
			int row = x + dr[i];
			int col = y + dc[i];
			if(row < 0 || row >= n || col < 0 || col >= n) continue;
			if(map[row][col] == 1 && !visited[row][col]){
				q.push(make_pair(row, col));
				visited[row][col] = true;
				cnt++;
			}
		}	
	}
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> n;
	
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n ; j++){
			char tmp;
			cin >> tmp;
			map[i][j] = tmp - '0';
		}
	}
	
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n ; j++){
			if(map[i][j] == 1 && !visited[i][j]){
				q.push(make_pair(i,j)); // 시작점. 
				visited[i][j] = true;
				cnt++;
				danji++;
				BFS();
				res.push_back(cnt);
				cnt = 0;
			}
		}
	}
	
	sort(res.begin(), res.end());
	
	cout << danji << endl;
	for(int i = 0; i < res.size() ; i++){
		cout << res[i] << endl;
	}
	
	return 0;
}
