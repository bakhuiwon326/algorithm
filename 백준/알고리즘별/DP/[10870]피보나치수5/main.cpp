#include<iostream>

#define MAXN 21

using namespace std;

int n;
int dp[MAXN];

int fibonacci(int n) {
	dp[0] = 0;
	dp[1] = 1;
	for(int i = 2; i <= n; i++){
		dp[i] = dp[i-1] + dp[i-2];
	}
	return dp[n];
}

void init(){
	for(int i = 0; i <= n ; i++ ){
		dp[i] = 0;
	}
}

int main(){
	
	scanf("%d", &n);
	
	init();
	
	printf("%d", fibonacci(n));
	
	return 0;
}
