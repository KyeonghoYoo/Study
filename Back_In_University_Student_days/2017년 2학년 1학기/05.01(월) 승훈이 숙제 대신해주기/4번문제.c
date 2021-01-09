#include <stdio.h>
#include <stdlib.h> // itoa 함수를 사용하기 위해 전처리
#define LOW 5 // 가로세로 5x5
#define COL 5

void printLCD(char*dgt, char LCD[][LOW][COL]);

int main()
{
	char LCD[10][LOW][COL] = {
								{	"|||||",
									"|   |",
									"|   |",
									"|   |",
									"|||||"		}, // 0
								{	"  |  ",
									"  |  ",
									"  |  ", 
									"  |  ", 
									"  |  "		}, // 1
								{	"|||||",
									"    |",
									"|||||",
									"|    ",
									"|||||"		}, // 2
								{   "|||||",
									"    |",
									"|||||",
									"    |",
									"|||||"		}, // 3
								{   "   | ",
									"  || ",
									" | | ",
									"|||||",
									"   | " }, // 4
								{   "|||||",
									"|    ",
									"|||||",
									"    |",
									"|||||" }, // 5
								{   "|||||",
									"|    ",
									"|||||",
									"|   |",
									"|||||" }, // 6
								{   "|||||",
									"|   |",
									"    |",
									"   | ",
									"   | " }, // 7
								{	"|||||",
									"|   |",
									"|||||",
									"|   |",
									"|||||" }, // 8
								{	"|||||",
									"|   |",
									"|||||",
									"    |",
									"|||||" }, // 9
	};
	int input = 0;
	char numdigit[10];
	while(1) // 무한 반복
	{
		printf("정수를 입력하시오:");
		scanf("%d", &input);
		if (input < 0) // 입력한 수가 음수이면 반복문 빠져나옴
			break;
		itoa(input, numdigit, 10);
		printLCD(numdigit, LCD);
	}

	return 0;
}

void printLCD(char*dgt, char LCD[][LOW][COL])
{
	int i = 0, j = 0, k = 0;
	for (j = 0;j < 5;j++) // 위에 줄 부터 출력
	{
		for (i = 0;dgt[i] != NULL;i++) // 문자열의 숫자에 따라 출력
		{
			for (k = 0;k < 5;k++)      // dgt[i]에 해당하는 j줄의 모든 index 출력
			{
				printf("%c", LCD[dgt[i] - 48][j][k]); 
			}
			printf(" ");
		}
		printf("\n");
	}

	return;
}