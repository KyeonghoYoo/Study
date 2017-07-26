#include<stdio.h>

int main()
{
	int arr[20][20], num1, num2, i = 0, j = 0, con1 = 0, count1 = 0;
	scanf("%d", &num1);
	num2 = num1*num1;

	for (i = 0; i < num1; i++)
	{
		for (j = 0; j < num1; j++)
		{
			arr[i][j] = 0;
		}
	}

	i = 0; j = 0;
	arr[0][0] = num2;

	for (con1 = 0; con1 < num1; con1++)
	{
		for (j += 1; j < num1 - con1; j++)
		{
		    arr[i][j] = arr[i][j - 1] - 1;
		}
		j -= 1;

		for (i += 1; i < num1 - con1; i++)
		{
			arr[i][j] = arr[i - 1][j] - 1;
		}
		i -= 1;
	
		for (j -= 1; j >= count1; j--)
		{
			arr[i][j] = arr[i][j + 1] - 1;
		}
		j += 1;
		for (i -= 1; i > count1; i--)
		{
			arr[i][j] = arr[i + 1][j] - 1;
		}
		i += 1;	
		count1++;
	}

	for (i = 0; i < num1; i++)
	{
		for (j = 0; j < num1; j++)
		{
			printf("%4d ", arr[i][j]);
		}
		printf("\n");
	}

	return 0;
}