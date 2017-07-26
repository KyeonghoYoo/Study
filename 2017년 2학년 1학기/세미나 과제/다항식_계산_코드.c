#include<stdio.h>

/*(x^2+3x+2)+(10x^2+8x+1) 를 계산하는 코드 */

int main()
{
	int arr1[3] = { 1, 3, 2 }; //(x^2+3x+2), (10x^2+8x+1) 두 식에대한 각 지수의 계수를 배열에 순차적으로 담음
	int arr2[3] = { 10, 8, 1 };

	int arr3[3] = { 0 };//식을 계산한 값을 담을 배열 선언

	int i = 0; // for문 조건을 위한 변수

	printf("\n(x^2+3x+2)+(10x^2+8x+1)을 계산\n");

	for (i = 0; i < 3; i++) // 두 다항식의 같은 지수의 계수끼리 덧셈하여 결과를 arr3[i]에 저장하는 반복문
	{
		arr3[i] = arr1[i] + arr2[i];
	}

	printf("\n계산 결과:%dx^2+%dx+%d\n\n", arr3[0], arr3[1], arr3[2]); // 결과값 출력하는 printf문

	return 0;
}