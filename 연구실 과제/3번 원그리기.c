#include<stdio.h>
#include<math.h>

int main()
{
	int arr = 0;
	int x;

	printf("반지름을 입력하시오:");
	scanf("%d", &arr);

	for (int i = arr; i >= -arr; i--)
	{
		printf("  ");

		x = floor(2.0 * sqrt(pow(arr, 2) - pow(i, 2)));

		for (int j = (2.0 * -arr); j <= -x; j++)
			printf(" ");
		for (int j = -x; j <= x; j++)
			printf("*");
		printf("\n");
	}


	return 0;
}