#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int n, sum = 0, constructor; 

int main(){
	
	constructor = -1; 
	
	 scanf("%d", &n);

	 for(int i = 1 ; i <= n; i++){
	 	int tmp = i;
	 	sum = i;
	 	while(tmp){
	 		sum += tmp % 10;
	 		tmp /= 10;
		}
		if(sum == n){
			constructor = i;
			break;
		}
	 }
	 
	 if(constructor == -1) printf("0");
	 else printf("%d", constructor);
	 
	return 0;
	
}
