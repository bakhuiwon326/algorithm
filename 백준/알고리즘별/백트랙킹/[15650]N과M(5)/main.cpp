#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;
int n, m;
int arr[9];
vector<int> vec;
bool visited[9];

void init(){
	for(int i = 0; i <= n; i++){
		arr[i] = 0;
		visited[i] = false;
	}
	vec.clear();
}

void backtracking(int v){
	if(v == m){
		for(int i = 0 ; i < m; i++) printf("%d ", arr[i]);
		printf("\n");
		return;
	}else{
		for(int i = 0 ; i < n; i++){
			if(!visited[i]){
				visited[i] = true;
				arr[v] = vec[i];
				backtracking(v+1);
				visited[i] = false;
			}
		}
	}
}

bool compare(int a, int b){
	return a < b;
}

int main(){

	scanf("%d %d", &n, &m);
	
	init();
	
	for(int i = 0; i < n; i++){
		int tmp;
		scanf("%d", &tmp);
		vec.push_back(tmp);
	}
	
	sort(vec.begin(), vec.end(), compare);
	
	backtracking(0);
	
	return 0;
}
