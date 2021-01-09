#include<stdio.h>

/*러시아 농부 알고리즘 재귀함수로 코드 짜기

  - 곱하고 싶은 두 수 A와 B를 첫 줄에 쓴다.
  - A부터 시작하여 2로 나누고 나머지는 버린다. 더 이상 나눌것이 없을 때까지 계속 반복하고,
    결과를 차례대로 써내려간다.
  - A를 나눈 만큼 B를 곱해나간다. 결과를 차례대로 아랫줄에 써내려간다.
  - A단에서 홀수가 있는 줄에 있는 B단의 숫자들을 모두 더 한다. 그것이 곱셈의 결과이다.*/

int func(int num1, int num2, int result)
{
	if (num1 == 0) // A를 더 이상 나눌 수 없을 때 result를 반환한다.
		return result;
	else if (num1 % 2 == 1) // A가 홀수 일 때 B를 result에 더 한다.
		result += num2;

	func(num1 /= 2, num2 *= 2, result); /* A를 2로 나누고 나머지는 버린다, A를 나눈 만큼 B를 곱한다.
										   그 값을 이용해 재귀함수를 돌린다. */
}
int main()
{
	int num1 = 0, num2 = 0, result = 0; // 정수형 변수 세개 선언

	printf("\n곱 할 두개의 정수를 입력하시오: ");
	scanf("%d %d", &num1, &num2);// num1과 num2에 입력을 받는다.
	printf("\n곱한 결과:%d\n\n", func(num1, num2, result)); // %d에 func함수를 이용해 곱한 값을 출력한다.

	return 0;
}