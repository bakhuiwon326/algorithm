#include<iostream>
#include<vector>

#define MAXN 101

int input[MAXN];
int n, m, max = -1;

int main(){
	
	scanf("%d %d", &n, &m);
	
	for(int i= 0 ; i < n ; i++){
		scanf("%d", &input[i]);
	}
	
	for(int i = 0; i < n; i++){
		for(int j = 0; j < n;j++){
			if(j == i) continue;
			for(int k = 0; k < n; k++){
				if(k == j || k ==i) continue;
				int sum = input[i] + input[j] + input[k];
				if(max < sum && sum <= m ) max = sum;
			}
		}
	}
	
	printf("%d", max);
	return 0;
}
