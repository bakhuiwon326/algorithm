#include<iostream>
#include<algorithm>
#include<vector>

using namespace std;

int n, m;
int arr[10];
bool used[10];

void init(){
	for(int i = 0 ; i <= m; i++){
		arr[i] = 0;
		used[i] = false;
	}
}

void backtracking(int v, int next){
	if(v == m){
		for(int i = 0; i < m; i++) printf("%d ", arr[i]);
		printf("\n");
		return;
	}else{
		for(int i = next; i<= n; i++){
			if(!used[i]){
				used[i] = true;
				arr[v] = i;
				backtracking(v+1, i+1);
				used[i] = false;
			}
		}
	}
	
}

int main(){
	
	scanf("%d %d",&n, &m);
	
	init();
	
	backtracking(0,1);
	
	return 0;
}
