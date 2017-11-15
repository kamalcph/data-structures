package in.co.nmsworks.nms.system.server;

public class SudokuSolver {

    private static int N = 9;
    private int[][] grid;

    public SudokuSolver(int[][] grid) {
        this.grid = grid;
    }

    static class Cell {
        int row;
        int col;

        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    private Cell getNextCell(Cell cur) {
        int row = cur.row;
        int col = cur.col;

        col++;
        if (col > 8) {
            col = 0;
            row++;
        }

        if (row > 8) {
            return null;
        }

        return new Cell(row, col);
    }

    private boolean isValid(Cell cell, int val) {
        if (grid[cell.row][cell.col] != 0) {
            throw new RuntimeException("Cannot fill an Full cell");
        }

        for (int i=0; i<N; i++) {
            if (grid[cell.row][i] == val)
                return false;

            if (grid[i][cell.col] == val)
                return false;
        }

        // (3,3)
        // x1 = 3, y1 = 3, x2 = 5, y2 = 5
        // (3,3) (3,4) (3,5)
        // (4,3) (4,4) (4,5)
        // (5,3) (5,4) (5,5)

        int x1 = 3 * (cell.row / 3);
        int y1 = 3 * (cell.col / 3);
        int x2 = x1 + 2;
        int y2 = y1 + 2;

        for (int x=x1; x<=x2; x++) {
            for (int y=y1; y<=y2; y++) {
                if (grid[x][y] == val)
                    return false;
            }
        }

        return true;
    }

    public boolean solve(Cell cur) {
        if (cur == null)
            return true;

        if (grid[cur.row][cur.col] != 0) {
            return solve(getNextCell(cur));
        }

        // apply the values in the cell
        for (int i=1; i<=N; i++) {
            final boolean valid = isValid(cur, i);

            if (!valid)
                continue;

            grid[cur.row][cur.col] = i;
            boolean isSolved = solve(getNextCell(cur));
            if (isSolved)
                return true;
            else {
                grid[cur.row][cur.col] = 0;
            }
        }
        return false;
    }

    public void printGrid() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0,7,5,0,9,0,0,0,6},
                {0,2,3,0,8,0,0,4,0},
                {8,0,0,0,0,3,0,0,1},
                {5,0,0,7,0,2,0,0,0},
                {0,4,0,8,0,6,0,2,0},
                {0,0,0,9,0,1,0,0,3},
                {9,0,0,4,0,0,0,0,7},
                {0,6,0,0,7,0,5,8,0},
                {7,0,0,0,1,0,3,9,0}
        };

        int[][] grid2  = {
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };

        SudokuSolver solver = new SudokuSolver(grid2);
        long startMs = System.currentTimeMillis();
        boolean solved = solver.solve(new Cell(0, 0));
        System.out.println("Is solved : " + solved + ", time taken : " + (System.currentTimeMillis() - startMs) + " ms");

        if (solved) {
            System.out.println("Solution \n");
            solver.printGrid();
        }

    }
}
