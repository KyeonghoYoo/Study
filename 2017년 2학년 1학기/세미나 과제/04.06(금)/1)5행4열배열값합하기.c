#include<Stdio.h>

int ArrSum(int(*arr)[4]); // 배열의 값들을 더해서 반환하는 함수

int main()
{
	int arr[5][4] = { 0 };
	int i = 0, j = 0;

	for (i = 0; i < 5; i++) // 임의의 값 입력
	{
		for (j = 0; j < 4; j++)
		{
			scanf("%d", &arr[i][j]);
		}
	}

	printf("%d\n", ArrSum(arr)); // 합 값 출력

	return 0;
}

int ArrSum(int(*arr)[4])
{
	int i = 0, j = 0;
	int sum = 0;
	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 4; j++)
		{
			sum += arr[i][j];
		}
	}
	return sum;
}
