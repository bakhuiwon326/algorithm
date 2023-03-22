#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<vector>
#include<cmath>

using namespace std;

int k;
int nodeCnt = 1;
int input[1024];
vector<int> tree[10];

void inorder(int depth, int start, int end) {
	if (depth == k) return;
	
	int mid = (start + end) / 2;
	tree[depth].push_back(input[mid]);
	inorder(depth + 1, start, mid-1);
	inorder(depth + 1,mid + 1, end);
}

int main() {

	scanf("%d", &k);

	int tmpK = k;
	nodeCnt = pow(2, k) - 1;

	for (int i = 0; i < nodeCnt; i++) {
		scanf("%d", &input[i]);
	}

	inorder(0, 0, nodeCnt);
	
	// Ãâ·Â
	for (int i = 0; i < k; i++) {
		for (int j = 0; j < tree[i].size(); j++) {
			printf("%d ",tree[i][j]);
		}
		printf("\n");
	}
	
	return 0;
} 
  