#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <conio.h>
#include <time.h>

void prog_1(void);  void prog_2(void);  void prog_3(void);

int main(void)
{
	int i;
	do  {
		printf("수행하고자 하는 문제 번호 입력(1~3), 종료(0)\n  >> ");
		scanf("%d", &i);	fflush(stdin);

		switch (i) {	//문제 선택
		case  1:	 prog_1();		printf("\n\n");		system("pause");	system("cls");		break;
		case  2:	 prog_2();		printf("\n\n");		system("pause");	system("cls");		break;
		case  3:	 prog_3();		printf("\n\n");		system("pause");	system("cls");		break;
		case  0:	 printf("종료되었습니다.\n");		break;
		default:	 printf("잘못입력하였습니다.\n\n"); system("pause");	system("cls");
		}
	} while (i != 0);   //사용자가 종료를 원할 때까지 반복

}

void prog_1(void) // 첫번째 문제 프로그램 8장 5번 문제
{
	int i = 0x324F3A24, j; // i 에 0x324F3A24 저장
	char *p;               // char형 포인터 변수 p 선언

	printf("int i = ");
	for (j = 28;j >= 0;j -= 4)
	{
		p = i >> j & 15;   // 포인터 변수 p를 이용하여 i의 저장값을 
		printf("%X", p);   // 바이트 단위로 쪼개서 담아 바로 출력
	}
	printf("\n");

	return;
}


void prog_2(void) // 두번째 문제 프로그램 9장 4번 문제
{
	int A[][4] = 
	{ { 12,30,82,54 },
	{ 43,51,32,47 },
	{ 30,42,41,69 } }; // 각 원소의 값 초기화

	int i, j;

	for (i = 0;i < 3;i++)
	{
		for (j = 0;j < 4;j++)
		{
			A[i][j] = A[i][j] * 10 + 2; // 각 원소를 수식과 같이 수정
		}
	}

	for (i = 0;i < 3;i++) // 출력
	{
		for (j = 0;j < 3;j++)
		{
			printf("%d, ", A[i][j]);
		}
		printf("%d\n", A[i][3]);
	}

	return;
}


void prog_3(void) // 세번째 문제 프로그램 9장 11번 문제
{
	int arr[5][4] = 
	{ { 97,90,88,95 },
	{ 76,89,75,83 },
	{ 60,70,88,82 },
	{ 83,89,92,85 },
	{ 75,73,72,78 } }; // 배열에 값 입력

	int i, j;
	int sum;

	for (j = 0;j < 4;j++) // 0열부터 계산
	{
		if (j < 2)        // 중간, 기말 정보 출력
			printf("중간%d\n", j + 1);
		else
			printf("기말%d\n", j - 1);

		for (i = 0, sum = 0;i < 5;i++) // 행이 학생들이므로 행 차례대로 계산
		{
			sum += arr[i][j];
		}
		printf("합: %d\n", sum);
		printf("평균: %.1lf\n\n", (double)sum / i); // 형변환, i == 5
	}
	return;
}