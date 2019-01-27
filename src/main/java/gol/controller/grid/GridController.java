package gol.controller.grid;

import gol.model.Cell;
import gol.model.Simulation;
import gol.view.CellView;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class GridController implements Initializable
{
    @FXML
    private GridPane gridPane;

    private IntegerProperty alivePopulation = new SimpleIntegerProperty();
    private IntegerProperty deadPopulation = new SimpleIntegerProperty();

    private int gridSize;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void initialize(int gridSize, Color alive, Color dead)
    {
        this.gridSize = gridSize;

        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();

        for (int row = 0; row < gridSize; row++)
        {
            for (int column = 0; column < gridSize; column++)
            {
                CellView cellView = new CellView(alive, dead);
                gridPane.add(cellView, column, row);
            }
        }
    }

    public void update(Simulation simulation)
    {
        int alivePopulation = 0;
        int deadPopulation = 0;

        for (int row = 0; row < gridSize; row++)
        {
            for (int column = 0; column < gridSize; column++)
            {
                int cellViewIndex = row * gridSize + column;
                CellView cellView = (CellView) gridPane.getChildren().get(cellViewIndex);
                Cell cell = simulation.getCell(row, column);

                if (cell.isAlive())
                {
                    cellView.turnAlive();
                    alivePopulation++;
                }
                else if (cell.isDead())
                {
                    cellView.turnDead();
                    deadPopulation++;
                }
            }
        }

        this.alivePopulation.setValue(alivePopulation);
        this.deadPopulation.setValue(deadPopulation);
        //System.out.println(simulation.toString());
    }

    public IntegerProperty alivePopulationProperty()
    {
        return alivePopulation;
    }

    public IntegerProperty deadPopulationProperty()
    {
        return deadPopulation;
    }
}
