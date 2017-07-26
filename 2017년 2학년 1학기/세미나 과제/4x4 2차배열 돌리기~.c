#include<stdio.h>
/*열헐 강의 C 406p 도전1*/
void PutValueToArr(int arr[][4]);
void PrintArr(int arr[][4]);
void TurnIndexInArr(int arr[][4]);

int main()
{
	int arr[4][4] = { 0 };


	PutValueToArr(arr);
	PrintArr(arr);
	printf("\n");

	TurnIndexInArr(arr);	// 첫번째
	PrintArr(arr);
	printf("\n");

	TurnIndexInArr(arr);	// 두번째
	PrintArr(arr);
	printf("\n");

	TurnIndexInArr(arr);	// 세번째
	PrintArr(arr);
	printf("\n");

	TurnIndexInArr(arr);	// 네번째(원상태)
	PrintArr(arr);
	return;
}

void PutValueToArr(int arr[][4])
{
	int i = 0, j = 0;
	int value = 1;

	for (i = 0;i < 4;i++)
	{
		for (j = 0;j < 4;j++)
		{
			arr[i][j] = value++;
		}
	}

	return;
}

void PrintArr(int arr[][4])
{
	int i = 0, j = 0;

	for (i = 0;i < 4;i++)
	{
		for (j = 0;j < 4;j++)
		{
			printf("%2d ", arr[i][j]);
		}
		printf("\n");
	}
}

void TurnIndexInArr(int arr[][4])
{
	int i = 0, j = 0, k = 0, l = 1, m = 0;
	int temp = 0;

	for (m = 0, temp = arr[0][0]; m < 6; m++)
	{
		for (k = 0;k < 3;k++)
		{
			if (l == 1)
			{
				arr[i][j] = arr[i + 1][j];
				i++;
			}
			else if (l == 3)
			{
				arr[i][j] = arr[i - 1][j];
				i--;
			}
		}
		l++;
		for (k = 0;k < 3;k++)
		{
			if (l == 2)
			{
				arr[i][j] = arr[i][j + 1];
				j++;
			}
			else if (l == 4)
			{
				arr[i][j] = arr[i][j - 1];
				j--;
			}
		}
		if (l == 4)
		{
			arr[0][1] = temp;
			temp = arr[0][0];
			l = 0;
		}
		l++;
	}

	temp = arr[1][1];
	arr[1][1] = arr[2][1];
	arr[2][1] = arr[2][2];
	arr[2][2] = arr[1][2];
	arr[1][2] = temp;

	return;
}