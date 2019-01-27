package gol.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CellView extends Rectangle
{
    private Color alive;
    private Color dead;

    public CellView(Color alive, Color dead)
    {
        this.alive = alive;
        this.dead = dead;

        fillProperty().setValue(Constants.BORDER_COLOR);
        setWidth(6);
        setHeight(6);
    }

    public void turnAlive()
    {
        fillProperty().setValue(alive);
    }

    public void turnDead()
    {
        fillProperty().setValue(dead);
    }
}
