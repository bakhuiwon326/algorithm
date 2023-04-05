#include <iostream>
#include<queue>
#include<vector>
#include<algorithm>
#include<cstring>
#include<memory.h>

#define MAXN 16

using namespace std;

int t, n, m, maxValue;
int priority[10];
queue<pair<int, int>> q;
vector <pair<int, int>> vec; // (처음 인덱스, 우선순위)

void init() {
	memset(priority, 0, sizeof(priority));
	maxValue = -1;
	vec.clear();
	queue<pair<int, int>> nq;
	q = nq;
}

bool compare(pair<int ,int> a, pair<int ,int> b) {
	if (a.second > b.second) return true;
	else return false;
}

int main() {

	scanf("%d", &t);

	while (t--) {
		init();
		scanf("%d %d", &n, &m);
		for (int i = 0; i < n; i++) {
			int tmp;
			scanf("%d", &tmp);
			priority[tmp]++;
			maxValue = tmp > maxValue ? tmp : maxValue;
			q.push(make_pair(i, tmp));;
		}

		for (int i = maxValue; i > 0; i--) {
			for (int j = priority[i]; j > 0; j--) {
				while (true) {
					if (q.front().second == i) {
						vec.push_back(q.front());
						q.pop();
						break;
					}
					else {
						q.push(q.front());
						q.pop();
					}
				}
			}
		}


		for (int i = 0; i < n; i++) {
			if (m == vec[i].first) {
				printf("%d\n", i + 1);
				break;
			}
		}
	
	}


	return 0;
}