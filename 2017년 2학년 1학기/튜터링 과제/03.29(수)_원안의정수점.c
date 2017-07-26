#include<stdio.h>

/*원의 반지름을 입력하면 원 안에 정수로만 이루어진 모든 점의 개수를 출력하는 코드*/

int main(void)
{
	int area = 0;      // 반지름 변수
	int i1 = 0, i2 = 0;// x, y 에 해당하는 변수
	int count = 0;     // 카운트 할 변수
	printf("원의 반지름을 입력하시오:");
	scanf("%d", &area);                 // 반지름 입력

	for (i1 = area; i1 >= -area; i1--)
	{
		for (i2 = area; i2 >= -area;i2--)
		{
			if (((i1*i1) + (i2*i2)) <= (area*area)) // (0,0)과 (x, y)의 거리가 area와 같거나 작을때만 카운트
			{
				count++;
				printf("(%d, %d) ", i1, i2);
			}
		}
	}
	printf("\n원안의 정수로 이루어진 모든의 점의 개수:%d\n", count); // 카운트 출력
	return 0;
}