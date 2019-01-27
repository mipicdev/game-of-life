package gol.controller.input;

import gol.controller.MainController;
import gol.controller.Speed;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class InputController implements Initializable
{
    @FXML
    public Spinner<Integer> probabilitySpinner;

    @FXML
    public Spinner<Integer> universeSizeSpinner;

    @FXML
    public Spinner<Integer> numberOfGenerationsSpinner;

    @FXML
    public ChoiceBox<Speed> speedChoiceBox;

    @FXML
    public ColorPicker cellAliveColorPicker;

    @FXML
    public ColorPicker cellDeadColorPicker;

    @FXML
    public Button startButton;

    @FXML
    public Button stopButton;

    private MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        universeSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 250, 100));
        universeSizeSpinner.getEditor().textProperty().addListener(new SpinnerIntegerChangeListener(universeSizeSpinner));

        probabilitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 50));
        probabilitySpinner.getEditor().textProperty().addListener(new SpinnerIntegerChangeListener(probabilitySpinner));

        numberOfGenerationsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, Integer.MAX_VALUE, 1000));
        numberOfGenerationsSpinner.getEditor().textProperty().addListener(new SpinnerIntegerChangeListener(numberOfGenerationsSpinner));

        speedChoiceBox.setItems(FXCollections.observableArrayList(Speed.values()));
        speedChoiceBox.setValue(Speed.ULTRAFAST);

        cellAliveColorPicker.setValue(Color.YELLOW);
        cellDeadColorPicker.setValue(Color.BLUE);

        startButton.setOnAction(event ->
        {
            deactivateControls();
            mainController.startSimulation();
        });

        stopButton.setOnAction(event ->
        {
            mainController.stopSimulation();
            activateControls();
        });

        activateControls();
    }

    private void deactivateControls()
    {
        universeSizeSpinner.setDisable(true);
        probabilitySpinner.setDisable(true);
        numberOfGenerationsSpinner.setDisable(true);
        speedChoiceBox.setDisable(true);
        startButton.setDisable(true);
        stopButton.setDisable(false);
        cellAliveColorPicker.setDisable(true);
        cellDeadColorPicker.setDisable(true);
    }

    public void activateControls()
    {
        universeSizeSpinner.setDisable(false);
        probabilitySpinner.setDisable(false);
        numberOfGenerationsSpinner.setDisable(false);
        speedChoiceBox.setDisable(false);
        startButton.setDisable(false);
        stopButton.setDisable(true);
        cellAliveColorPicker.setDisable(false);
        cellDeadColorPicker.setDisable(false);
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
}
