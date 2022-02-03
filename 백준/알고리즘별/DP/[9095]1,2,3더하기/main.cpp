#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

int t, n;
int d[12];


int main(){
	
	scanf("%d", &t);
	
	while(t--){
		scanf("%d", &n);
		d[1] = 1;
		d[2] = 2;
		d[3] = 4;
		for(int i = 4; i <= n; i++) d[i] = d[i-1] + d[i-2] + d[i-3];
		printf("%d\n", d[n]);
	}
	
	return 0;
}
