#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

typedef struct road {
	int start, end, len;
}road;

int n, d;
road shortWay[13];
vector<int> res;

void dfs(int nowKm, int weight) { // km¿Ã depth ∞≥≥‰¿”
	if (nowKm == d) {
		res.push_back(weight);
	}
	else {
		for (int i = 0; i < n; i++) {
			int start = shortWay[i].start;
			int end = shortWay[i].end;
			int len = shortWay[i].len;
			if (nowKm <= start && end <= d) {
				int diff = start - nowKm;
				dfs(end, weight + diff + len);
			}
		}
		if (nowKm < d) dfs(d, weight + d - nowKm);
	}
}

int main() {

	scanf("%d %d", &n, &d);

	for (int i = 0; i < n; i++) {
		int s, e, l;
		scanf("%d %d %d", &s, &e, &l);
		shortWay[i].start = s;
		shortWay[i].end = e;
		shortWay[i].len = l;
	}

	dfs(0, 0);

	sort(res.begin(), res.end());

	printf("%d", res[0]);

	return 0;
} 
 