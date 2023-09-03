#include <iostream>
#include <memory.h>
#include <vector>

using namespace std;

int n, q;
int dr[4] = { 0,1,0,-1 };
int dc[4] = { 1,0,-1,0 };
int map[65][65];
vector<int> level;
bool visited[65][65];
int tmp[65][65];

void init() {
	level.clear();
	memset(map, 0, sizeof(map));
	memset(visited, false, sizeof(visited));
}

void input() {
	scanf("%d %d", &n, &q);
	n = 1 << n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &map[i][j]);
		}
	}
	for (int i = 0; i < q; i++) {
		int input;
		scanf("%d", &input);
		level.push_back(input);
	}
}

void copyMap(int (*origin)[65], int (*tmp)[65]) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			tmp[i][j] = origin[i][j];
		}
	}
}

void printMap(string title) {
	printf("%s\n", title.c_str());
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%3d ", map[i][j]);
		}
		printf("\n");
	}
}

void moveMini(int mini_n, int leftTop_r, int leftTop_c, int dir) {
	printf("mini_n = %d / dir = %d\n", mini_n, dir);
	printf("miniÀÇ ¿ÞÂÊ Å¾ = (%d, %d)\n", leftTop_r, leftTop_c);
	printf("nr, nc = ");
	for (int i = leftTop_r; i < leftTop_r + mini_n; i++) {
		for (int j = leftTop_c; j < leftTop_c + mini_n; j++) {
			int nr = i + dr[dir] * mini_n;
			int nc = j + dc[dir] * mini_n;
			printf("(%d, %d) ", nr, nc);
			map[nr][nc] = tmp[i][j];
		}
	}
	printf("\n");
}

void rotateMini(int partial_n) {
	int mini_n = partial_n / 2;
	//printf("partial_n: %d / mini_n: %d \n", partial_n, mini_n);
	// 	(i, j)´Â partialÀÇ ¿ÞÂÊÅ¾
	memset(tmp, 0, sizeof(tmp));
	copyMap(map, tmp);
	int d = 0;
	for (int i = 0; i < n; i += partial_n) {
		for (int j = 0; j < n; j += partial_n) {
			// 4µîºÐÀÌ´Ï±ñ
			printf("ÆÄ¼È ¿ÞÂÊÅ½ (%d, %d)\n", i, j);
			moveMini(mini_n, i, j, 0);
			moveMini(mini_n, i, j + mini_n, 1);
			moveMini(mini_n, i + mini_n, j + mini_n, 2);
			moveMini(mini_n, i + mini_n, j, 3);
		}
	}

}

bool isRange(int r, int c) {
	return r >= 0 && r < n && c >= 0 && c < n;
}

void melting() {
	bool isMelting[65][65];
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 0) continue;
			int cnt = 0;
			for (int k = 0; k < 4; k++) {
				int nr = i + dr[k];
				int nc = j + dc[k];
				if (isRange(nr, nc) && map[nr][nc] > 0) cnt++;
			}
			if (cnt < 3) isMelting[i][j] = true;
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (isMelting[i][j]) map[i][j]--;
		}
	}
}

int dfs(int r, int c, int cnt) {
	visited[r][c] = true;
	for (int k = 0; k < 4; k++) {
		int nr = r + dr[k];
		int nc = c + dc[k];
		if (isRange(nr, nc) && map[nr][nc] > 0) cnt = dfs(nr, nc, cnt + 1);
	}
	return cnt;
}

int getMaxCnt() {
	int maxCnt = -1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (visited[i][j]) continue;
			maxCnt = max(dfs(i, j, 1), maxCnt);
		}
	}
	return maxCnt;
}

int getSum() {
	int sum = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			sum += map[i][j];
		}
	}
	printf("sum: %d \n", sum);
	return sum;
}

int main() {
	init();
	input();
	for (int i = 0; i < q; i++) {
		//printf("level: %d / shift: %d\n", level[i], 1 << level[i]);
		rotateMini(1 << level[i]);
		printMap("È¸Àü Á¾·á");
		melting();
	}
	printf("%d \n", getSum());
	//printf("%d %d", getSum(), getMaxCnt());
	
}