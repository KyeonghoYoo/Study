#include<stdio.h>

struct student
{
	int student_Number;
	char Student_Name[20];
	double credit;
};

void main()
{
	struct student s1, s2, s3;
	printf("1번 학생의 정보를 입력하세요.\n");
	printf("학번 : ");
	scanf("%d", &s1.student_Number);
	printf("이름 : ");
	scanf("%s", s1.Student_Name);
	printf("학점 : ");
	scanf("%lf", &s1.credit);
	printf("2번 학생의 정보를 입력하세요.\n");
	printf("학번 : ");
	scanf("%d", &s2.student_Number);
	printf("이름 : ");
	scanf("%s", s2.Student_Name);
	printf("학점 : ");
	scanf("%lf", &s2.credit);
	printf("3번 학생의 정보를 입력하세요.\n");
	printf("학번 : ");
	scanf("%d", &s3.student_Number);
	printf("이름 : ");
	scanf("%s", s3.Student_Name);
	printf("학점 : ");
	scanf("%lf", &s3.credit);
	if (s1.credit > s2.credit)
	{
		if (s1.credit > s3.credit)
			printf("가장 학점이 높은 학생은 %d %s 입니다.", s1.student_Number, s1.Student_Name);
		else if (s1.credit < s3.credit)
			printf("가장 학점이 높은 학생은 %d %s 입니다.", s3.student_Number, s3.Student_Name);
	}
	else if (s1.credit < s2.credit)
	{
		if (s2.credit > s3.credit)
			printf("가장 학점이 높은 학생은 %d %s 입니다.", s2.student_Number, s2.Student_Name);
		else if (s2.credit < s3.credit)
			printf("가장 학점이 높은 학생은 %d %s 입니다.", s3.student_Number, s3.Student_Name);
	}
}