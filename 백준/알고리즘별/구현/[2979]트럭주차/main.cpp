#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>

using namespace std;

int a, b, c, res;
int timeArr[105] = {0,};

int main() {
	
	scanf("%d %d %d", &a, &b, &c);

	for (int i = 0; i < 3; i++) {
		int arrive, leave;
		scanf("%d %d", &arrive, &leave);
		for (int j = arrive; j < leave; j++) {
			timeArr[j]++;
		}
	}

	for (int i = 1; i < 100; i++) {
		if (timeArr[i] == 1) {
			res += a;
		}
		else if (timeArr[i] == 2) {
			res += b * 2;
		}
		else if (timeArr[i] == 3) {
			res += c * 3;
		}
	}

	printf("%d", res);
	
	return 0;
}