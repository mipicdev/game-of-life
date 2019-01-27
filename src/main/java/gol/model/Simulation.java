package gol.model;

import java.util.List;

public class Simulation
{
    private final Grid grid;
    private int currentGeneration;

    public Simulation(int gridSize, double probability)
    {
        grid = new Grid(gridSize, probability);
        currentGeneration = 1;
    }

    public Cell getCell(int row, int column)
    {
        return grid.getCell(row, column);
    }

    public void calculateNextGeneration()
    {
        currentGeneration++;

        determineNextGeneration();
        updateGridToNextGeneration();
    }

    private void determineNextGeneration()
    {
        for (int row = 1; row < (grid.getSize()); row++)
        {
            for (int column = 1; column < (grid.getSize()); column++)
            {
                Cell currentCell = grid.getCell(row, column);
                int neighborsAlive = calculateNeighborsAlive(row, column);

                determineNextStateOfNeighbors(currentCell, neighborsAlive);
            }
        }
    }

    private void updateGridToNextGeneration()
    {
        for (int row = 1; row < (grid.getSize()); row++)
        {
            for (int column = 1; column < (grid.getSize()); column++)
            {
                Cell currentCell = grid.getCell(row, column);
                currentCell.updateCurrentState();
            }
        }
    }

    private int calculateNeighborsAlive(int row, int column)
    {
        List<Cell> neighbors = grid.getNeighbors(row, column);
        int neighborsAlive = 0;

        for (Cell neighbor : neighbors)
        {
            if (neighbor.isAlive())
            {
                neighborsAlive++;
            }
        }

        //Printer.printStateOfNeighbors(row, column, neighbors);
        return neighborsAlive;
    }

    private void determineNextStateOfNeighbors(Cell currentCell, int neighborsAlive)
    {
        if (currentCell.isAlive() && (isUnderpopulation(neighborsAlive) || isOverpopulation(neighborsAlive)))
        {
            currentCell.turnDead();
        }
        else if (currentCell.isDead() && isReproduction(neighborsAlive))
        {
            currentCell.turnAlive();
        }
        else
        {
            currentCell.staySameState();
        }
    }

    private boolean isUnderpopulation(int neighborsAlive)
    {
        return neighborsAlive < 2;
    }

    private boolean isOverpopulation(int neighborsAlive)
    {
        return neighborsAlive > 3;
    }

    private boolean isReproduction(int neighborsAlive)
    {
        return neighborsAlive == 3;
    }

    public int getCurrentGeneration()
    {
        return currentGeneration;
    }

    @Override
    public String toString()
    {
        return "Generation " + currentGeneration + "\n" + grid;
    }
}
