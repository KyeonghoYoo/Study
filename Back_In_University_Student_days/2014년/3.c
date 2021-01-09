#include<stdio.h>

int main()
{
	double a, b, c, d, e = 3, f = 2;
	printf("input: ");
	scanf("%lf", &a);
	printf("1 ");
	for (b = 1; b < a/2; b++)
	{
		if (!(e == a) && !(f == a))
		{
			printf("%.0lf ", e);
			e = e + 2;
			printf("%.0lf ", f);
			f = f + 2;
		}
		else
		{
			break;
		}

	}
	printf("\n");
	
	return 0;
}