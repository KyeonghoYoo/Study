#include<stdio.h>
#include<string.h>
// 문자열에서 해당하는 문자를 지우는 함수를 구현하고 문자열과 문자를 표준 입력 받은 후 
// delchar 로 테스트해보아라

void mystrcat(char s1[], const char s2[])
{
	int i = 0, j = 0;
	for (i = 0;i < sizeof(s1) / sizeof(char);i++)
	{
		if (s1[i] == NULL)
		{
			s1[i] = ' ';
			i++;
			break;
		}
	}

	for (j = 0; s2[j] != NULL;j++)
	{
		s1[i] = s2[j];
		i++;
	}

	return;
}

void delchar(char str[], const char ch)
{
	int i = 0;

	for (i = 0;str[i] != NULL;i++)
	{
		if (str[i] == ch)
		{
			while (str[i] != NULL)
			{
					str[i] = str[i + 1];
				i++;
			}
			i = -1;
		}
	}

	return;
} // 앞의 문자열에서 뒤 문자를 삭제하는 함수

int main(void)
{
	char str[30];
	char ch;
	printf("문자열과 문자를 각각 입력하시오.:");
	gets(str); // 표준 입력 함수로 문자열 입력받음
	scanf("%c", &ch);; // 문자 입력받음
	delchar(str, ch); // delchar 로 str 문자열에서 ch 와 같은 문자들 제거
	puts(str); // str 출력
	return 0;
}