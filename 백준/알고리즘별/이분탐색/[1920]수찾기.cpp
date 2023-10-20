#include <iostream>
#include <vector>
#include <unordered_map>

using namespace std;

int n, m;
unordered_map<int, bool> a;
vector<int> target;
vector<int> res;

void input() {
	int input;
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d", &input);
		a[input] = true;
	}
	scanf("%d", &m);
	for (int i = 0; i < m; i++) {
		scanf("%d", &input);
		target.push_back(input);
	}
}

void printRes() {
	for (int i = 0; i < res.size(); i++) {
		printf("%d\n", res[i]);
	}
}

int main() {

	input();

	int isExist = 0;
	for (int i = 0; i < m; i++) {
		isExist = a.find(target[i]) == a.end() ? 0 : 1;
		res.push_back(isExist);
		printf("target: %d / °á°ú: %d\n", target[i], isExist);
	}

	printRes();

	return 0;
}