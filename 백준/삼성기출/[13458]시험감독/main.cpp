#include<iostream>
#include<vector>
#include<algorithm>

#define MAXN 1000001

using namespace std;

int n, b, c;
long long cnt;
int room[MAXN];

void init(){
	for(int i = 0; i < n; i++) {
		room[i] = 0;
		cnt = 0;
	}
}




int main(){
	
	scanf("%d", &n);
	init();
	for(int i = 0; i < n; i++){
		scanf("%d", &room[i]);
	}
	scanf("%d %d", &b, &c);
	for(int i = 0; i < n; i++){
		room[i] -= b;
		cnt++;
		if(room[i] > 0){
			cnt += room[i] / c;
			if(room[i] % c > 0) cnt++;
		}
	}
	
	printf("%lld", cnt);
	
	
	
	return 0;
}

