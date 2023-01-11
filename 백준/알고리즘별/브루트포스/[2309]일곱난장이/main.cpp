#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int sum;
vector<int> result;

void init() {
	sum = 0;
	result.clear();
}

int compare(int a, int b) {
	return a < b;
}


int main() {
	
	init();

	for (int i = 0; i < 9; i++) {
		int tmp = 0;
		scanf("%d", &tmp);
		result.push_back(tmp);
		sum += result[i];
	}
	
	sort(result.begin(), result.end());

	for (int i = 0; i < 8; i++) {
		for (int j = i + 1; j < 9; j++) {
			if ((sum - (result[i] + result[j])) == 100) {
				for (int k = 0; k < 9; k++) {
					if (k == j || k == i) continue;
					printf("%d\n", result[k]);
				}
				return 0;
			}
		}
	}


	return 0;

}