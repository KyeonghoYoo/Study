#include<stdio.h>
                                           /* 20141747 유경호
										      홀수짝수 판별  */
int main()
{
	int num1 = 0;                          // 입력받을 변수를 선언

	printf("하나의 정수를 입력하시오.:"); 
	scanf("%d", &num1);                    // 변수에 판별할 정수 입력

	if (num1 % 2 == 0)                     // 나머지 연산자를 이용해 num % 2 == 0 이라면 짝수라고 출력
		printf("%d는 짝수입니다.\n", num1);
	else                                   // 다른 경우는 모두 홀수이므로 홀수라고 출력
		printf("%d는 홀수입니다.\n", num1);

	return 0;                              // 끝 ㅎㅅㅎ
}