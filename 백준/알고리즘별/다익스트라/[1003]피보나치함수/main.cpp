#include<iostream>

#define MAXN 21

using namespace std;

int t, n;
int dp[MAXN][MAXN];  

void init(){
	for(int i = 0; i <= n ; i++ ){
		dp[i][0] = 0;
		dp[i][1] = 0;
	}
}

int main(){
	
	scanf("%d", &t);
	
	while(t--){
		scanf("%d", &n);
		
		init();
		
		dp[0][0] = 1;
		dp[0][1] = 0;
		dp[1][0] = 0;
		dp[1][1] = 1;
		
		for(int i = 2 ;i <= n; i++){
			dp[i][0] = dp[i-1][0] + dp[i-2][0];
			dp[i][1] = dp[i-1][1] + dp[i-2][1];
		}
		
		printf("%d %d\n", dp[n][0], dp[n][1]);	
	}
	
	return 0;
}
