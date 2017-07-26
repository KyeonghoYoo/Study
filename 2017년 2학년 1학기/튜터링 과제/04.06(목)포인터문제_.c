#include<stdio.h>

void AddValue(int *ptr1, int *ptr2);
void ChangeValue(int **ptr1, int **ptr2);
void PrintValueAndAddress(int *ptr1, int *ptr2);

int main()
{
	int *ptr1 = 0, *ptr2 = 0;  // 정수형 포인터 변수 선언
	int num1 = 0, num2 = 0;    // 정수형 변수 선언
	ptr1 = &num1;
	ptr2 = &num2;

	AddValue(ptr1, ptr2);      // 포인터를 이용해 각 num1과 num2에 10, 20을 더해주는 함수

	PrintValueAndAddress(ptr1, ptr2); // 값과 주소값을 출력하는 함수

	ChangeValue(&ptr1, &ptr2); // 서로 가르키는 주소값을 교환해주는 함수

	PrintValueAndAddress(ptr1, ptr2);

	return;
}

void AddValue(int *ptr1, int *ptr2)    // ^_^
{
	*ptr1 += 10;
	*ptr2 += 20;

	return;
}

void ChangeValue(int **ptr1, int **ptr2) // main 함수에 있는 포인터 변수*ptr1과 *ptr2의 주소값을 받는 
{										 // 더블 포인터를 매개변수로 정의한다.
	int *ptr_temp = 0;

	ptr_temp = *ptr1;
	*ptr1 = *ptr2;
	*ptr2 = ptr_temp;

	return;
}

void PrintValueAndAddress(int *ptr1, int *ptr2)
{
	printf("ptr1 : %d  ptr 2 : %d\n", *ptr1, *ptr2);
	printf("ptr1 : %p  ptr 2 : %p\n\n", ptr1, ptr2);

	return;
}