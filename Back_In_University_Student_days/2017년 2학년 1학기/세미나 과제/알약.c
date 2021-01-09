#include<stdio.h>

int count = 0;

void medicine(int W, int H)
{
	if (W == 0 && H == 0) // 각 경우의수를 카운트하면서 종료
	{
		count++;
		return;
	}
	if (W > 0) // 알약을 먹고 W를 출력하고 반을 병에 담음
		medicine(W - 1, H + 1);

	if (H > 0)
		medicine(W, H - 1);
	/* W와 H는 항상 쌍을 이루어야한다. 
	문자열의 길이는 2N W+H=2N이므로 W와 H는 항상 수가 같고
	약을 쪼개고 W를 출력해야 H를 출력 할 수 있음 그러므로 한 쌍을 이뤄야함
	남은 알약을 먼저 먹는 경우와 알약이 남았지만 반짜리를 먼저 먹는 경우*/
}

int main()
{
	int arr[100], n;
	scanf("%d", &n); // 알약의 개수 입력

	medicine(n, 0);  // 모든 경우의 수를 계산하는 함수 출력

	printf("%d\n", count); // 결과 출력

	return 0;
}