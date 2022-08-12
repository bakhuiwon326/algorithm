#include<iostream>
#include<vector>

#define MAXN 21

using namespace std;

int n;
int minVal = 987654321;
int s[MAXN][MAXN];
bool visited[MAXN];
vector<int> start;
vector<int> link;

void backtracking(int depth, int current) {
	if (depth == (n / 2)) {
		for (int i = 0; i < n; i++) {
			if (visited[i]) { // link 评 家加
				link.push_back(i);
			}
			else { // start 评 家加
				start.push_back(i);
			}
		}

		int startSum = 0;
		int linkSum = 0;
		for (int i = 0; i < (n / 2); i++) {
			for (int j = 0; j < (n / 2); j++) {
				startSum += s[start[i]][start[j]];
				linkSum += s[link[i]][link[j]];
			}
		}
		
		if (minVal > abs(startSum - linkSum)) minVal = abs(startSum - linkSum);

		start.clear();
		link.clear();

		return;

	}
	for (int i = current; i < n; i++) {
		if (!visited[i]) {
			visited[i] = true;
			backtracking(depth + 1, i);
			visited[i] = false;
		}
	}

}

void init() {
	start.clear();
	link.clear();
	minVal = 987654321;
	n = 0;
	for (int i = 0; i < MAXN; i++) {
		visited[i] = false;
		start[i] = 0;
		link[i] = 0;
		for (int j = 0; j < MAXN; j++) {
			s[i][j] = 0;
		}
	}
}


int main() {

	//init();

	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			scanf("%d", &s[i][j]);
		}
	}

	backtracking(0, 0);

	printf("%d\n", minVal);

	return 0;
}