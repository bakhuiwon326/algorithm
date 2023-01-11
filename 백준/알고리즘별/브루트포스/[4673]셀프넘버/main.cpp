#define _CRT_SECURE_NO_WARNINGS

#include<iostream>

using namespace std;

int n;
bool isSelfNumber[10101];

void init() {
	n = 0;
	for (int i = 0; i < 10101; i++) {
		isSelfNumber[i] = false;
	}
}

int main() {
	
	init();
	
	int k;

	for (int n = 1; n <= 10000; n++) {
		int one = n % 10;
		int ten = n < 10 ? 0 : (n / 10) % 10;
		int hundred = n < 100 ? 0 : (n / 100) % 10;
		int thousand = n < 1000 ? 0 : n / 1000;
		int k = n + one + ten + hundred + thousand;
		if (k <= 10000)isSelfNumber[k] = true;
	}
	
	for (int i = 1; i <= 10000;  i++) {
		if (!isSelfNumber[i]) printf("%d\n",i);
	}

	return 0;

}