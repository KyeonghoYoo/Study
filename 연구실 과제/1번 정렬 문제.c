#include<stdio.h>

int num = 1;

void putNumtoArr(int arr[][8][8][8]); // 배열에 숫자를 넣는 함수
void printArr(int arr[][8][8][8]); // 배열을 모두 출력하는 함수

int main()
{
	int arr[8][8][8][8] = { 0 }; // 4차원 배열 arr을 선언

	putNumtoArr(arr);
	printArr(arr);

	return 0;
}

void putNumtoArr(int arr[][8][8][8])
{
	int i = 0, j = 0, k = 0, l = 0;

	for (k = 0; k < 8; k++)
	{
		for (l = 0; l < 8; l++)
		{
			for (i = 0; i < 8; i++)
			{
				for (j = 0; j < 8; j++, num)
				{
					arr[k][l][i][j] = num++;
				}
			}
		}
	}

	return;
}

void printArr(int arr[][8][8][8])

{
	int i = 0, j = 0, k = 0, l = 0;

	for (k = 0; k < 8; k++)
	{
		for (i = 0; i < 8; i++)
		{
			for (l = 0; l < 8; l++)
			{
				for (j = 0; j < 8; j++, num)
				{
					printf("%4d ", arr[k][l][i][j]);
				}
				printf("\t");
			}
			printf("\n");
		}
		printf("\n\n");
	}

	return;
}
