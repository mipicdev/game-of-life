package gol.helper;

import gol.model.Cell;

import java.util.List;

public class Printer
{
    public static void printStateOfNeighbors(int row, int column, List<Cell> neighbors)
    {
        int neighborsAlive = 0;
        int neighborsDead = 0;
        int neighborsBorder = 0;

        for (Cell neighbor : neighbors)
        {
            if (neighbor.isAlive())
            {
                neighborsAlive++;
            }
            else if (neighbor.isDead())
            {
                neighborsDead++;
            }
            else if (neighbor.isBorder())
            {
                neighborsBorder++;
            }
        }

        String message = "(" + row + "/" + column + ") alive=" + neighborsAlive + " dead=" + neighborsDead + " border="
            + neighborsBorder;
        System.out.println(message);
    }
}
