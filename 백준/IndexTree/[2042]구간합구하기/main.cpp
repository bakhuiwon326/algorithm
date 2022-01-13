#include<iostream>
#include<vector>
#include<queue>
#include<algorithm>

int n,m,k,B;
long long indexTree[1<<20];

using namespace std;

// tree 전체를 초기화 
void initTree(){
	for(int i = B - 1 ; i >= 1; i--){
		indexTree[i] = indexTree[2*i] + indexTree[2*i + 1];
	}
}

void update(int b, long long c){
	int parent = B + b - 1;
	indexTree[parent] = c;
	// 바뀐 노드의 부모 노드만 값을 업데이트 
	while(parent>>=1){
		indexTree[parent] = indexTree[parent * 2] + indexTree[parent * 2+ 1];
	}
} 

long long getSum(int b, int c){
	long long sum = 0;
	int left = B + b - 1;
	int right = B + c - 1;
	
	while(left <= right){
		if(left & 1){
			sum += indexTree[left];
		}
		left = (left + 1) / 2;
		
		if(!(right & 1)){
			sum += indexTree[right];	
		}
		right = (right - 1) / 2;
	}
	
	return sum;	
}

int main(){
	
	std::ios::sync_with_stdio(false);
	
	cin >> n >> m >> k;
	
	for(B = 1; B < n ; B<<=1);
	for(int i = B; i < n + B; i++){
		cin >> indexTree[i];
	}
	
	// tree 초기화
	initTree(); 
	
	for(int i = 0 ; i < m + k ; i++ ){
		int a,b;
		long long c;
		cin >> a >> b >> c;
		if(a == 1){
			update(b,c);
		}else if (a == 2){
			cout << getSum(b,c) << endl;
		}
	}
	 
	return 0;
}
