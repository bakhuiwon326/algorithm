#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int t[16], p[16];
int n;
int start, next, pay;

void init(){
	start = 0;
	next = 0;
	pay = 0;
	for(int i = 1; i <= n; i++){
		t[i] = 0;
		p[i] = 0;
	}
}

int main(){
	
	scanf("%d", &n);
	printf("n: %d \n", n);
	
	init();
	
	for(int i = 1; i <= n; i++){
		scanf("%d %d", &t[i], &p[i]);
	}
	
	start = 1;
	next = start + t[start];
	pay += p[start];
	
	while(start <= n && next <= n){
		start = next;
		next = start + t[start];
		pay += p[start];
		printf("start: %d next: %d pay: %d\n", start, next, p[start]);
	}
	printf("%d", pay);
	
	return 0;
}
