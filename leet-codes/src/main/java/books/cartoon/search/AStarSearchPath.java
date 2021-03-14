package books.cartoon.search;

import java.util.ArrayList;
import java.util.List;

public class AStarSearchPath {

    public static final int[][] MAZE = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0}
    };

    static class Grid {
        public int x;
        public int y;

        public int f; //从起点到当前格子，从当前格子到目标的总步数 f=g+h
        public int g; //从起点走到当前格子的步数
        public int h; //不考虑障碍，当前格子走到目标的距离
        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            this.parent = parent;
            if (parent != null) {
                this.g = parent.g + 1;
            } else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }
    }

    /**
     * A*寻路算法
     *
     * @param start
     * @param end
     * @return
     */
    public static Grid aStarSearch(Grid start, Grid end) {
        //记录可以走的
        ArrayList<Grid> openList = new ArrayList<>();
        //记录已经走过的
        ArrayList<Grid> closeList = new ArrayList<>();

        openList.add(start);

        while (!openList.isEmpty()) {

            Grid current = findMinGrid(openList);

            openList.remove(current);

            closeList.add(current);

            List<Grid> neighbors = findNeighbors(current, openList, closeList);

            for (Grid grid : neighbors) {
                if (!openList.contains(grid)) {
                    grid.initGrid(current, end);
                    openList.add(grid);
                }
            }

            //终点在 openList 中，直接返回
            for (Grid grid : openList) {
                if (grid.x == end.x && grid.y == end.y) {
                    return grid;
                }
            }
        }

        //openList用尽,目标不可达
        return null;
    }

    private static Grid findMinGrid(ArrayList<Grid> openList) {
        Grid tmp = openList.get(0);
        for (Grid g : openList) {
            if (g.f < tmp.f) {
                tmp = g;
            }
        }
        return tmp;
    }

    private static ArrayList<Grid> findNeighbors(Grid grid, List<Grid> openList, List<Grid> closeList) {
        ArrayList<Grid> grids = new ArrayList<>();

        //acm.search up
        if (isValidGrid(grid.x, grid.y + 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y + 1));
        }

        //acm.search down
        if (isValidGrid(grid.x, grid.y - 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y - 1));
        }

        //acm.search left
        if (isValidGrid(grid.x - 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x - 1, grid.y));
        }

        //acm.search right
        if (isValidGrid(grid.x + 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x + 1, grid.y));
        }

        return grids;
    }

    private static boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeList) {
        //是否超过边界
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        //是否有障碍物
        if (MAZE[x][y] == 1) {
            return false;
        }

        //是否在openList中
        if (containGrid(openList, x, y)) {
            return false;
        }

        //是否在closeList中
        if (containGrid(closeList, x, y)) {
            return false;
        }

        return true;
    }

    private static boolean containGrid(List<Grid> grids, int x, int y) {
        for (Grid grid : grids) {
            if (grid.x == x && grid.y == y) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Grid start = new Grid(2, 1);
        Grid end = new Grid(2, 5);
        Grid result = aStarSearch(start, end);
        ArrayList<Grid> path = new ArrayList<>();
        while (result != null) {
            path.add(new Grid(result.x, result.y));
            result = result.parent;
        }

        for (int i = 0; i < MAZE.length; i++) {
            for (int j = 0; j < MAZE[0].length; j++) {
                if(containGrid(path, i, j)){
                    System.out.print("*, ");
                } else {
                    System.out.print(MAZE[i][j] + ", ");
                }
            }
            System.out.println();
        }
    }
}
