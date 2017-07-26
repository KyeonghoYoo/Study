#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int func(int n)
{
	int a, ran, sum = 0;

	for (a = 1; a <= n; a++)
	{
		ran = rand() % ((10 - 1) + 1) + 1;
		sum = sum + ran;
	}
	return sum;
}
int main()
{
	int n;
	srand(time(NULL));
	printf("input num: ");
	scanf("%d", &n);
	printf("out put: %d\n", func(n));
	return 0;
}