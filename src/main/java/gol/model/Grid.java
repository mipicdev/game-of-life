package gol.model;

import java.util.ArrayList;
import java.util.List;

class Grid
{
    private final int size;
    private final double probability;
    private Cell[][] grid;

    Grid(int size, double probability)
    {
        this.size = size;
        this.probability = probability;

        createGrid();
    }

    private void createGrid()
    {
        grid = new Cell[size][size];

        createBorder();
        createCells();
    }

    private void createBorder()
    {
        for (int column = 0; column < size; column++)
        {
            grid[0][column] = new Cell(State.BORDER);
        }

        for (int row = 0; row < size; row++)
        {
            grid[row][0] = new Cell(State.BORDER);
        }

        for (int row = 0; row < size; row++)
        {
            grid[row][size - 1] = new Cell(State.BORDER);
        }

        for (int column = 0; column < size; column++)
        {
            grid[size - 1][column] = new Cell(State.BORDER);
        }
    }

    private void createCells()
    {
        for (int row = 1; row < (size - 1); row++)
        {
            for (int column = 1; column < (size - 1); column++)
            {
                if (Math.random() < probability)
                {
                    grid[row][column] = new Cell(State.ALIVE);
                }
                else
                {
                    grid[row][column] = new Cell(State.DEAD);
                }
            }
        }
    }

    int getSize()
    {
        return size - 1;
    }

    Cell getCell(int row, int column)
    {
        return grid[row][column];
    }

    List<Cell> getNeighbors(int row, int column)
    {
        List<Cell> neighbors = new ArrayList<>();

        neighbors.add(grid[row - 1][column - 1]);
        neighbors.add(grid[row - 1][column]);
        neighbors.add(grid[row - 1][column + 1]);

        neighbors.add(grid[row][column - 1]);
        neighbors.add(grid[row][column + 1]);

        neighbors.add(grid[row + 1][column - 1]);
        neighbors.add(grid[row + 1][column]);
        neighbors.add(grid[row + 1][column + 1]);

        return neighbors;
    }

    @Override
    public String toString()
    {
        String result = "";

        for (int row = 0; row < size; row++)
        {
            result += "[ ";

            for (int column = 0; column < size; column++)
            {
                result += grid[row][column] + " ";
            }

            result += "]\n";
        }

        return result;
    }
}
