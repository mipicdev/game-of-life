package gol.controller.input;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Spinner;

public class SpinnerIntegerChangeListener implements ChangeListener<String>
{
    private Spinner<Integer> spinner;

    public SpinnerIntegerChangeListener(Spinner<Integer> spinner)
    {
        this.spinner = spinner;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue)
    {
        if (!newValue.isEmpty())
        {
            try
            {
                Integer.parseInt(newValue);
            }
            catch (NumberFormatException exception)
            {
                spinner.getEditor().textProperty().setValue(oldValue);
            }
        }
    }
}
