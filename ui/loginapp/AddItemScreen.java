package ui.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.exception.TooManyItemException;
import model.items.Item;
import model.items.RegularItem;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

public class AddItemScreen extends MainScreen  {

    @FXML
    public TextArea descriptionBox;
    public TextField dateBox;
    public Button addButton;
    public AnchorPane rootAnchorPane;
    public Button back;
    public DatePicker calender1;
    public DatePicker databox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            super.loadTodoListManager();
            super.loadTodoList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TooManyItemException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS:Fixed a bug about how to make multiple line string combine into one string so input is alright
    public void confirm(ActionEvent actionEvent) throws IOException, TooManyItemException {
        if (descriptionBox.getText().equals("")) {
                descriptionBox.setText("Please add a task");
            } else if (calender1.toString().length() == 0) {
            dateBox.setText("Please add a deadline");
        } else if ((calender1.toString().length() == 10)) {
//            System.out.println("Please type date in the correct format [yyyy/MM/dd]");
            Alert dg = new Alert(Alert.AlertType.INFORMATION);
            dg.setTitle("Error");
            dg.setHeaderText("Please us yy/mm/dd format for your date");
            dg.setContentText("Invalid date");
            dg.show();
        } else {
            LocalDate ld = databox.getValue();
            Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String name = descriptionBox.getText().replaceAll("[\r\n]", " ");
            Item item = new RegularItem(name, date);
            Alert dg = new Alert(Alert.AlertType.INFORMATION);
            dg.setHeaderText("Task successfully added");
            dg.show();
//            todoListManager.addTodoItemInManager(userOnline.getUserName(), item);
            todoListManager.getTodoList(userOnline.getUserName()).addTodo(item);
            todoListManager.save();
            Parent mainScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene main = new Scene(mainScreen);
            Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            window.setScene(main);
            window.show();
        }
    }


    public void switchScreen() throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main = new Scene(mainScreen);
        //How to make switch screen as a method because it doesn't use an event handler but
        //There was the problem of getting multiple screen if I initialize a new window
//        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//        window.setScene(main);
//        window.show();
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }
}
