#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<string>
#include<stack>

using namespace std;

int cnt;
bool continuous_right;
stack<char> stk_left;


void init() {
	cnt = 0;
	continuous_right = false;
	stack<char> newStk;
	stk_left = newStk;
}

int main() {
	
	init();

	string input;
	getline(cin, input);

	int inputLength = input.length();
	for (int i = 0; i < inputLength; i++) {
		if (input[i] == '(') {
			continuous_right = false;
			stk_left.push('(');
		}
		else if (input[i] == ')') {
			if (continuous_right) {
				cnt++;
				stk_left.pop();
			}
			else {
				stk_left.pop();
				cnt += stk_left.size();
			}
			continuous_right = true;
		}
	}

	printf("%d", cnt);

	return 0;
}