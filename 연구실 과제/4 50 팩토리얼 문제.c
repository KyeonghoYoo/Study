#include<stdio.h>

int main()
{
	int arr[50];
	int i = 0, j = 0, k = 1, l = 0, n = 0;
	while (1)
	{
		k = 1;
		arr[0] = 1;
		for (i = 1; i < 50; i++)
			arr[i] = 0;
		printf("정수를 하나 입력하시오:");
		scanf("%d", &n);
		for (i = 1; i <= n; i++)
		{
			for (j = 0; j < k; j++)
				arr[j] *= i;
			for (j = 0; j < k; j++)
			{
				if (arr[j] > 10000)
				{
					arr[j + 1] += arr[j] / 10000;
					arr[j] %= 10000;
					if (j == k - 1)
						k++;
				}
			}
		}
		printf("%d", arr[k - 1]);
		for (i = k - 2; i >= 0; i--)
		{
			if (arr[i] == 0)
				printf("000%d");
			else if (arr[i] / 10 == 0)
				printf("000%d", arr[i]);
			else if (arr[i] / 100 == 0)
				printf("00%d", arr[i]);
			else if (arr[i] / 1000 == 0)
				printf("0%d", arr[i]);
			else
				printf("%d", arr[i]);
		}
		printf("\n");
	}
	return 0;
}