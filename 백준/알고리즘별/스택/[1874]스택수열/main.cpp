#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<algorithm>
#include<stack>
#include<vector>

using namespace std;

vector<int> origin;
vector<int> vec;
vector<char> res;
stack<int> stk;

int n;

int main() {
	
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		int tmp;
		scanf("%d", &tmp);
		vec.push_back(tmp);
		origin.push_back(tmp);
	}

	sort(vec.begin(), vec.end());

	int originIndex = 0;

	for (int i = 0; i < n; i++) {
		stk.push(vec[i]);
		res.push_back('+');
		
		while (!stk.empty() && stk.top() == origin[originIndex]) {
			originIndex++;
			stk.pop();
			res.push_back('-');
		}
	}

	if (!stk.empty()) {
		printf("NO");
	}
	else {
		for (int i = 0; i < res.size(); i++) {
			printf("%c\n", res[i]);
		}
	}


	return 0;
}