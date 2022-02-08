#include<iostream>

#define MAXN 9

using namespace std;

int n, m;
int arr[MAXN];

void init(){
	for(int i = 0; i <= n; i++){
		arr[i] = 0;
	}

}

void backtracking(int v, int next){
	if(v == m){
		for(int i = 0; i < m; i++) printf("%d ", arr[i]);
		printf("\n");
		return;
	}else{
		for(int i = next; i <= n; i++){
			arr[v] = i;
			backtracking(v+1, i);
		}
	}
}

int main(){
	
	scanf("%d %d", &n, &m);
	
	init();
	
	backtracking(0,1);
	
	return 0;
}
