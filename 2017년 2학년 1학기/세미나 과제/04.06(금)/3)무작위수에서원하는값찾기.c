#include<Stdio.h>
#include<stdlib.h>
#include<time.h>

void InputRand(int arr[]); // 배열을 무작위 수로 초기화 하는 함수
void PrintArr(int arr[]);  // 배열을 출력하는 함수
int SearchValue(int arr[], int PutNum); // 값을 찾고 값의 index + 1을 반환하는 함수

int main()
{
	int arr[10] = { 0 };
	int PutNum = 0;

	srand((int)time(NULL));
	InputRand(arr);

	printf("찾고 싶은 값을 입력하시오(1~10):");
	scanf("%d", &PutNum);

	printf("%d 값은 %d 번째에 위치하고 있습니다.\n", PutNum, SearchValue(arr, PutNum));

	printf("현재 배열에 저장된 값은\n");
	PrintArr(arr);
	printf("\n");
	return 0;
}

void InputRand(int arr[])
{
	int i = 0, j = 0;
	int x = 0;

	for (i = 0; i < 10; i++)
	{
		x = rand() % (10 + 1);
		for (j = 0; j <= i; j++)
		{
			if (x == arr[j])
			{
				x = rand() % (10 + 1);
				j = -1;
			}
		}
		arr[i] = x;
	}
}

void PrintArr(int arr[])
{
	int i = 0;
	for (i = 0; i < 10; i++)
		printf("%d ", arr[i]);
	printf("\n");
}

int SearchValue(int arr[], int PutNum)
{
	int i = 0;

	for (i = 0; i < 10; i++)
	{
		if (arr[i] == PutNum)
			return i + 1;
	}
	return -1;
}