#include<iostream>
#include<vector>

using namespace std;

int n, cnt;
int arr[3];


int main(){
	
	scanf("%d", &n);
	
	if(n < 100){
			cnt = n;
	}else {
		cnt = 99;
		for(int i = 100 ; i <= n; i++){
			int hundred = i / 100;
			int ten = (i % 100)/10;
			int one = (i % 100)%10;
			if((hundred-ten) == (ten-one)) cnt++;
		}
	}
	
	cout << cnt;
	 
	return 0;
}
