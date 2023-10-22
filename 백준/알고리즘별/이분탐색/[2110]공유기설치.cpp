#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n, c;
vector<int> home;

void init() {
	home.clear();
}

void input() {
	scanf("%d %d", &n, &c);
	int input;
	for (int i = 0; i < n; i++) {
		scanf("%d", &input);
		home.push_back(input);
	}
}

int main() {
	
	init();
	input();

	sort(home.begin(), home.end());

	int res = -1;
	int left = 0;
	int right = home[n - 1] - home[0];

	while (left <= right) {
		int mid = (left + right) / 2;
		int tmp_c = 1; // 첫 집에 무조건 공유기 설치되어있다.
		int last_index = 0;
		for (int i = 1; i < n; i++) {
			// mid와 같아지거나 초과하는 부분이 공유기를 설치하게 될 위치다
			if (home[i] - home[last_index] >= mid) {
				last_index = i;
				tmp_c++;
			}
		}
		if (tmp_c < c) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
			res = max(res, mid);
		}
	}

	printf("%lld", res);

	return 0;
}