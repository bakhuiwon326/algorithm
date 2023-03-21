#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<cstring>

using namespace std;

int t, n, teamCnt, winner;
int input[1001];
int cnt[201];
int minScore = 2147483647;
pair<int, pair<int, int>> arr[201]; // {명수, 총점수, 5번째선수 점수}

void init() {
	teamCnt = -1;
	winner = 0;
	memset(input, 0, sizeof(input));
	memset(cnt, 0, sizeof(cnt));
	minScore = 2147483647;
	for (int i = 0; i < 201; i++) arr[i] = make_pair(0, make_pair(0, 0));
}

int main() {

	scanf("%d", &t);

	while (t--) {
		init();

		scanf("%d", &n);
	
		// 입력
		for (int i = 0; i < n; i++) {
			int tmp = -1;
			scanf("%d", &tmp);
			input[i] = tmp;
			cnt[input[i]]++;
			teamCnt = teamCnt < input[i] ? input[i] : teamCnt;
		}

		int rank = 0;
		for (int i = 0; i < n; i++) {
			int num = input[i];
			
			if (cnt[num] == 6) {
				arr[num].first++;
				rank++;
				if(arr[num].first <= 4)	arr[num].second.first += (rank);
				if (arr[num].first == 5) arr[num].second.second = rank;
			}
		}
		
		for (int i = 1; i <= teamCnt; i++) {
			if (arr[i].first > 0) {
				if (arr[i].second.first < minScore) {
					minScore = arr[i].second.first;
					winner = i;
				}
				else if (arr[i].second.first == minScore) {
					winner = arr[i].second.second < arr[winner].second.second ? i : winner;
				}
			}
		}
		
		printf("%d\n", winner);
	}
	

	return 0;
}
