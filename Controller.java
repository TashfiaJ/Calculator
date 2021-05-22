package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;


public class Controller {
    @FXML
    private Label Expressions;

    @FXML
    private Label result;

    private ArrayList<String> calculation_history = new ArrayList<>();

    public void insertOperator(String operator){
        Expressions.setText(Expressions.getText()+" "+operator+" ");
    }

    public void insertNumber(String number){
        Expressions.setText(Expressions.getText() + number);
    }

    public void deleteLast(){
        if(!getExpressions().getText().isEmpty()){
            StringBuilder text = new StringBuilder(getExpressions().getText());
            text.deleteCharAt(text.length()-1);
            getExpressions().setText(text.toString());
        }
    }
    public void clearExpression(){
        Expressions.setText("");
        result.setText("");
    }

    public Label getExpressions() {
        return Expressions;
    }

    public Label getResult() {
        return result;
    }

    public void setResult(String newResult){
        result.setText("= " + newResult);
    }

    public void insertAns(String answer){
        Expressions.setText(Expressions.getText()+answer);
    }

    public void openHistoryWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HistoryFXML.fxml"));
            Parent root = loader.load();

            Main.getHistoryStage().setScene(new Scene(root));

            HistoryController HistoryController = loader.getController();
            HistoryController.initializeCalculation(calculation_history);
            Main.getHistoryStage().show();

        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void addCalculation(String expression, String result){
        this.calculation_history.add(expression + " = " +  result);
    }

    public void onMouseClick(MouseEvent mouseEvent){
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();


        switch (buttonText){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumber(buttonText);
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                insertOperator(buttonText);
                break;
            case "CLEAR":
                clearExpression();
                break;
            case "=":
                double result = EvaluateString.evaluate(getExpressions().getText());
                addCalculation(this.getExpressions().getText(), String.valueOf(result));
                setResult(String.valueOf(result));
                break;
            case "ANS":
                insertAns(getResult().getText().substring(2));
                break;
            case "DELETE":
                deleteLast();
                break;
            case "HIST":
                openHistoryWindow();
                break;
        }
    }

}
