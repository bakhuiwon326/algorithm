#include <iostream>
#include<vector> 
#include<cstring>

using namespace std;

int n, res;
int num = 1;
int maxBlock = -1;
int origin_map[21][21];
int map[21][21];
int dr[4] = { -1,1,0,0 };
int dc[4] = { 0,0,-1,1 };
vector<int> seq;

void init() {
	res = 0;
	num = 1;
	maxBlock = -1;
	memset(origin_map, 0, sizeof(origin_map));
	memset(map, 0, sizeof(map));
}

void copyMap(int(*origin)[21], int(*tmp)[21]) {
	for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) tmp[i][j] = origin[i][j];
}

void MaxValue() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] != 0) maxBlock = max(map[i][j], maxBlock);
		}
	}
}
void rotate() {
	int tmp[21][21];
	for (int i = 0; i < 21; i++) for (int j = 0; j < n; j++) tmp[i][j] = map[i][j];
	for (int i = 0; i < 21; i++) {
		for (int j = 0; j < n; j++) {
			map[i][j] = tmp[n - 1 - j][i];
		}
	}
}

void up() {
	int tmp[21][21]; // 이동 결과
	memset(tmp, 0, sizeof(tmp));
	for (int col = 0; col < n; col++) {
		int target = -1;
		bool canMerge = true;
		for (int row = 0; row < n; row++) {
			if (map[row][col] == 0) continue;
			if (canMerge && tmp[target][col] == map[row][col]) {
				tmp[target][col] += map[row][col];
				canMerge = false;
			}
			else {
				target++;
				canMerge = true;
				tmp[target][col] = map[row][col];
			}
		}
	}
	copyMap(tmp, map);
}

void play() {

	copyMap(origin_map, map);
	// seq 경우의 수에 맞게 5번 움직여라

	for (int i = 0; i < 5; i++) {
		int dir = seq[i];
		// 상: 위쪽에서 연속으로 같은 값의 두개의 블록들이 합쳐진다.
		if (dir == 0) // 그대로
		{
			up();
		}
		else if (dir == 1) // 회전 두번
		{
			rotate();
			rotate();
			up();
		}
		else if (dir == 2)  // 회전 세번
		{
			rotate();
			rotate();
			rotate();
			up();
		}
		else if (dir == 3)  // 회전 한번
		{
			rotate();
			up();
		}

	}

}

int main() {

	init();

	scanf("%d", &n);

	for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) scanf("%d", &origin_map[i][j]);

	// 4^5
	for (int i = 0; i < 5; i++) num *= 4;

	// 경우의 수 구하기 : 상하좌우의 중복 순열을 구한다.
	for (int i = 1; i < num; i++) {

		int tmp = i;
		seq.clear(); // 0 상 / 1 하 / 2 좌 / 3 우
		// 4진수 생성!
		for (int j = 0; j < 5; j++) {
			seq.push_back(tmp % 4);
			tmp /= 4;
		}
		// seq에 담긴 경우대로 게임 플레이 진행
		play();

		// 최댓값 찾기
		MaxValue();
	}

	printf("%d", maxBlock);

	return 0;

}