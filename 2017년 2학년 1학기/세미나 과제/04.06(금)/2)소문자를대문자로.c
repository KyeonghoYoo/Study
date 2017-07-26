#include<Stdio.h>

void InputLowercase(char arr[][4]);   // 아스키코드와 반복문 이용해 소문자로 초기화
void printarr(char arr[][4]);         // 배열 출력
void ChangeToUppercase(char arr[][4]);// 소문자를 대문자로 변환

int main()
{
	char arr[5][4] = { NULL };

	InputLowercase(arr);

	printarr(arr);

	printf("--------\n");

	ChangeToUppercase(arr);

	printarr(arr);
	return 0;

}

void InputLowercase(char arr[][4])
{	
	int i = 0, j = 0, lowercase = 97; // lowercase는 소문자 알파벳 a의 아스키코드 값을 넣어준다.

	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 4; j++)
		{
			arr[i][j] = lowercase;
			lowercase++;              // lowercase를 증감해줌으로써 알파벳을 순서대로 넣어준다.
		}
	}
}

void printarr(char arr[][4])
{
	int i = 0, j = 0;

	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 4; j++)
		{
			printf("%c ", arr[i][j]);
		}
		printf("\n\n");
	}
}

void ChangeToUppercase(char arr[][4])
{
	int i = 0, j = 0;

	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 4; j++)
		{
			arr[i][j] -= 32;          // 소문자 알파벳 아스키 코드 값에서 32만 빼주면 대문자로 바뀐다.
		}
	}
}