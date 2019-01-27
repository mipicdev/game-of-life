package gol.controller;

import gol.controller.grid.GridController;
import gol.controller.information.InformationController;
import gol.controller.input.InputController;
import gol.model.Simulation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    @FXML
    private InputController inputController;

    @FXML
    private InformationController informationController;

    @FXML
    private GridController gridController;

    private Simulation simulation;
    private Timeline timeline;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        inputController.setMainController(this);

        SimulationBindings.forInput(informationController, inputController);
        SimulationBindings.forGrid(informationController, gridController);
    }

    public void startSimulation()
    {
        int gridSize = inputController.universeSizeSpinner.getValue() + 2;
        double probability = inputController.probabilitySpinner.getValue() / 100.0;
        simulation = new Simulation(gridSize, probability);

        Color alive = inputController.cellAliveColorPicker.getValue();
        Color dead = inputController.cellDeadColorPicker.getValue();
        gridController.initialize(gridSize, alive, dead);

        gridController.update(simulation);
        informationController.update(simulation.getCurrentGeneration());
        createTimeline();
    }

    private void createTimeline()
    {
        int numberOfGenerations = inputController.numberOfGenerationsSpinner.valueProperty().get();
        Duration duration = inputController.speedChoiceBox.getValue().getDuration();

        timeline = new Timeline();
        timeline.setCycleCount(numberOfGenerations - 1);
        timeline.getKeyFrames().add(new KeyFrame(duration, (event ->
        {
            simulation.calculateNextGeneration();
            gridController.update(simulation);
            informationController.update(simulation.getCurrentGeneration());

        })));
        timeline.setOnFinished(event -> inputController.activateControls());
        timeline.playFromStart();
    }

    public void stopSimulation()
    {
        timeline.stop();
    }
}

