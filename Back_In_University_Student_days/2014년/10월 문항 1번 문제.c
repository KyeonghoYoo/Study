#include<stdio.h>

int main()
{
	int n, con1, con2, result;
	while (1)
	{
		printf("출력할 단을 입력해주세요: ");
		scanf("%d", &n);
		if (n <= 9 && 1 <= n)
		{
			for (con1 = 1; con1 <= 9; con1++)
			{
				for (con2 = 1; con2 <= (con1*n); con2++)
				{
					printf("*");
				}
				printf("\n");
			}
			break;
		}
		else
		{
			printf("1~9 사이의 숫자로 다시 입력해주세요----------\n");
		}
	}
	return 0;
}