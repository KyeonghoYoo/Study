#include<stdio.h>
#include<stdlib.h>
#include<time.h>

int FindValue(int *arr, int n)
{
	int count=0,i=0;

	while(i<10)
	{
		if(*arr != n)
		{
			count++;
			i++;
		}
		else if(*arr == n)
		{
			break;
		}
		arr++;
	}
	if(count!=10)
		return count;
	else if(count == 10)
		return -1;
}

int main()
{
	int arr[10]={0}, n=0,n1=0;
	srand(time(NULL));
	printf("현재 저장된 무작위 값\n");
	for(n=0;n<10;n++)
	{
		arr[n]=rand()%((10-1)+1)+1;
		for(n1=0;n1<10;n1++)
		{
		if(n != n1)
		{
		 if(arr[n] == arr[n1])
		 {
		 arr[n]=rand()%((10-1)+1)+1;
		 n1 = -1;
		 }
		}
		}
		printf("%d ", arr[n]);
	}
	printf("\n");
	printf("찾고 싶은 값을 입력하세요: ");
	scanf("%d",&n);
	printf("%d 값은 %d번째에 위치하고 있습니다.\n",n , FindValue(arr,n));
	return;
}