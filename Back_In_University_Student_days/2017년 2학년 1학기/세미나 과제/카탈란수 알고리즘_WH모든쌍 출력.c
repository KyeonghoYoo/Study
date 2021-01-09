#include<stdio.h>

int count = 0;

void print(int arr[], int arr_size) 
{
	int i = 0;
	for (i = 0; i < arr_size; i++)
	{
		if (arr[i] == 0)
			printf("W");
		else if (arr[i] == 1)
			printf("H");
	}
	printf("\n");
}

void medicine(int to_W, int to_H, int arr[], int arr_size)
{
	if (to_W == 0 && to_H == 0)
	{
		print(arr, arr_size);
		count++;
		return;
	}
	if (to_W > 0)
	{
		arr[arr_size] = 0;
		medicine(to_W - 1, to_H + 1, arr, arr_size + 1);
	}
	if (to_H > 0)
	{
		arr[arr_size] = 1;
		medicine(to_W, to_H - 1, arr, arr_size + 1);
	}
}

int main()
{
	int arr[100], n;
	scanf("%d", &n);

	medicine(n, 0, arr, 0);

	printf("%d\n", count);

	return 0;
}