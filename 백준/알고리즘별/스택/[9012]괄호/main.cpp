#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<string>
#include<stack>

using namespace std;

int t;

int main() {
	
	scanf("%d", &t);
	cin.ignore();
	while (t--) {
		
		string answer = "YES";

		string input;
		getline(cin, input);

		stack<char> stk_left;
		int strLength = input.length();
		for (int i = 0; i < strLength; i++) {
			if (i == 0 && input[0] == ')') {
				answer = "NO";
				break;
			}
			if (input[i] == '(') {
				stk_left.push('(');
			}
			else if (input[i] == ')') {
				if (!stk_left.empty()) {
					stk_left.pop();
				}
				else {
					answer = "NO";
					break;
				}
			}
		}

		if (!stk_left.empty()) answer = "NO";

		printf("%s\n", answer.c_str());

	}
	

	return 0;
}