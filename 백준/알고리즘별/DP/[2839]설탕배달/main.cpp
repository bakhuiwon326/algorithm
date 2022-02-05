#include<iostream>

#define MAXN 5001

using namespace std;

int n, dp[MAXN];

void init(){
	for(int i = 0; i <= n; i++){
		dp[i] = 0;
	}
}

int main(){
	
	scanf("%d", &n);
	
	dp[3] = 1;
	dp[4] = -1;
	dp[5] = 1;
	dp[6] = 2;
	dp[7] = -1;
	
	for(int i = 8 ; i<= n; i++){
		dp[i] = min(dp[i-3], dp[i-5]) + 1;
		if(dp[i] == 0) dp[i] = max(dp[i-3], dp[i-5]) + 1;	
	}
	
	printf("%d", dp[n]);
		
	return 0;
}
