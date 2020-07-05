class solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        int temp = 0; 
        char[][] board_grid = new char[m][n];
        int[][] board_int = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board_grid[i][j] = board[i].charAt(j);
            }
        }
        boolean check = true;
        int i = 0;
        int j = 0;
        while (check) {
            temp = answer;
            for (i = 0; i < m - 1; i++) {
                for (j = 0; j < n - 1; j++) {
                    if (board_grid[i][j] == board_grid[i][j + 1]
                            && board_grid[i][j] != ' ') {
                        if (board_grid[i][j] == board_grid[i + 1][j]) {
                            if (board_grid[i][j] == board_grid[i + 1][j + 1]) {
                                for (int k = i; k < i + 2; k++) {
                                    for (int l = j; l < j + 2; l++) {
                                        if (board_int[k][l] != 1) {
                                            board_int[k][l] = 1;
                                            answer++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (i = 0; i < n; i++) {
                for (j = 0; j < m - 1; j++) {
                    if (board_grid[j][i] != ' ') {
                        if (board_int[j + 1][i] == 1) {
                            int tmp = board_int[j + 1][i];
                            board_int[j + 1][i] = board_int[j][i];
                            board_int[j][i] = tmp;
                            board_grid[j + 1][i] = board_grid[j][i];
                            board_grid[j][i] = ' ';
                            j = -1;
                        }
                    }
                }
            }
            if(temp == answer) {
                check = false;
            }
        }
        return answer;
    }
}