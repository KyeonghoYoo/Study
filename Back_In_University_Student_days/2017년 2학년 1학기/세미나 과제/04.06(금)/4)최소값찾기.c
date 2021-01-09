#include<Stdio.h>
#include<stdlib.h>
#include<time.h>

void InputRand(int arr[]); // 배열을 무작위 수로 초기화 하는 함수
void PrintArr(int arr[]);  // 배열을 출력하는 함수
int SrcMinValue(int arr[]); // 값을 찾고 값의 index + 1을 반환하는 함수

int main()
{
	int arr[10] = { 0 };
	int PutNum = 0;
	srand((int)time(NULL));

	InputRand(arr);
	PrintArr(arr);

	printf("\n최소값은 %d입니다.\n\n", SrcMinValue(arr));

	return 0;
}

void InputRand(int arr[])
{
	int i = 0, j = 0;
	int x = 0;

	for (i = 0; i < 10; i++)
	{
		x = rand() % (100 + 1);
		for (j = 0; j <= i; j++)
		{
			if (x == arr[j])
			{
				x = rand() % (100 + 1);
				j = -1;
			}
		}
		arr[i] = x;
	}
}

void PrintArr(int arr[])
{
	int i = 0;
	printf("\n------------------------------\n");
	for (i = 0; i < 10; i++)
		printf("%2d ", i + 1);
	printf("\n------------------------------\n");
	for (i = 0; i < 10; i++)
		printf("%2d ", arr[i]);
	printf("\n");
}

int SrcMinValue(int arr[])
{
	int i = 0;
	int min = arr[0];
	for (i = 0; i < 10; i++)
	{
		if (arr[i] < min)
			min = arr[i];
	}
	return min;
}