#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>
#include<math.h>
#include<algorithm>

using namespace std;

typedef struct {
	int i, j;
	bool visited;
} chicken;

int n, m;
vector<chicken> chickenStore;
vector<pair<int, int>> housePos;
int cityDist = 2147483647;

void calculateDist() {
	int tmpCityDist = 0;
	for (int i = 0; i < housePos.size(); i++) {
		int minDist = 2147483647;
		for (int j = 0; j < chickenStore.size(); j++) {
			if (chickenStore[j].visited) {
				int dist_r = abs(housePos[i].first - chickenStore[j].i);
				int dist_c = abs(housePos[i].second - chickenStore[j].j);
				int d = dist_r + dist_c;
				minDist = min(minDist, d);
			}
		}
		tmpCityDist += minDist;
	}
	cityDist = min(cityDist, tmpCityDist);
}

void combination(int depth, int next) {
	if (depth == m) {
		calculateDist();
		return;
	}
	else {
		for (int i = next; i < chickenStore.size(); i++) {
			if (!chickenStore[i].visited) {
				chickenStore[i].visited = true;
				combination(depth + 1, i + 1);
				chickenStore[i].visited = false;
			}
		}
	}

}

int main() {

	scanf("%d %d", &n, &m);

	int input;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			scanf("%d", &input);
			if (input == 1) housePos.push_back(make_pair(i, j));
			else if (input == 2) chickenStore.push_back({ i,j,false });
		}
	}

	combination(0, 0);
	
	printf("%d", cityDist);
	
	return 0;
}