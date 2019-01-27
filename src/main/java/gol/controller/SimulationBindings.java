package gol.controller;

import gol.controller.grid.GridController;
import gol.controller.information.InformationController;
import gol.controller.input.InputController;
import javafx.beans.binding.Bindings;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimulationBindings
{
    public static void forInput(InformationController information, InputController input)
    {
        information.totalGenerationsLabel.textProperty().bind(input.numberOfGenerationsSpinner.valueProperty().asString());

        information.totalPopulationLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            int universeSize = input.universeSizeSpinner.valueProperty().getValue();
            return String.valueOf(universeSize * universeSize);
        }, input.universeSizeSpinner.valueProperty()));

        information.totalTimeLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            double speed = input.speedChoiceBox.valueProperty().getValue().getDuration().toSeconds();
            int numberOfGenerations = input.numberOfGenerationsSpinner.valueProperty().getValue();
            return (speed * numberOfGenerations) + " s";
        }, input.speedChoiceBox.valueProperty(), input.numberOfGenerationsSpinner.valueProperty()));

        information.currentTimeLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            int currentGeneration = Integer.valueOf(information.currentGenerationLabel.textProperty().getValue());
            double speed = input.speedChoiceBox.valueProperty().getValue().getDuration().toSeconds();
            double result = speed * currentGeneration;
            return BigDecimal.valueOf(result).setScale(2, RoundingMode.CEILING) + " s";
        }, information.currentGenerationLabel.textProperty()));

        information.remainingTimeLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            int remainingGenerations = Integer.valueOf(information.remainingGenerationsLabel.textProperty().getValue());
            double speed = input.speedChoiceBox.valueProperty().getValue().getDuration().toSeconds();
            double result = speed * remainingGenerations;
            return BigDecimal.valueOf(result).setScale(2, RoundingMode.CEILING) + " s";
        }, information.remainingGenerationsLabel.textProperty()));
    }

    public static void forGrid(InformationController information, GridController grid)
    {
        information.alivePopulationLabel.textProperty().bind(grid.alivePopulationProperty().asString());

        information.alivePopulationPercentLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            double alivePopulation = Double.valueOf(information.alivePopulationLabel.textProperty().getValue());
            double totalPopulation = Double.valueOf(information.totalPopulationLabel.textProperty().getValue());
            double result = alivePopulation / totalPopulation * 100;
            return BigDecimal.valueOf(result).setScale(2, RoundingMode.CEILING) + " %";
        }, information.alivePopulationLabel.textProperty()));

        information.deadPopulationLabel.textProperty().bind(grid.deadPopulationProperty().asString());

        information.deadPopulationPercentLabel.textProperty().bind(Bindings.createStringBinding(() ->
        {
            double deadPopulation = Double.valueOf(information.deadPopulationLabel.textProperty().getValue());
            double totalPopulation = Double.valueOf(information.totalPopulationLabel.textProperty().getValue());
            double result = deadPopulation / totalPopulation * 100;
            return BigDecimal.valueOf(result).setScale(2, RoundingMode.CEILING) + " %";
        }, information.deadPopulationLabel.textProperty()));
    }
}
