package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;


public class HistoryController {
    @FXML
    private ListView HistoryList;
    public void initializeCalculation(ArrayList<String > calculation_history){
        calculation_history.forEach((calculation)->{
            HistoryList.getItems().add(calculation);
        });
    }
}
