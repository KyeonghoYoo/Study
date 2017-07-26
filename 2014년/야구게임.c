#include<stdio.h>
#include<stdlib.h>
#include<time.h>



int main()
{
	int n1, n2, n3, a, b, c, ran, chance, try, countStrik, countBall;
	char yn;
	srand(time(NULL));
	while (1)
	{

		do
		{
			a = rand() % ((9 - 1) + 1) + 1;
			b = rand() % ((9 - 1) + 1) + 1;
			c = rand() % ((9 - 1) + 1) + 1;
		} while (a == b || a == c || b == c);
		chance = 9;
		printf("야구 게임을 시작합니다. 기회는 9번 입니다.\n");
		printf("9번 안에 숫자 3개를 맞춰주세요.<숫자는 중복되지 않습니다>\n\n");
		printf("숫자 3개를 입력해 주세요. ex> 1 2 3\n");
		for (try = 1; try <= chance; try++)
		{
			countStrik = 0;
			countBall = 0;
			printf("%d회차 input : ", try);
			scanf("%d %d %d", &n1, &n2, &n3);
			if (n1 == a)
			{
				countStrik += 1;
			}
			else if (n1 == b || n1 == c)
			{
				countBall += 1;
			}
			if (n2 == b)
			{
				countStrik += 1;
			}
			else if (n2 == a || n2 == c)
			{
				countBall += 1;
			}
			if (n3 == c)
			{
				countStrik += 1;
			}
			else if (n3 == b || n3 == a)
			{
				countBall += 1;
			}
			printf("strike = %d ball = %d\n", countStrik, countBall);
			if (countStrik == 3)
			{
				break;
			}
		}
		if (countStrik == 3)
		{
			printf("Good Jop!!!\n");
		}
		else
		{
			printf("You Fauled.  :<\n");
			printf(" 정답은 %d %d %d 입니다.\n", a, b, c);
		}
		while (1)
		{
			printf("\nRestart<y or n> ? ");
			fflush(stdin);
			scanf("%c", &yn);
			if (yn == 'y' || yn == 'Y' || yn == 'n' || yn == 'N')
			{
				break;
			}
			else
			{
				printf("잘못된 입력입니다.\n");
			}
		}
		if (yn == 'y' || yn == 'Y')
		{
			system("cls");
		}
		else
		{
			break;
		}
	}
	printf("Thank you for Playing ^_^\n");
	return 0;
}