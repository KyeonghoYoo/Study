#include<stdio.h>

int main()
{
	int i = 0, j = 0, k = 0;
	int putNum = 0;

	printf("숫자를 입력하시오:");
	scanf("%d", &putNum);

	for (i = 1; i < putNum * 2; i++)
	{
		if (putNum % 2 == 0)
		{
			if (i <= putNum)
			{
				for (j = i; j < putNum; j++)
					printf(" ");
				for (k = 0; k < 2 * i; k++)
					printf("*");
				printf("\n");
			}
			else
			{
				for (j = 0; j < -(putNum - i); j++)
					printf(" ");
				for (k = i; k < putNum * 2; k++)
					printf("**");
				printf("\n");
			}
		}
		else
		{
			if (i <= putNum)
			{
				for (j = i; j < putNum; j++)
					printf(" ");
				for (k = 1; k < i * 2; k++)
					printf("*");
				printf("\n");
			}
			else
			{
				for (j = 0; j < -(putNum - i); j++)
					printf(" ");
				for (k = 1; k < (putNum * 2) - ((i - putNum) * 2); k++)
					printf("*");
				printf("\n");
			}
		}
	}

	return 0;
}