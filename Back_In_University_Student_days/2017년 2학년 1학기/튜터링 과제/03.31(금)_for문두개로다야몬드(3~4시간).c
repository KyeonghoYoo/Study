#include<stdio.h>
/* for문 2개로 다이아몬드 출력하기
ex)정수 하나 입력:5
    *
   **
   ***
  ****
  *****
  ****
   ***
   **
    *
  */
int main(void)
{
	int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
	int putN = 0;
	printf("정수 하나 입력:");
	scanf("%d", &putN);
	for (i1 = 0;i1 <= (2 * putN);i1++)       // 별을 출력하기 위해 필요한 행 수 만큼 실행되는 반복문
	{
		if (i1 % 2 == 1)                     // i1 이 홀수일 때 13 변화
		{
			if (putN > i1)                   // i1 이 정수만큼 도달 허기까지 i3 증가하다가 도달하면 감소
				i3++;
			else
				i3--;
		}
		else if (i1 % 2 == 0)                // i1 이 짝수일 때 i4 변화
		{
			if (putN > i1)                   // i1 이 정수만큼 도달 허기까지 i4 증가하다가 도달하면 감소
				i4++;
			else
				i4--;
		}

		for (i2 = 0;i2 < putN;i2++)          // 별과 공백을 입력한 정수의 수만큼 출력하는 반복문
		{
			if (i1 == putN)                  // i1 이 입력한 정수와 같아지면 별을 정수만큼 출력
			{
				if (putN % 2 == 0)
					printf(" *");        // 짝수일때 가운데 별
				else
					printf("* ");        // 홀수일때 가운데 별
			}
			else if (i1 % 2 == 1)            // i1 이 홀수일 때 i3를 이용해 별을 출력
			{
				if ((putN / 2) - i3 < i2 && i2 < (putN / 2) + i3)
					printf("* ");
				else
					printf("  ");
			}
			else                             // i1 이 짝수일 때 i4를 이용해 별을 출력
			{
				if ((putN / 2) - i4 < i2 && i2 < (putN / 2) + i4 - 1)
					printf(" *");
				else
					printf("  ");
			}
		}
		printf("\n ", i3, i4);
		if (putN == i1)    // i1 이 입력한 정수의 수와 같아질때 별을 정수만큼 출력하기 위한 조건문
		{
			i3 = (putN / 2) + 1; // 입력한 정수가 i1과 같을땐 i3,i4를 이용한 출력을 하지 않지만
			i4 = (putN / 2) + 1; // i3,i4는 계속 증감 되므로 1씩 더해준다.
			if (putN % 2 == 1)   // 홀수 일때는 행수가 하나 줄어드므로 i4를 1 더 해서 맞춰준다.(king어거지ㅎ)
				i4++;
		}
	}
	return 0;
}