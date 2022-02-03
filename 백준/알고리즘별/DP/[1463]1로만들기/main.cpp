#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

int n;
int d[1000001];


int main(){
	
	scanf("%d", &n);
	
	d[1] = 0;
	
	for(int i = 2 ; i <= n; i++){
		d[i] = d[i-1] + 1;
		if(i % 3 == 0) d[i] = min(d[i], d[i/3] + 1);
		if( i % 2 == 0) d[i] = min(d[i], d[i/2] + 1);
	}
	
	printf("%d", d[n]);
	
	return 0;
}
