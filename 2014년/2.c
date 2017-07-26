#include<stdio.h>

int func(int a, int b, int c, int n) /*1.시작 값(a), 곱할 값(b), 더할 값(c), 몇 번째 항인지 나타내는 정수(n) 네개의
                                       값을 입력받게 되니까 네개의 변수에 따라 값이 달라져야한다.*/
{
	int num, result; // 2.반복문 조건식에 쓰일 변수를 선언해준다. 4.계산된 값을 넣어줄 변수도 선언.
	result = a; // 첫번째 항은 시작값과 같으므로 a의 값으로 초기값을 설정해준다.
	for (num = 1; num < n; num++) // 3.n 번째 항의 값을 구하는것이기 때문에 1번째항부터 차근차근 n번째 항을 구해준다.
	{
		result = result*b + c;
	}

	return result; // n번째 항의 값이 입력된 result를 리턴값으로 설정해준다.
}

int main()
{
	int a, b, c, n;
	printf("시작 값(a), 곱할 값(b), 더할 값(c), 몇 번째 항인지 나타내는 정수(n)를 차례대로 입력하시오.\n:");
	scanf("%d %d %d %d", &a, &b, &c, &n);
	printf("%d번째 항의 값은 %d\n입니다.", n, func(a, b, c, n));
	return 0;
}