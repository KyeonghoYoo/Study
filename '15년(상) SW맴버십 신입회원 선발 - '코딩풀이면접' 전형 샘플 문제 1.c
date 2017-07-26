#include<stdio.h>

int main()
{
	int count1[20] = { 0 }, T = 0, i = 0, i2 = 0, i3 = 0, countn = 0, countn2 = 0, countn3 = 0;
	char str1[20][20] = { 0 };
	scanf("%d", &T);
	for (i = 0; i < T; i++)
	{
		scanf("%s", str1[i]);
	}
	for (i = 0; i < T; i++)
	{
		printf("#testcase%d\n", i + 1);
		printf("%s ", str1[i]);
		while (str1[i][countn] != '\0')
		{
			countn++;
		}
		printf("%d\n", countn);
		countn2 = 1;
		for (i2 = 0; str1[i][i2] != '\0'; i2++)
		{
			if (str1[i][i2] == str1[i][i2 + 1])
			{
				countn2++;
			}
			else
			{
				if (countn2 < 10)
				{
					countn2 = 1;
					countn3 += 2;
				}
				else if (countn2 >= 10)
				{
					countn2 = 1;
					countn3 += 3;
				}
			}
		}
		countn2 = 1;
		if (countn3<countn)
		{
			countn3 = 0;
			for (i2 = 0; str1[i][i2] != '\0'; i2++)
			{
				if (str1[i][i2] == str1[i][i2 + 1])
				{
					countn2++;
				}
				else
				{
					if (countn2 < 10)
					{
						printf("%c%d", str1[i][i2], countn2);
						countn2 = 1;
						countn3 += 2;
					}
					else if (countn2 >= 10)
					{
						printf("%c%d", str1[i][i2], countn2);
						countn2 = 1;
						countn3 += 3;
					}
				}

			}
			printf(" %d\n", countn3);
		}
		else
		{
			printf("%s ", str1[i]);
			printf("%d\n", countn);
		}

		countn = 0;
		countn2 = 0;
		countn3 = 0;
	}

	return 0;
}