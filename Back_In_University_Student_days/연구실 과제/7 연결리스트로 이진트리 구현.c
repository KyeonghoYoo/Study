#include<stdio.h>
#include<stdlib.h>

typedef struct __treenode {
	struct __treenode* left;
	int data;
	struct __treenode* right;
}TreeNode; // 트리노드 구조체

TreeNode* makenewTreeNode(int data)
{
	TreeNode* treenode = (TreeNode*)malloc(sizeof(TreeNode));
	treenode->data = data;
	treenode->left = NULL;
	treenode->right = NULL;

	return treenode;
} // 새로운 노드를 생성하는 함수


void callMenu(); // 선택 메뉴를 출력
int getNewData(); // 데이터를 입력받는 함수
void setnewTreeNode(TreeNode** rootNode, int data); // 새로운 노드를 트리에 설정해줌
TreeNode* searchNode(TreeNode** rootNode, int data); // 데이터에 해당하는 노드를 찾아서 반환해줌
TreeNode* searchRightsubEndofleft(TreeNode* Ptr); // left 서브 트리를 right 서브 트리의 제일 left노드의 left에 붙이는 함수

void func1(TreeNode** rootNode); // 데이터를 입력받아 새로운 노드를 트리에 설정해줌
void func2(TreeNode** rootNode); // 후위 순회로 트리를 출력
void func3(TreeNode** rootNode); // 삭제 함수

int main()
{
	TreeNode* rootNode;
	rootNode = NULL;
	int selectNum = 0;

	while (1)
	{
		callMenu();
		printf("번호를 입력하세요.\n>>");
		scanf("%d", &selectNum);
		switch (selectNum)
		{
			case 1:
				func1(&rootNode);
				break;
			case 2:
				func2(&rootNode);
				printf("\n");
				break;
			case 3:
				func3(&rootNode);
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
	printf("1.새로운 데이터 입력\n");
	printf("2.모든 데이터 출력\n");
	printf("3.선택 데이터 삭제\n");
	printf("====================\n");

	return;
}

int getNewData()
{
	int data = 0;
	printf("입력 할 정수를 입력하세요.\n>>");
	scanf("%d", &data);

	return data;
}

void setnewTreeNode(TreeNode** rootNode, int data)
{
	if (*rootNode == NULL) // 비어있을 경우 그 자리에 새로운 노드를 트리에 세팅해줌
	{
		(*rootNode) = makenewTreeNode(data);
		return;
	}

	if ((*rootNode)->data >= data) // 데이터가 작을 경우
	{
		setnewTreeNode(&((*rootNode)->left), data);
	}
	else if ((*rootNode)->data < data) // 데이터가 클 경우
	{
		setnewTreeNode(&((*rootNode)->right), data);
	}

	return;
}

TreeNode* searchNode(TreeNode** rootNode, int data)
{
	TreeNode* Ptr = *rootNode;
	TreeNode* temp;
	while (Ptr->data != data)
	{
		if (Ptr->data >= data)
		{
			if (Ptr->left->data == data)
				temp = Ptr;
			Ptr = Ptr->left;
			if (Ptr->data == data)
				temp->left = Ptr->right;
		}
		else
		{
			if (Ptr->right->data == data)
				temp = Ptr;
			Ptr = Ptr->right;
			if (Ptr->data == data)
				temp->right = Ptr->right;
		}
	}
	
	return Ptr;
}

TreeNode* searchRightsubEndofleft(TreeNode* Ptr)
{
	if (Ptr->left == NULL)
		return Ptr;
	else
		return searchRightsubEndofleft(Ptr->left);
}

void func1(TreeNode** rootNode)
{
	int data = getNewData();

	if (rootNode == NULL)
	{
		(*rootNode) = makenewTreeNode(data);
	}
	else
	{
		setnewTreeNode(rootNode, data);
	}

	return;
}

void func2(TreeNode** rootNode)
{
	if (*rootNode == NULL)
		return;
	func2(&((*rootNode)->left));
	func2(&((*rootNode)->right));
	printf("%d ", (*rootNode)->data);

	return;
}

void func3(TreeNode** rootNode)
{
	TreeNode* Ptr;
	int delData = 0;
	printf("삭제 하실 데이터를 입력해주세요.\n>>");
	scanf("%d", &delData);
	Ptr = searchNode(&(*rootNode), delData);
	if (Ptr->left == NULL && Ptr->right == NULL)
	{
		if (Ptr == *rootNode)
			*rootNode = NULL;
		free(Ptr);
	}
	else
	{
		searchRightsubEndofleft(Ptr->right)->left = Ptr->left;
		
		free(Ptr);
	}
	return;
}