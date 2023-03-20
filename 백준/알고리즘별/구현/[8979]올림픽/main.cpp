#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<cstring>
#include<algorithm>

using namespace std;

typedef struct countryStruct{
	int number, gold, silver, bronze;
	bool isSameRank = false;
} countryStruct;

int n, k, res, sameRank;
countryStruct arr[501];

void init() {
	n, k, sameRank = 0;
	res = 0;
	for (int i = 0; i < 501; i++) {
		arr[i] = { 0,0,0,0, false };
	}
}

bool compare(countryStruct a, countryStruct b) {
	if (a.gold > b.gold) return true;
	else if (a.gold == b.gold && a.silver > b.silver) return true;
	else if (a.silver == b.silver && a.bronze > a.bronze) return true;
	else return false;
}

int main() {

	init();

	scanf("%d %d", &n, &k);

	for (int i = 0; i < n; i++) {
		scanf("%d %d %d %d", &arr[i].number, &arr[i].gold, &arr[i].silver, &arr[i].bronze);
;	}

	sort(arr, arr + n, compare);


	for (int i = 1 ; i < n ; i++) {
		if (arr[i - 1].gold == arr[i].gold
			&& arr[i - 1].silver == arr[i].silver
			&& arr[i - 1].bronze == arr[i].bronze) {
			arr[i - 1].isSameRank = true;
			arr[i].isSameRank = true;
		}
	}

	int sameRank = -1;

	for (int i = k - 1; i >= 0; i--) {
		if (arr[i].isSameRank) {
			sameRank++;
			res++;
		}
		else {
			res++;
		}
	}

	printf("%d", res - sameRank);


	return 0;
}