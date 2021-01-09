#include<stdio.h>
#include<stdlib.h>
#include<Windows.h>
#include<time.h>

#define BOARD_HEIGHT 20
#define BOARD_WIDTH 10
#define BOARD_START_X 2
#define BOARD_START_Y 1


#define UP 72
#define DOWN 80
#define LEFT 75
#define RIGHT 77
#define SPACEBAR 32

//블럭 구조체
typedef struct __TetrisBlock
{
	int data[5][5];
	int w;
	int h;
}TetrisBlock;

// 블럭 구조체 배열
const TetrisBlock blocks[7] =
{
	{
		{ { 1,1 },
		{ 1,1 } },2,2
	},
	{
		{ { 1,1,1,1 } },4,1
	},
	{
		{ { 0,1,1 },
		{ 1,1,0 } },3,2
	},
	{
		{ { 1,1,0 },
		{ 0,1,1 } },3,2
	},
	{
		{ { 1,0,0 },
		{ 1,1,1 } },3,2
	},
	{
		{ { 0,0,1 },
		{ 1,1,1 } },3,2
	},
	{
		{ { 0,1,0 },
		{ 1,1,1 } },3,2
	}
};

int BOARD[22][12] =
{
	{ 1,1,1,1,1,1,1,1,1,1,1,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,0,0,0,0,0,0,0,0,0,0,1 },
	{ 1,1,1,1,1,1,1,1,1,1,1,1 }
};

int times = 0, delay = 1000;
int score = 0, level = 1;

void printStartScreen();
void decide_pushKey(TetrisBlock* currentBlock, TetrisBlock* nextBlock, int* x, int* y);
void gotoxy(int x, int y);
void ramoveCursor();
void makeNewBlock(TetrisBlock* currentBlock);
void resetCurrent(TetrisBlock* currentBlock);
void turnBlock(TetrisBlock* currentBlock);
void resetBoard();
void printBoard();
void printBoard2();
void printBlock(TetrisBlock* currentBlock, int absX, int absY);
void removeBlockMark(TetrisBlock* currentBlock, int absX, int absY);
int Does_BlockHitWall(TetrisBlock* currentBlock, int X, int Y);
void Does_BlockHitBottom(TetrisBlock* currentBlock, int X, int Y);
void clearline(int X, int Y);
void moveline(int i);
int printGameOver();
void quickDrop(TetrisBlock * currentBlock, TetrisBlock* nextBlock, int * X, int * Y);
void saveNextblock(TetrisBlock * currentBlock, TetrisBlock * nextBlock);


int main()
{
	ramoveCursor();
	TetrisBlock currentBlock;
	TetrisBlock nextBlock;
	resetCurrent(&currentBlock);
	resetCurrent(&nextBlock);
	srand((unsigned)time(NULL));
	rand();

	int absX = 8, absY = 0;
	int gameover = 1;

	printStartScreen();

	while (gameover)
	{
		gotoxy(30, 9);
		printf("20141747 유경호");
		gotoxy(30, 11);
		printf("이동        ← ↓ →");
		gotoxy(30, 12);
		printf("모양 바꾸기 ↑");
		gotoxy(30, 13);
		printf("블럭 낙하   SPACEBAR");
		gotoxy(30, 18);
		printf("level : %d", level);
		gotoxy(30, 20);
		printf("score : %d", score);
		printBoard();
		gotoxy(absX, absY);
		makeNewBlock(&nextBlock);
		printBlock(&nextBlock, 28, 2);
		makeNewBlock(&currentBlock);
		printBlock(&currentBlock, absX, absY);
		while (1)
		{

			while (!kbhit()) // kbhit() 참고
			{
				if ((int)times % (int)delay == (int)delay - 1) // 일정한 시간으로 동작 하는 if문 참고
				{
					removeBlockMark(&currentBlock, absX, absY);

					if (Does_BlockHitWall(&currentBlock, absX, absY + 1))
						absY++;
					else
					{
						Does_BlockHitBottom(&currentBlock, absX, absY);
						printBlock(&currentBlock, absX, absY);
						clearline(absX, absY);
						resetCurrent(&currentBlock);
						saveNextblock(&currentBlock, &nextBlock);
						absX = 8;
						absY = 0;
					}
				}
				if (!Does_BlockHitWall(&currentBlock, absX, absY))
				{
					gameover = printGameOver();
					goto A;
				}
				else
				{
					printBlock(&currentBlock, absX, absY);
					times++;
				}
			}

			if (!Does_BlockHitWall(&currentBlock, absX, absY))
			{
				if (gameover == 0)
					goto A;
				gameover = printGameOver();
				goto A;
			}
			decide_pushKey(&currentBlock, &nextBlock, &absX, &absY);
			printBlock(&currentBlock, absX, absY);
		}
	A:
		if (gameover == 1)
		{
			resetCurrent(&currentBlock);
			resetCurrent(&nextBlock);
			resetBoard();
			absX = 8;
			absY = 0;
			times = 0, delay = 1000;
			score = 0, level = 1;
			system("cls");
		}

	}

	return;
}

// 시작화면(타이틀화면) 출력
void printStartScreen()
{
	int i = 16;
	int times = 0;
	int c = 0;
	//T
	{
		gotoxy(26 - i, 8);
		printf("■■■■■");
		gotoxy(30 - i, 9);
		printf("■");
		gotoxy(30 - i, 10);
		printf("■");
		gotoxy(30 - i, 11);
		printf("■");
		gotoxy(30 - i, 12);
		printf("■");
	}

	//E
	{
		gotoxy(38 - i, 8);
		printf("■■■■");
		gotoxy(38 - i, 9);
		printf("■");
		gotoxy(38 - i, 10);
		printf("■■■■");
		gotoxy(38 - i, 11);
		printf("■");
		gotoxy(38 - i, 12);
		printf("■■■■");
	}

	//T
	{
		gotoxy(48 - i, 8);
		printf("■■■■■");
		gotoxy(52 - i, 9);
		printf("■");
		gotoxy(52 - i, 10);
		printf("■");
		gotoxy(52 - i, 11);
		printf("■");
		gotoxy(52 - i, 12);
		printf("■");
	}

	//R
	{
		gotoxy(60 - i, 8);
		printf("■■■");
		gotoxy(60 - i, 9);
		printf("■    ■");
		gotoxy(60 - i, 10);
		printf("■■■");
		gotoxy(60 - i, 11);
		printf("■  ■");
		gotoxy(60 - i, 12);
		printf("■    ■");
	}

	//I
	{
		gotoxy(70 - i, 8);
		printf("■■■");
		gotoxy(70 - i, 9);
		printf("  ■");
		gotoxy(70 - i, 10);
		printf("  ■");
		gotoxy(70 - i, 11);
		printf("  ■");
		gotoxy(70 - i, 12);
		printf("■■■");
	}

	//S
	{
		gotoxy(78 - i, 8);
		printf("  ■■■");
		gotoxy(78 - i, 9);
		printf("■");
		gotoxy(78 - i, 10);
		printf("  ■■");
		gotoxy(78 - i, 11);
		printf("      ■");
		gotoxy(78 - i, 12);
		printf("■■■");
	}


	while (!kbhit())
	{
		gotoxy(30, 17);
		printf("Press any key to start!");
		Sleep(500);
		gotoxy(30, 17);
		printf("                       ");
		Sleep(500);
	}
	c = getch();
	system("cls");
}

// 키가 눌려졌는지 판단해서 키를 인식 및 동작하는 함수 (참고)
void decide_pushKey(TetrisBlock* currentBlock, TetrisBlock* nextBlock, int* absX, int* absY)
{
	char key = getch(); // getch() 함수 참고
	int X = *absX, Y = *absY;
	TetrisBlock dumy = *currentBlock;

	switch (key)
	{
	case UP:
		turnBlock(&dumy);
		if (Does_BlockHitWall(&dumy, X, Y))
		{
			removeBlockMark(currentBlock, *absX, *absY);
			turnBlock(currentBlock);
		}
		break;
	case DOWN:
		Y++;
		if (Does_BlockHitWall(currentBlock, X, Y))
		{
			removeBlockMark(currentBlock, *absX, *absY);
			(*absY)++;
		}
		break;
	case LEFT:
		X -= 2;
		if (Does_BlockHitWall(currentBlock, X, Y))
		{
			removeBlockMark(currentBlock, *absX, *absY);
			(*absX) -= 2;
		}
		break;
	case RIGHT:
		X += 2;
		if (Does_BlockHitWall(currentBlock, X, Y))
		{
			removeBlockMark(currentBlock, *absX, *absY);
			(*absX) += 2;
		}
		break;
	case SPACEBAR:
		if (Does_BlockHitWall(currentBlock, X, Y))
		{
			removeBlockMark(currentBlock, *absX, *absY);
			quickDrop(currentBlock, nextBlock, absX, absY);
		}
		break;
	default:
		break;
	}
	gotoxy(*absX, *absY);

	return;
}

// 커서 위치를 설정하는 함수 (참고)
void gotoxy(int x, int y)
{
	COORD pos = { x, y };
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);
}

// 커서를 지우는 함수 (참고)
void ramoveCursor()
{
	CONSOLE_CURSOR_INFO curinfo;
	GetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &curinfo);
	curinfo.bVisible = 0;
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &curinfo);
}

// 새로운 블럭을 생성하는 함수
void makeNewBlock(TetrisBlock* currentBlock)
{
	int i, j;
	int a = rand() % 7;
	TetrisBlock newblock = blocks[a];
	if (a > 1)
		for (i = 0; i < newblock.h; i++)
			for (j = 0; j < newblock.w; j++)
				currentBlock->data[1 + i][1 + j] = newblock.data[i][j];
	else
	{
		if (a == 0)
			for (i = 0; i < newblock.h; i++)
				for (j = 0; j < newblock.w; j++)
					currentBlock->data[1 + i][2 + j] = newblock.data[i][j];
		if (a == 1)
			for (i = 0; i < newblock.h; i++)
				for (j = 0; j < newblock.w; j++)
					currentBlock->data[2 + i][1 + j] = newblock.data[i][j];
	}
	return;
}

// 블럭 배열에 블럭이 없는 초기 상태로 되돌림
void resetCurrent(TetrisBlock* currentBlock)
{
	int i, j;
	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			currentBlock->data[i][j] = 0;
		}
	}
}

// 블럭을 돌리는 함수
void turnBlock(TetrisBlock* currentBlock)
{
	int i, j;
	TetrisBlock a = *currentBlock;
	TetrisBlock b = *currentBlock;

	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			a.data[j][4 - i] = b.data[i][j];
		}
	}

	a.w = b.h;
	a.h = b.w;
	*currentBlock = a;
	return;
}

//보드판을 리셋해주는 함수
void resetBoard()
{
	int i, j;
	for (i = 0; i < 22; i++)
	{
		for (j = 0; j < 12; j++)
		{
			if (i == 0 || i == 21)
				BOARD[i][j] = 1;
			else if (j == 0 || j == 11)
				BOARD[i][j] = 1;
			else
				BOARD[i][j] = 0;
		}
	}
}

// 보드판 그리는 함수
void printBoard()
{
	int i, j;
	for (i = 0; i < 22; i++)
	{
		gotoxy(BOARD_START_X, BOARD_START_Y + i);
		for (j = 0; j < 12; j++)
		{
			if (BOARD[i][j] == 1)
				printf("□");
			else if (BOARD[i][j] == 0)
				printf("  ");
		}
	}

	for (i = 0; i < 7; i++)
	{
		gotoxy(26, 1 + i);
		for (j = 0; j < 7; j++)
		{
			if ((i == 0 || i == 6) || (j == 0 || j == 6))
				printf("□");
			else
				printf("  ");
		}
	}

	return;
}

// 한 줄 지우고나서 보드판을 다시 그려주는 함수
void printBoard2()
{
	int i, j;
	for (i = 1; i < 21; i++)
	{
		gotoxy(BOARD_START_X + 2, BOARD_START_Y + i);
		for (j = 1; j < 11; j++)
		{
			if (BOARD[i][j] == 1)
				printf("■");
			else if (BOARD[i][j] == 0)
				printf("  ");
		}
	}

	return;
}

// 블럭을 출력하는 함수
void printBlock(TetrisBlock* currentBlock, int absX, int absY)
{
	int X, Y;
	X = absX;
	Y = absY;
	int i = 0, j = 0;
	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			if (currentBlock->data[i][j] == 1)
			{

				if (Y < 2);
				else
					printf("■");
			}
			else
			{
				X += 2;
				gotoxy(X, Y);
			}
		}
		Y += 1;
		X = absX;
		gotoxy(X, Y);
	}
	gotoxy(absX, absY);
}

// 블럭이 지나갈 때 남는 자국 지우기
void removeBlockMark(TetrisBlock* currentBlock, int absX, int absY)
{
	int X, Y;
	X = absX;
	Y = absY;
	int i = 0, j = 0;
	for (i = 0; i < 5; i++)
	{
		for (j = 0; j < 5; j++)
		{
			if (currentBlock->data[i][j] == 1)
			{
				if (Y < 2);
				else
					printf("  ");
			}
			else
			{
				X += 2;
				gotoxy(X, Y);
			}

		}
		Y += 1;
		X = absX;
		gotoxy(X, Y);
	}
}

// 블럭이 1에 닿으면 못가게(FALSE 반환) 하는 함수
int Does_BlockHitWall(TetrisBlock* currentBlock, int X, int Y)
{
	int i = 0, j = 0;
	int x = X, y = Y;
	for (i = 0; i < 5; i++, y++)
	{
		for (j = 0; j < 5; j++, x += 2)
		{
			if (currentBlock->data[i][j] == 1)
			{
				if ((x < 4 || x > 22) || (y > 1 && (BOARD[y - BOARD_START_Y][((x - BOARD_START_X) / 2)] == 1)))
				{
					return FALSE;
				}
			}
		}
		x = X;
	}
	return TRUE;
}

// 블럭이 딜레이 후 이동하는데 1에 닿을시 보드 배열에 블럭모양으로 1을 저장
void Does_BlockHitBottom(TetrisBlock* currentBlock, int X, int Y)
{
	int i = 0, j = 0;
	int x = X, y = Y;
	for (i = 0; i < 5; i++, y++, x = X)
	{
		for (j = 0; j < 5; j++, x += 2)
		{
			if (currentBlock->data[i][j] == 1)
			{
				BOARD[y - BOARD_START_Y][((x - BOARD_START_X) / 2)] = 1;
			}
		}
	}

	return;
}

// 한 줄이 완성된지 보드를 검열한 후 줄이 완성됬으면 없애는 함수
void clearline(int X, int Y)
{
	int i = 0, j = 0;
	int x = X, y = Y;
	for (i = 20; i > 0; i--)
	{
		for (j = 1; BOARD[i][j] != 0; j++)
		{
			if (j == 10)
			{
				moveline(i);
				printBoard2();
				i++;
			}
		}
	}

}

// 위에 줄을 밑으로 땡겨주는 함수
void moveline(int i)
{
	int j = 0, k = 0;

	for (j = i - 1; j > 0; j--)
	{
		for (k = 1; k < 11; k++)
		{
			BOARD[j + 1][k] = BOARD[j][k];
		}
	}
	score += 10;
	gotoxy(38, 20);
	printf("%d", score);
	if (score % 50 == 0)
	{
		gotoxy(38, 18);
		printf("%d", ++level);
		delay -= (delay / 10);
	}

}

// 겜오버 출력
int printGameOver()
{
	system("cls");
	char c = 0;
	gotoxy(35, 11);
	printf("Game Over!");
	gotoxy(34, 12);
	printf("Your score:%d", score);
	gotoxy(34, 13);
	printf("Restart? Y/N");
	gotoxy(26, 23);

	while ((c = getch()) != 'Y' && c != 'y' && c != 'n' && c != 'N');

	if (c == 'Y' || c == 'y')
	{
		return 1;
	}
	else
	{
		return 0;
	}
}

// 스페이스바 누르면 밑으로 떨어뜨려주는 함수
void quickDrop(TetrisBlock* currentBlock, TetrisBlock* nextBlock, int *X, int *Y)
{
	for (; Does_BlockHitWall(currentBlock, *X, (*Y) + 1); (*Y)++);
	Does_BlockHitBottom(currentBlock, *X, *Y);
	printBlock(currentBlock, *X, *Y);
	clearline(*X, *Y);
	resetCurrent(currentBlock);
	saveNextblock(currentBlock, nextBlock);
	*X = 8;
	*Y = 0;
}

//다음블럭을 현재블럭 구조체에 옮기고 새로운 다음블럭을 저장
void saveNextblock(TetrisBlock* currentBlock, TetrisBlock* nextBlock)
{
	*currentBlock = *nextBlock;
	removeBlockMark(nextBlock, 28, 2);
	resetCurrent(nextBlock);
	makeNewBlock(nextBlock);
	printBlock(nextBlock, 28, 2);
}