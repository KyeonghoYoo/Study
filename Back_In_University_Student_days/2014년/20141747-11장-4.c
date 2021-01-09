#include<stdio.h>
#include<stdlib.h>
#include<time.h>

void SumAndAvg(int * p, int size, int * sum, double * avg)
{
	int *p2, n1 = 0;
	p2 = p;
	for (n1 = 0; n1 < size; n1++)
	{
		*sum += *p2;
		p2++;
	}
	*avg = (double)*sum/10.0;
}
int main()
{
	srand(time(NULL));
	int *psum;
	double *pavg;
	int arr[10] = { 0 }, n = 0, n1 = 0, sum = 0;
	double avg = 1.0;
	psum = &sum;
	pavg = &avg;
	printf("현재 저장된 무작위 값\n");
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
	SumAndAvg(arr, 10, psum, pavg);
	printf("총합: %d\n", *psum);
	printf("평균: %0.2lf\n", *pavg);

	return;
}
