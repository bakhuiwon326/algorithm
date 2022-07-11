#include<iostream>
#include<algorithm>
#include<vector>

#define MAXR 21

using namespace std;

int r, c, cnt;
int maxCnt = -1;
char map[MAXR][MAXR];
int passedAlphabet[26];
int dr[4] = { 0,0,-1,1 };
int dc[4] = { 1,-1,0,0 };
vector<char> path;

void DFS(int row, int col, int count) {

	char alphabet = map[row][col];
	if (passedAlphabet[alphabet - 'A'] >= 1) return;
	else passedAlphabet[alphabet - 'A']++;
	count++;

	if (maxCnt < count) maxCnt = count;

	for (int i = 0; i < 4; i++) {
		int x = row + dr[i];
		int y = col + dc[i];
		if (x >= 1 && x <= r && y >= 1 && y <= c) {
			if (passedAlphabet[map[x][y] - 'A'] == 0) {
				DFS(x, y, count);
				passedAlphabet[map[x][y] - 'A'] = 0;
			}
		}
	}
}

int main() {
	
	scanf("%d %d", &r, &c);

	for (int i = 1; i <= r; i++) {
		for (int j = 1; j <= c; j++) {
			scanf("%1s", &map[i][j]);
		}
	}

	DFS(1, 1, 0);

	printf("%d", maxCnt);

	return 0;
}

