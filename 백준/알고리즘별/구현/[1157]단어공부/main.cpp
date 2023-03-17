#define _CRT_SECURE_NO_WARNINGS

#include<iostream>
#include<string>

using namespace std;

int alphabet[27];
string input;
int maxValue = -1;
int cnt, idx;

int main() {

	getline(cin, input);

	for (int i = 0; i < input.length(); i++) {
		if (input[i] < 'a') { // 대문자
			alphabet[input[i] - 'A']++;
		}
		else { // 소문자
			alphabet[input[i] - 'a']++;
		}
	}
	
	char res = 'a';

	for (int i = 0; i < 27; i++) {
		if (alphabet[i] > maxValue) {
			maxValue = alphabet[i];
			res = i + 'A';
		}
		else if (alphabet[i] == maxValue) {
			res = '?';
		}
	}

	printf("%c", res);


	return 0;
}