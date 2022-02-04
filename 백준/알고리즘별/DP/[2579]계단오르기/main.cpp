#include<iostream>

using namespace std;

int n;
int score[301], dp[301][3];

void init(){
	for(int i = 0; i <= n ; i++){
		score[i] = 0;
		dp[i][1] = 0;
		dp[i][2] = 0;
	}
}

int main(){
	
	scanf("%d", &n);
	
	init();
	
	for(int i = 1; i <= n; i++){
		scanf("%d", &score[i]);
	}
	
	if(n==1) printf("%d", score[1]);
	else{
		dp[1][1] = score[1];
	}
	
	dp[1][1] = score[1];
	dp[1][2] = 0;
	dp[2][1]= score[2];
	dp[2][2] = score[1] + score[2];
	
	for(int i = 3 ; i <= n; i++){
		dp[i][1] = max(dp[i-2][1], dp[i-2][2]) + score[i];
		dp[i][2] = dp[i-1][1] + score[i];
	}
	
	printf("%d", dp[n]);
	
}
