#include<iostream>

using namespace std;

int n, m;
int arr[9];

// v는 원소를 저장할 arr의 인덱스 위치이다.
void backtracking(int v){
	if(v == m){
		for(int i = 0 ; i < m; i++) printf("%d ", arr[i]);
		printf("\n");
	}else{
		for(int i = 1 ; i <= n; i++){
			arr[v] = i;
			backtracking(v+1);
		}
	}
}

int main(){
	
	scanf("%d %d", &n, &m);
	
	backtracking(0);
	
	return 0;
}

