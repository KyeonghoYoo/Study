#include<Stdio.h>
#include<stdlib.h>
#include<time.h>

void InputRand(int arr[]); // 배열을 무작위 수로 초기화 하는 함수
void PrintArr(int arr[]);  // 배열을 출력하는 함수
void BubbleSort(int arr[]); // 버블정렬을 시행하는 함수
void b_search(int arr[], int PutNum); // 이진 탐색으로 값의 인덱스를 출력해주는 함수
int main()
{
	int arr[20] = { 0 };
	int PutNum = 0;
	srand((int)time(NULL));
	printf("정렬 전:");
	InputRand(arr);
	PrintArr(arr);
	printf("정렬 후:");
	BubbleSort(arr);
	PrintArr(arr);
	printf("\n찾고자 하는 값을 입력하시오(0~99):");
	scanf("%d", &PutNum);
	b_search(arr, PutNum);
	return 0;
}

void InputRand(int arr[])
{
	int i = 0, j = 0;
	int x = (rand() % 100);

	for (i = 0; i < 20; i++)
	{
		for (j = 0; j <= i; j++)
		{
			if (x == arr[j])
			{
				x = (rand() % 100);
				j = -1;
			}
		}
		arr[i] = x;
	}

	return;
}

void PrintArr(int arr[])
{
	int i = 0;

	for (i = 0; i < 20; i++)
		printf("%2d ", arr[i]);
	printf("\n");

	return;
}

void BubbleSort(int arr[])
{
	int i = 0, j = 0;
	int temp = 0;
	for (i = 0; i < 20; i++)
	{
		for (j = 0; j < 19; j++)
		{
			if (arr[j] > arr[j + 1])
			{
				temp = arr[j + 1];
				arr[j + 1] = arr[j];
				arr[j] = temp;
			}
		}
	}
	return;
}

void b_search(int arr[], int PutNum)
{
	int i = 0, j = 19, k = (i + j) / 2, l = 0;
	int num = 0;
	printf("[%2d %2d]\n", i, j);
	while (arr[k] != PutNum)
	{
		if (arr[k] < PutNum)
		{
			i = k + 1;
			k = (i + j) / 2;
		}
		else
		{
			j = k - 1;
			k = (i + j) / 2;
		}
		printf("[%2d %2d]\n", i, j);
		if (i > j)
		{
			printf("찾는 값은 존재하지 않습니다!\n");
			return;
		}
	}
	printf("탐색 결과:%d\n", k);

	return;
}