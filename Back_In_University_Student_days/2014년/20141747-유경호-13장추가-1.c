#include<stdio.h>

struct date {
	int year;
	int month;
	int day;
};

void ShowDate(struct date s);

void main()
{
	int i1 = 0, i2 = 0, i3 = 0, i4 = 0, count1 = 0;
	char str[30] = { NULL };
	struct date date_s1;
	date_s1.year = 0;
	date_s1.month = 0;
	date_s1.day = 0;
	gets(str);
	i2 = 1000;
	i3 = 10;
	i4 = 10;
	for (i1 = 0; str[i1] != NULL; i1++)
	{
		if (str[i1] == '/')
		{
			count1++;
			i1++;
		}
		if (count1 == 0)
		{
			if (str[i1] != 0)
				date_s1.year += (str[i1] - 48)*i2;
			if (i2 == 0)
				i2 = 1;
			else
				i2 /= 10;
		}
		if (count1 == 1)
		{
			if (str[i1] != 0)
				date_s1.month += (str[i1] - 48)*i3;
			if (i3 == 0)
				i3 = 1;
			else
				i3 /= 10;

		}
		if (count1 == 2)
		{
			if (str[i1] != 0)
				date_s1.day += (str[i1] - 48)*i4;
			if (i4 == 0)
				i4 = 1;
			else
				i4 /= 10;
		}
	}
	ShowDate(date_s1);
}

void ShowDate(struct date s)
{
	printf("%d년 %d월 %d일입니다.\n", s.year,s.month,s.day);
}