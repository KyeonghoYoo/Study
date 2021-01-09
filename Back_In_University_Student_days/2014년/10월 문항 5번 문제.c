#include<stdio.h>

int main()
{
	int arr[20][20], n, n2, con1, i, j, count1 = 0, count2 = 0, count3 = 0, count4 = 0;
	i = 0;
	j = 0;
	arr[0][0] = 1;
	printf("Plase enter any value for nXn array : ");
	scanf("%d", &n);
	n2 = n;

	for (con1 = 1; con1 <= n*n; con1++)
	{
		if (count1 == count2 && count1 == count3 && count1 == count4)
		{
			for (j += 1; j <= n - 1; j++)
			{
				arr[i][j] = arr[i][j - 1] + 1;
			}
			j -= 1;
			count1++;
		}

		else if (count2 == count3 && count2 == count4)
		{
			for (i += 1; i <= n - 1; i++)
			{
				if (i == 0)
				{
					arr[i][j] = 10;
				}
				else
					arr[i][j] = arr[i - 1][j] + 1;
			}
			i -= 1;
			count2++;
		}

		else if (count3 == count4)
		{
			for (j -= 1; j >= n2 - n; j--)
			{
				arr[i][j] = arr[i][j + 1] + 1;
			}
			j += 1;
			count3++;
		}
		else if (count4 != count1 && count4 != count2 && count4 != count3)
		{
			for (i -= 1; i > n2 - n; i--)
			{
				arr[i][j] = arr[i + 1][j] + 1;
			}
			i += 1;
			n -= 1;
			count4++;
		}
	}
	for (i = 0; i <= n2 - 1; i++)
	{
		for (j = 0; j <= n2 - 1; j++)
		{
			printf("%4d ", arr[i][j]);
		}
		printf("\n");
	}


	return 0;
}