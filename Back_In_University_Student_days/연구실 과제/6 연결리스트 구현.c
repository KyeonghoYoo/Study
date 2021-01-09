#include<stdio.h>
#include<stdlib.h>

typedef struct __node {
	struct __node* previous;
	int data;
	struct __node* next;
}node;

typedef struct __list {
	node* head;
	node* tail;
	int size;
}List;

node* newNode(int data)
{
	node* newNode = (node*)malloc(sizeof(node));
	newNode->data = data;
	newNode->previous = NULL;
	newNode->next = NULL;

	return newNode;
} // 새로운 노드를 만드는 함수

void callMenu();
void makeNewNode_Head(List* list); // 헤드 앞에 노드를 만드는 함수
void makeNewNode_Tail(List* list); // 테일 뒤에 노드를 만드는 함수
void makeNewNode_index(List* list); // 원하는 위치에 노드를 만드는 함수
void deleteNode(List* list); // 노드를 삭제하는 함수
void printfList(List* list); // 리스트를 출력하는 함수

int main()
{
	List list;
	list.head = NULL;
	list.tail = NULL;
	list.size = 0;
	int selectNum = 0;
	while (selectNum != 6)
	{
		callMenu();
		printf("번호를 입력하세요.\n>>");
		scanf("%d", &selectNum);
		switch (selectNum)
		{
			case 1:
				makeNewNode_Head(&list);
				break;
			case 2:
				makeNewNode_Tail(&list);
				break;
			case 3:
				makeNewNode_index(&list);
				break;
			case 4:
				deleteNode(&list);
				break;
			case 5:
				printfList(&list);
				break;
			default:
				break;
		}
	}
	return 0;
}

void callMenu()
{
	printf("====================\n");
	printf("1.헤드 앞에 새로운 노드 만들기\n");
	printf("2.테일 뒤에 새로운 노드 만들기\n");
	printf("3.원하는 인덱스에 노드를 추가하기\n");
	printf("4.원하는 인덱스의 노드를 삭제하기\n");
	printf("5.리스트 전체 출력하기\n");
	printf("6. 종료\n");
	printf("====================\n");
	return;
}

void makeNewNode_Head(List* list)
{
	int data = 0;

	printf("데이터를 입력하시오.\n>>");
	scanf("%d", &data);
	node* node = newNode(data);

	if (list->head == NULL && list->tail == NULL)
	{
		list->head = node;
		list->tail = node;
		(list->size)++;
	}
	else
	{
		node->next = list->head;
		list->head->previous = node;
		list->head = node;
		list->head->previous = list->tail;
		(list->size)++;
	}
	
	return;
}

void makeNewNode_Tail(List* list)
{
	int data = 0;

	printf("데이터를 입력하시오.\n>>");
	scanf("%d", &data);
	node* node = newNode(data);

	if (list->head == NULL && list->tail == NULL)
	{
		list->head = node;
		list->tail = node;
		(list->size)++;
	}
	else
	{
		node->previous = list->tail;
		list->tail->next = node;
		list->tail = node;
		list->tail->next = list->head;
		(list->size)++;
	}

	return;
}

void makeNewNode_index(List* list)
{
	int data = 0;
	int index = 0;
	node* dummyhead = list->head;
	printf("데이터를 입력하시오.\n>>");
	scanf("%d", &data);
	printf("원하는 위치를 입력하시오.(리스트의 크기:%d)\n>>", list->size);
	scanf("%d", &index);
	node* node = newNode(data);
	if (list->head == NULL && list->tail == NULL)
	{
		list->head = node;
		list->tail = node;
		(list->size)++;
	}
	else
	{
		if (index == 1)
		{
			node->next = list->head;
			list->head->previous = node;
			list->head = node;
			list->head->previous = list->tail;
		}
		else if (index == list->size)
		{
			node->previous = list->tail;
			list->tail->next = node;
			list->tail = node;
			list->tail->next = list->head;
		}
		else
		{
			for (int i = 1;i < index;i++)
				dummyhead = dummyhead->next;
			dummyhead->previous->next = node;
			node->previous = dummyhead->previous;
			node->next = dummyhead;
			dummyhead->previous = node;
		}
		(list->size)++;
	}

	return;
}

void deleteNode(List* list)
{
	int index = 0;
	node* dummyhead = list->head;
	if (list->size == 0)
		return;
	else if (list->size == 1)
	{
		free(dummyhead);
		(list->size)--;
		return;
	}
	printf("원하는 위치를 입력하시오.(리스트의 크기:%d)\n>>", list->size);
	scanf("%d", &index);
	for (int i = 1;i < index;i++)
		dummyhead = dummyhead->next;
	dummyhead->previous->next = dummyhead->next;
	dummyhead->next->previous = dummyhead->previous;
	if (index == 1)
		list->head = dummyhead->next;
	else if (index == list->size)
		list->tail = dummyhead->previous;
	free(dummyhead);
	(list->size)--;

	return;
}

void printfList(List* list) 
{
	int i = 0;
	node* dummyhead = list->head;

	for (i = 0;i < list->size;i++)
	{
		printf("%d번째 노드의 데이터:%d\n", i + 1, dummyhead->data);
		dummyhead = dummyhead->next;
	}
	printf("\n");

	return;
}