#include<stdio.h>

void inputarr(int(*arr)[10], int a, int b);// 가로 세로 길이에 의해 숫자를 arr 배열에 넣어주는 함수 선언
void printfarr(int(*arr)[10], int a, int b);// inputarr 에서 arr에 넣어준 숫자를 출력해주는 함수 선언
void printS(int count, int *arr2);// ShortestDistances 함수로 부터 최단 거리를 출력하는 함수 선언
void ShortestDistance(int(*arr)[10], int aSIZE, int bSIZE, int a, int b, int count, int arr2[]);// 최단 거리를 구하는 함수 선언


int main(void)
{
	int arr[10][10] = { 0 }; // 가로 세로에 따라 만들어질 판을 담을 배열!
	int arr2[20] = { 0 };    // 최단 거리를 담을 배열!
	int PutN1 = 0, PutN2 = 0;// 가로, 세로 변수

	printf("가로 세로 길이를 입력하시오.(0 < N <= 10):");
	scanf("%d %d", &PutN1, &PutN2); // 가로, 세로 입력
	printf("\n");
	inputarr(arr, PutN1, PutN2); // 배열에 입력된 가로 세로 수에 따라 arr에 수를 넣어주는 함수
	printfarr(arr, PutN1, PutN2);// 배열 출력해주는 함수
	printf("\n");
	printf("- 모든 최단 거리 -\n");
	ShortestDistance(arr, PutN1, PutN2, 0, PutN2 - 1, 0, arr2); // 최단거리 출력해주는 함수 ~.~
	printf("\n");
	return 0;
}

void inputarr(int(*arr)[10], int a, int b)
{
	int i = 0, j = -1, k = 0;
	int Num1 = 1;

	for (i = b - 1;i >= 0;i--) // 위로 한 칸씩 이동
	{
		if (j < 0)    // 지그재그로 숫자를 채우기 위한 조건문 j가 0보다 적으면 오른쪽으로 크면 왼쪽으로 채워짐
		{
			j++;
			while (j < a)
			{
				arr[i][j] = Num1;
				Num1++;
				j++;
			}
		}
		else
		{
			j--;
			while (j >= 0)
			{
				arr[i][j] = Num1;
				Num1++;
				j--;
			}
		}
	}
	return;
} // 가로 세로 길이에 의해 숫자를 arr 배열에 넣어주는 함수 

void printfarr(int(*arr)[10], int a, int b)
{
	int i = 0, j = 0;

	for (i = 0;i < b;i++)
	{
		for (j = 0;j < a;j++)
			printf("%2d ", arr[i][j]);
		printf("\n");
	}
	return;
} // inputarr 에서 arr에 넣어준 숫자를 출력해주는 함수 

void printS(int count, int *arr2)
{
	int i = 0;

	for (i = 0; i <= count;i++)
		printf("%d ", arr2[i]);
	printf("\n");

	return;
} // ShortestDistances 함수로 부터 최단 거리를 출력하는 함수

void ShortestDistance(int(*arr)[10], int aSIZE, int bSIZE, int a, int b, int count, int arr2[])
{

	if (b == 0 && a == aSIZE - 1)
	{
		arr2[count] = arr[b][a];
		printS(count, arr2); // 도착 했으면 printS 함수로 출력

		return;
	}

	if (a < aSIZE) // 오른쪽으로 이동
	{
		arr2[count] = arr[b][a];
		ShortestDistance(arr, aSIZE, bSIZE, a + 1, b, count + 1, arr2);
	}
	if (b > 0) // 위로 이동
	{
		arr2[count] = arr[b][a];
		ShortestDistance(arr, aSIZE, bSIZE, a, b - 1, count + 1, arr2);
	}

} // 최단 거리를 구하는 함수

