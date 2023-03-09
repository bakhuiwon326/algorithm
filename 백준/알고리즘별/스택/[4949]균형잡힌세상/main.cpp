#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<string>
#include<vector>
#include<stack>

using namespace std;

string input;
vector<string> res;
stack<char> stk_left;
bool checkRes;

void init() {
	//cin.ignore();
	stack<char> newStk_left;
	stk_left = newStk_left;
	checkRes = true;
}

int main() {
	
	while (true) {
		init();
		getline(cin, input);
		
		if (input.length() == 1 && input == ".") break;
		else {
			int inputLength = input.length();
			for (int i = 0; i < inputLength; i++) {
				if (input[i] == '(' || input[i] == '[') {
					stk_left.push(input[i]);
				}
				else if (input[i] == ')') {
					if (!stk_left.empty() && stk_left.top() == '(') {
						stk_left.pop();
						checkRes = true;
					}
					else {
						checkRes = false;
						break;
					}
				}
				else if (input[i] == ']') {
					if (!stk_left.empty() && stk_left.top() == '[') {
						stk_left.pop();
						checkRes = true;
					}
					else {
						checkRes = false;
						break;
					}
				}
			}
			if (!stk_left.empty()) checkRes = false;

			if (checkRes) {
				printf("yes\n");
			}
			else if (!checkRes) {
				printf("no\n");
			}
		}
	}


	for (int i = 0; i < res.size(); i++) {
		printf("%s\n", res[i].c_str());
	}

	return 0;
}