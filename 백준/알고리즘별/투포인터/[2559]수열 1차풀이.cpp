#include <iostream>
#include <vector>

using namespace std;

int n, k;
vector<int> temperature;

int main() {

	cin >> n >> k;
	int input;
	for (int i = 0; i < n; i++) {
		cin >> input;
		temperature.push_back(input);
	}

	int partialSum = 0; // ºÎºÐ ÇÕ
	int maxRes = -2147483647;
	int start = 0;
	for (int i = 0; i < n; i++) {
		partialSum += temperature[i];
		if (i >= k - 1) {
			maxRes = max(maxRes, partialSum);
			partialSum -= temperature[start++];
		}
	}

	cout << maxRes;


	return 0;
}