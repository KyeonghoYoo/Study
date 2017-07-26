#include<stdio.h>

int putValue(int *idx_x, int *idx_y, int *addnum); // 값 입력 함수
void addValue(int (*arr)[5], int idx_x, int idx_y, int addnum); // 원하는 인덱스에 접근해 증가시키는 함수
void printarr(int (*arr)[5]); // 배열 출력함수
int main()
{
	int arr[5][5] = { 0 };
	int putidx_x = 0, putidx_y = 0, addnum = 0;

	while (putValue(&putidx_x, &putidx_y, &addnum)) // 더 할 값을 0을 넣으면 putValue함수가 0을 반환한다. 
	{
		addValue(arr, putidx_x, putidx_y, addnum);

		printarr(arr);
	}

	return;
}

int putValue(int *idx_x, int *idx_y, int *addnum)
{
	printf("접근할 인덱스(arr[x][y]) 입력(x, y < 5) :");
	scanf("%d %d", idx_x, idx_y);
	printf("arr[%d][%d]에 더 할 정수 입력(종료'0') :", *idx_x, *idx_y);
	scanf("%d", addnum);
	if (*addnum == 0)
		return 0;
	return 1;
}

void addValue(int (*arr)[5], int idx_x, int idx_y, int addnum)
{
	arr[idx_x][idx_y] += addnum;
	return;
}

void printarr(int (*arr)[5])
{
	int i = 0, j = 0;
	for (i = 0;i < 5;i++)
	{
		for (j = 0;j < 5;j++)
		{
			printf("%3d", arr[i][j]);
		}
		printf("\n");
	}
	printf("\n");

	return;
}