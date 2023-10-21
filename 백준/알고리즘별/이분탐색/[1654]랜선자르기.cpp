#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int k, n;
vector<int> vec;


void init() {
	vec.clear();
}

void input() {
	scanf("%d %d", &k, &n);
	long long input;
	for (int i = 0; i < k; i++) {
		scanf("%lld", &input);
		vec.push_back(input);
	}
}

int main() {
	
	init();
	input();

	sort(vec.begin(), vec.end());

	long long res = -1;

	long long left = 1;
	long long right = vec[vec.size() - 1];

	while (left <= right) {
		long long mid = (left + right) / 2; // 자르는 랜선의 최대 길이라 가정
		// mid 길이로 잘랐을 때, 랜선 개수 세기
		long long cnt = 0;
		for (int i = 0; i < vec.size(); i++) {
			cnt += (vec[i] / mid);
		}

		if (cnt < n) {
			right = mid - 1;
		}
		else {
			left = mid + 1;
			res = max(mid, res);
		}
	}

	printf("%lld", res);

	return 0;
}