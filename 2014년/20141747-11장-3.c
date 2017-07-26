#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void SortAray(int * p, int size)
{
	int *p2 = p, *p3 = p;
	int con1 = 0, con2 = 0, temp = 0;
	while (con1 < size)
	{
		con2 = 0;
		while (con2 < size)
		{
			if (*p < *p2)
			{
				temp = *p;
				*p = *p2;
				*p2 = temp;
			}
			p2++;
			con2++;
		}
		p2 = p3;
		p++;
		con1++;
	}
}

int main()
{
	srand(time(NULL));
	int arr[10] = { 0 }, n = 0, n1 = 0;
	printf("정렬하기전 무작위 값\n");
	for (n = 0; n < 10; n++)
	{
		arr[n] = rand() % ((10 - 1) + 1) + 1;
		for (n1 = 0; n1 < 10; n1++)
		{
			if (n != n1)
			{
				if (arr[n] == arr[n1])
				{
					arr[n] = rand() % ((10 - 1) + 1) + 1;
					n1 = -1;
				}
			}
		}
		printf("%d ", arr[n]);
	}
	printf("\n");
	printf("정렬한 후 값\n");
	SortAray(arr, 10);
	for (n = 0; n<10; n++)
	{
		printf("%d ", arr[n]);
	}
	printf("\n");
	return;
}