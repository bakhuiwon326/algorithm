#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n, m, res;
vector<int> tree;

void input() {
	scanf("%d %d", &n, &m);
	int input;
	for (int i = 0; i < n; i++) {
		scanf("%d", &input);
		tree.push_back(input);
	}
}

void init() {
	res = -1;
	tree.clear();
}

int main() {
	init();
	input();

	sort(tree.begin(), tree.end());

	int left = 0;
	int right = tree[tree.size() - 1];


	while (left <= right) {
		int mid = (left + right) / 2; // H의 최댓값으로 가정
		// H가 mid일 때 상근이가 가져갈 수 있는 나무는 몇미터인지 계산하기
		long long tmp_m = 0;
		for (int i = 0; i < n; i++) {
			if (tree[i] > mid) tmp_m += (tree[i] - mid);
		}
		if (tmp_m < m) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
			if(mid > res) res = mid;
		}

	}

	printf("%d", res);


	return 0;
}