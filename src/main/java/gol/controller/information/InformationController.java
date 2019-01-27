package gol.controller.information;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationController implements Initializable
{
    @FXML
    public Label currentGenerationLabel;

    @FXML
    public Label remainingGenerationsLabel;

    @FXML
    public Label totalGenerationsLabel;

    @FXML
    public ProgressBar generationProgressBar;

    @FXML
    public ProgressIndicator generationProgressIndicator;

    @FXML
    public Label alivePopulationLabel;

    @FXML
    public Label alivePopulationPercentLabel;

    @FXML
    public Label deadPopulationLabel;

    @FXML
    public Label deadPopulationPercentLabel;

    @FXML
    public Label totalPopulationLabel;

    @FXML
    public Label currentTimeLabel;

    @FXML
    public Label remainingTimeLabel;

    @FXML
    public Label totalTimeLabel;

    private IntegerProperty currentGeneration = new SimpleIntegerProperty();
    private IntegerProperty remainingGeneration = new SimpleIntegerProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        currentGenerationLabel.textProperty().bind(currentGeneration.asString());
        remainingGenerationsLabel.textProperty().bind(remainingGeneration.asString());

        generationProgressBar.progressProperty().bind(Bindings.createDoubleBinding(() ->
        {
            double currentGeneration = Double.valueOf(currentGenerationLabel.textProperty().get());
            double totalGeneration = Double.valueOf(totalGenerationsLabel.textProperty().get());
            return currentGeneration / totalGeneration;
        }, currentGenerationLabel.textProperty()));

        generationProgressIndicator.progressProperty().bind(generationProgressBar.progressProperty());
    }

    public void update(int currentGeneration)
    {
        int totalGenerations = Integer.parseInt(totalGenerationsLabel.textProperty().get());
        int remainingGenerations = totalGenerations - currentGeneration;

        this.currentGeneration.setValue(currentGeneration);
        remainingGeneration.setValue(remainingGenerations);
    }
}
