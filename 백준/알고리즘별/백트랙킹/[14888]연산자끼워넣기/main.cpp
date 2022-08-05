#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int n;
vector<char> order;
vector<char> operators;
vector<int> result;
int operands[11];
bool visited[11];

void backtracking(int depth) {

	// basecase
	if (depth == n - 1) {
		int cal = 0;
		cal += operands[0];
		for (int i = 0; i < n - 1; i++) {
			if (order[i] == '+') cal += operands[i + 1];
			else if (order[i] == '-') cal -= operands[i + 1];
			else if (order[i] == '*') cal *= operands[i + 1];
			else if (order[i] == '/') cal /= operands[i + 1];
		}
		result.push_back(cal);
		return;
	}

	for (int i = 0; i < n - 1; i++) {
		if(!visited[i]) {
			visited[i] = true;
			order.push_back(operators[i]);
			backtracking(depth + 1);
			visited[i] = false;
			order.pop_back();
		}
	}

}

int main() {

	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		scanf("%d", &operands[i]);
	}

	int num;
	
	scanf("%d", &num); // plus num;
	for (int i = 0; i < num; i++) operators.push_back('+');

	scanf("%d", &num); // minus num;
	for (int i = 0; i < num; i++) operators.push_back('-');

	scanf("%d", &num); // product num;
	for (int i = 0; i < num; i++) operators.push_back('*');

	scanf("%d", &num); // divide num;
	for (int i = 0; i < num; i++) operators.push_back('/');

	backtracking(0);

	sort(result.begin(), result.end());

	printf("%d\n", result.back());
	printf("%d", result[0]);

	return 0;
}