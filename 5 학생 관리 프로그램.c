#include<stdio.h>
#include<stdlib.h>
// 학번 이름 점수 평균 등수

typedef struct __student
{
	int studentNumber;
	char studentName[10];
	int nativelang;
	int math;
	int science;
	int sum;
	double avr;
	int rank;
}Student;

void array(Student student[]); // 등수 정렬
void func1(Student student[]); // 삽입
void func2(Student student[]); // 저장
void func3(Student student[]); // 출력
void func4(Student student[]); // 검색
void func5(Student student[]); // 로드
void func6(Student student[]); // 삭제

int main()
{
	int putNum = 0;
	Student student[10];

	for (int i = 0; i < 10; i++)
	{
		student[i].studentNumber = NULL;
		student[i].avr = 0.0;
	}

	while (putNum != 7)
	{
		printf("1.삽입 2.저장 3.출력 4.검색 5.로드 6.삭제 7.종료\n>> ");
		scanf("%d", &putNum);
		switch (putNum)
		{
		case 1:
			func1(student);
			break;
		case 2:
			func2(student);
			break;
		case 3:
			func3(student);
			break;
		case 4:
			func4(student);
			break;
		case 5:
			func5(student);
			break;
		case 6:
			func6(student);
			break;
		default:
			break;
		}
	}

	return 0;
}

// 등수 정렬
void array(Student student[])
{
	Student temp;

	int k = 0;

	for (int i = 0; i < 9; i++)
	{
		for (int j = 0; j < 9; j++)
		{
			if (student[j].avr < student[j + 1].avr)
			{
				temp = student[j];
				student[j] = student[j + 1];
				student[j + 1] = temp;
			}
		}
	}
	for (int i = 0; student[i].studentNumber != NULL; i++)
		student[i].rank = i + 1;

	return;
}

// 삽입
void func1(Student student[])
{
	int i = 0, j = 0;

	for (i = 0; student[i].studentNumber != NULL; i++);
	printf("학번을 입력해주세요.\n>>");
	scanf("%d", &student[i].studentNumber);
	printf("이름을 입력해주세요.\n>>");
	scanf("%s", student[i].studentName);
	printf("국어 점수를 입력해주세요.\n>>");
	scanf("%d", &student[i].nativelang);
	printf("수학 점수를 입력해주세요.\n>>");
	scanf("%d", &student[i].math);
	printf("과학 점수를 입력해주세요.\n>>");
	scanf("%d", &student[i].science);

	student[i].sum = student[i].nativelang + student[i].math + student[i].science;
	student[i].avr = (double)student[i].sum / 3.0;

	array(student);

	printf("\n");

	return;
}

// 저장
void func2(Student student[])
{
	FILE* f;
	char fname[] = "student.txt";
	f = fopen(fname, "w");
	for (int i = 0;student[i].studentNumber != NULL;i++) 
		fprintf(f, "%d %s %d %d %d %d %lf %d\n", student[i].studentNumber,student[i].studentName,student[i].nativelang, student[i].math, student[i].science, student[i].sum, student[i].avr, student[i].rank);
	fclose(f);
	return;
}

// 출력
void func3(Student student[])
{
	int i = 0;
	printf("  학번    이름   국어  수학  과학  총합  평균  등수\n");
	for (i = 0; student[i].studentNumber != NULL; i++)
	{
		printf("%d  %s  %d    %d    %d   %d   %.1lf   %d\n", student[i].studentNumber, student[i].studentName, student[i].nativelang, student[i].math, student[i].science, student[i].sum, student[i].avr, student[i].rank);
	}
	printf("\n");

	return;
}

// 검색
void func4(Student student[])
{
	int i = 0, putnum = 0;

	printf("학번 목록: ");
	for (i = 0;student[i].studentNumber != NULL;i++)
		printf("%d ", student[i].studentNumber);
	printf("\n");

	printf("학번을 입력해주세요.\n>>");
	scanf("%d", &putnum);

	for (i = 0;student[i].studentNumber != putnum;i++);

	printf("  학번    이름   국어  수학  과학  총합  평균  등수\n");
	printf("%d  %s  %d    %d    %d   %d   %.1lf   %d\n", student[i].studentNumber, student[i].studentName, student[i].nativelang, student[i].math, student[i].science, student[i].sum, student[i].avr, student[i].rank);
	printf("\n");

	return;
}

// 로드
void func5(Student student[])
{
	FILE* f;
	char fname[] = "student.txt";
	f = fopen(fname, "r");
	for (int i = 0;!feof(f);i++)
		fscanf(f, "%d %s %d %d %d %d %lf %d\n", &student[i].studentNumber, student[i].studentName, &student[i].nativelang, &student[i].math, &student[i].science, &student[i].sum, &student[i].avr, &student[i].rank);
	fclose(f);
	return;
}

// 삭제
void func6(Student student[])
{
	int i = 0, putnum = 0;

	printf("학번 목록: ");
	for (i = 0;student[i].studentNumber != NULL;i++)
		printf("%d ", student[i].studentNumber);
	printf("\n");

	printf("학번을 입력해주세요.\n>>");
	scanf("%d", &putnum);

	for (i = 0;student[i].studentNumber != putnum;i++);

	student[i].studentNumber = NULL;
	student[i].avr = 0.0;

	array(student);

	return;
}