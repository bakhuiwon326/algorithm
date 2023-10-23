#include <iostream>
#include <memory.h>

using namespace std;

int n;
long long edge_0[100]; // 0으로 시작하면서 1이 연속으로 나오지 않는 이진수 중 끝이 0인 개수
long long edge_1[100]; // 0으로 시작하면서 1이 연속으로 나오지 않는 이진수 중 끝이 1인 개수

int main() {

	scanf("%d", &n);

	// 이친수는 맨앞이 반드시 1이여야함. 두번째 자리도 0이여야함.
	// 즉, 맨 앞 숫자를 제외한, 나머지 숫자가 0으로 시작하면서 1이 중복되면 안된다.
	// 자리수가 커질수록, 맨 끝에 0또는 1이 붙는다.
	// 이 성질을 이용해 이친수를 만들기 위해서는
	// 맨 끝이 0이면, 1과 0을 파생하고
	// 맨 끝이 1이면, 0만 파생한다.

	memset(edge_0, 0, sizeof(edge_0));
	memset(edge_1, 0, sizeof(edge_1));

	edge_0[1] = 0;
	edge_1[1] = 1;

	for (int i = 2; i <= n; i++) {
		edge_0[i] = edge_0[i - 1] + edge_1[i - 1];
		edge_1[i] = edge_0[i - 1];
	}

	printf("%lld", edge_0[n] + edge_1[n]);

	return 0;
}