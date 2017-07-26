#include<stdio.h>

int main()
{
	int arr1[5], a, b, count[5] = { 0, 0, 0, 0, 0 };
	printf("¼ö ÀÔ·Â\n");
	scanf("%d %d %d %d %d", &arr1[0], &arr1[1], &arr1[2], &arr1[3], &arr1[4]);
	for (a = 0; a <= 4; a++)
	{
		for (b = 0; b <= 4; b++)
		{
			if (arr1[a] >= arr1[b])
			{
				count[a] += 1;
			}
		}
	}
	for (a = 0; a <= 4; a++)
	{
		if (count[a] == 5)
		{
			printf("ÃÖ´ñ°ª :%d\n", arr1[a]);
		}
		if (count[a] == 1)
		{
			printf("ÃÖ¼Ú°ª :%d\n", arr1[a]);
		}
	}

	return 0;
}