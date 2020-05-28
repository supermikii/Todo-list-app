package ui.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.exception.TooManyItemException;
import model.todolist.TodoList;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class  SignupScreen extends LoginScene implements Initializable {



    @FXML
    
    public Button creatUserBox;
    public TextField email1;
    public TextField password;
    public TextField username;


    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Bug: How to save new user-name into the todolist Manager file input in the latest line
    //but my saving just saves all the item with -/-/- and @
    public void createUser(ActionEvent actionEvent) throws IOException, TooManyItemException, ParseException {
        createUser(username.getText(), password.getText());
        todoListManager.load();
        todoListManager.putUser(username.getText(), new TodoList());
        todoListManager.save();
        Parent mainScreen = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }
}
