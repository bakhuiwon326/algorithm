#include<iostream>

using namespace std;

int n, m;
int arr[10];
bool used[10];

void func(int k){
	if(k==m){
		for(int i = 0; i < m; i++) printf("%d ", arr[i]);
		printf("\n");
		return;
	}
	else{
		for(int i = 1; i <= n; i++){
			if(!used[i]){
				arr[k] = i;
				used[i] = 1;
				func(k+1);
				used[i] = 0;
			}
		}
	}
}


int main(){
	
	scanf("%d %d", &n, &m);
	
	func(0);

	return 0;
}
