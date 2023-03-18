#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<cstring>

using namespace std;

int p, num, inputCm, cnt;
int minValue = 2147483647;
int maxValue = -1;
int cm[22];

int main() {

	scanf("%d", &p);

	while (p--) {
		memset(cm, 0, sizeof(cm));
		cnt = 0;
		minValue = 2147483647;
		maxValue = -1;
		scanf("%d", &num);
		for (int i = 0; i < 20; i++) scanf("%d", &cm[i]);
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < i; j++) {
				if (cm[i] < cm[j]) cnt++;
			}
		}
		printf("%d %d\n", num, cnt);
	}


	return 0;
}