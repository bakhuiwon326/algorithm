#include<iostream>
#include<memory.h>

using namespace std;

int tobni[5][8];
int k, num, dir, score;
int dc[2] = { 1, -1 };
bool visited[5];
int rotateInfo[5];

void init() {
	score = 0;
	memset(tobni, 0, sizeof(tobni));
	memset(rotateInfo, 0, sizeof(rotateInfo));
	memset(visited, false, sizeof(visited));
}

void copy(int* origin, int* tmp) {
	for (int i = 0; i < 8; i++) {
		tmp[i] = origin[i];
	}
}

bool isRange(int idx) {
	return 1 <= idx && idx <= 4;
}

void dfs(int num, int dir) {
	for (int i = 0; i < 2; i++) {
		int next = num + dc[i];
		if (isRange(next) && !visited[next]) {
			visited[num] = true;
			if (dc[i] == 1) {
				if (tobni[num][2] == tobni[next][6]) {
					rotateInfo[next] = 0;
				}
				else {
					rotateInfo[next] = rotateInfo[num] * -1;
				}
			}
			else {
				if (tobni[num][6] == tobni[next][2]) {
					rotateInfo[next] = 0;
				}
				else {
					rotateInfo[next] = rotateInfo[num] * -1;
				}
			}
			dfs(next, rotateInfo[next]);
		}
	}
}

void rotate(int idx) {
	int tmp[8];
	copy(tobni[idx], tmp);
	for (int i = 0; i < 8; i++) {
		tobni[idx][(i - 1 + 8) % 8] = tmp[i];
	}
}

void rotate_clock(int idx) {
	int tmp[8];
	copy(tobni[idx], tmp);
	for (int i = 0; i < 8; i++) {
		tobni[idx][(i + 1) % 8] = tmp[i];
	}
}

int main() {

	init();

	// 톱니 정보 입력
	for (int i = 1; i <= 4; i++) {
		for (int j = 0; j < 8; j++) {
			scanf("%1d", &tobni[i][j]);
		}
	}

	// k입력
	scanf("%d", &k);

	// 회전 정보
	for (int i = 0; i < k; i++) {
		// num: 돌릴 톱니바퀴 / dir: 회전 방향
		scanf("%d %d", &num, &dir);
		// dfs로 각 톱니바퀴 회전방향 정하기
		memset(visited, false, sizeof(visited));
		rotateInfo[num] = dir;
		visited[num] = true;
		dfs(num, dir);
		// 회전
		for (int j = 1; j <= 4; j++) {
			int state = rotateInfo[j];
			if (state == 0) { // 정지
				continue;
			}
			else if (state == -1) { // 반시계
				rotate(j);
			}
			else if (state == 1) { // 시계
				rotate_clock(j);
			}
		}
	}

	// 회전이 모두 끝나서 점수 매김
	score = tobni[1][0] == 0 ? score : score + 1;
	score = tobni[2][0] == 0 ? score : score + 2;
	score = tobni[3][0] == 0 ? score : score + 4;
	score = tobni[4][0] == 0 ? score : score + 8;

	printf("%d", score);

	return 0;

}
