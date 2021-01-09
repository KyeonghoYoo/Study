#include<stdio.h>

int Triangle(int min, int max)
{
	int con1 = 0;
	for (; min <= max; min++)
	{
		for (con1 = 0; con1 < min; con1++)
		{
			printf("*");
		}
		printf("\n");
	}

	return 0;
}

int main()
{
	int min = 0, max = 0;
	printf("Intput Number: ");
	scanf("%d %d", &min, &max);
	Triangle(min, max);
	return 0;
}