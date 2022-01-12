#include<iostream>
#include<vector>
#include<algorithm>
#include<string>

#define MAXN 26

using namespace std;

int n;
int map[MAXN][MAXN];
vector<int> result;
bool visited[MAXN][MAXN];

int dc[4] = { 0,0,-1,1 }; // 열 
int dr[4] = { 1,-1,0,0 }; // 행 

int cnt;
int groupCnt;

bool compare(int a, int b) {
	return a < b;
}

void DFS(int i, int j) {
	visited[i][j] = true;
	cnt++;
	int row, col;
	for (int k = 0; k < 4; k++) {
		col = j + dc[k];
		row = i + dr[k];
		if (row < 1 || row > n || col < 1 || col > n) continue;
		if (map[row][col] == 1 && !visited[row][col]) {
			DFS(row, col);
		}
	}
}


int main() {

	std::ios::sync_with_stdio(false);

	cin >> n;

	// 초기화
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			visited[i][j] = false;
			map[i][j] = 0;
		}
	}
	groupCnt = 0;
	cnt = 0;

	// 입력
	for (int i = 1; i <= n; i++) {
		string str;
		cin >> str;
		for (int j = 0; j < n; j++) {
			map[i][j+1] = str[j] - '0';
		}
	}
	
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (map[i][j] == 1 && !visited[i][j]) {
				groupCnt++;
				DFS(i, j);
				result.push_back(cnt);
				cnt = 0;
			}
		}
	}

	sort(result.begin(), result.end(), compare);


	cout << groupCnt << endl;
	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << endl;
	}

}

