#include<stdio.h>
#include<math.h>

struct point // 구조체 point 정의
{
	double x, y;
};

void main()
{
	struct point pos1, pos2; // 구조체 변수 pos1, pos2 선언
	printf("1번째 좌표를 입력하세요. (x,y) : ");
	scanf("%lf %lf", &pos1.x, &pos1.y);
	printf("2번째 좌표를 입력하세요. (x,y) : ");
	scanf("%lf %lf", &pos2.x, &pos2.y);
	printf("두점 사이의 거리는 %0.2lf입니다.\n", sqrt((pos1.x - pos2.x)*(pos1.x - pos2.x) + (pos1.y - pos2.y)*(pos1.y - pos2.y)));
}