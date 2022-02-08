#include<iostream>

#define MAXN 1001

using namespace std;

int n, dp[MAXN];

void init(){
	for(int i = 0; i <= n; i++){
		dp[i] = 0;
	}
}

int main(){
	
	scanf("%d", &n);
	
	init();
	
	dp[1] = 1;
	dp[2] = 2;
	
	for(int i = 3 ; i <= n ; i++){
		dp[i] = (dp[i-1] + dp[i-2]) % 10007;
	}
	
	printf("%d", dp[n]);
	
	return 0;
}
