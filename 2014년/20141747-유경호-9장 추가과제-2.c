#include<stdio.h>

void Triangle(int min, int max)
{
	if (min == 0 && max == 0)
	{
		printf("\n");
		return;
	}
	else if (min <= max && max != 0)
	{
		Triangle(min, 0);
	}
	else if (min > 0 && max == 0)
	{
		printf("*");
		Triangle(min - 1, 0);
	}
	if (min < max)
	{
		return Triangle(min + 1, max);
	}
}

int main()
{
	int min = 0, max = 0;

	printf("intput Number : ");
	scanf("%d %d", &min, &max);
	printf("Triangle(%d, %d) :\n", min, max);
	Triangle(min, max);
	return 0;
}