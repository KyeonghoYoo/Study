#include<stdio.h>
/* 하나의 정수를 입력 받아 2진수와 %x, %o를 쓰지 않고 16진수, 8진수 출력하고 %X와 %o를 써서 비교하기*/
int main(void)
{
	int n = 0;
	int i1 = 0, i2 = 0;
	int a = 0, b = 0, c = 1;

	printf("정수 하나를 입력 하세요.:");
	scanf("%d", &n);

	printf("2진수:");
	for (i1 = 31;i1 >= 0;i1--) // 2진수로 출력하는 코드 32비트부터 값을 출력한다.
	{
		printf("%d", (n >> i1) & 1);
	}

	printf("\n8진수:%o ", n);
	for (a = n; a != 0;) // 10진수를 8진수로 변환해서 변수에 담읆
	{
		b += c * (a % 8);
		c *= 10;
		a /= 8;
	}
	printf("%d\n", b);

	printf("16진수:%X ", n);
	for (c = 0, a = n;a != 0;c++) // 16진수 자릿수 계산
	{
		a /= 16;
	}
	for (i1 = c;i1 > 0; i1--) // 16진수 거꾸로 출력
	{
		a = n;
		for (i2 = i1; i2 > 0; i2--)
		{
			if (i2 == 1)
			{
				if ((a % 16) < 10)
					printf("%d", a % 16);
				else
					printf("%c", (a % 16) + 55);
			}
			a /= 16;
		}
	}
	printf("\n");

	return 0;
}