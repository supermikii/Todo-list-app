package ui.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.exception.TooManyItemException;
import model.items.Item;

import java.io.IOException;
import java.net.URL;

import java.text.ParseException;
import java.util.ResourceBundle;

public class RemoveItemScreen extends MainScreen  implements Initializable {
    public Button confirm;
    public ChoiceBox menu;
    public Button back;


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
        setUpChoiceBox();
    }


    public void setUpChoiceBox() {
        for (Item i : todoListManager.getTodoList(userOnline.getUserName())) {
            menu.getItems().add(i.getName());
        }
    }

    public void confirmRemove(ActionEvent actionEvent) throws IOException {
        for (int i = 0; i < todoListManager.getTodoList(userOnline.getUserName()).getTodos().size(); i++) {
            if (todoListManager.getTodoList(userOnline.getUserName()).getNameTodoItem(i) == menu.getValue()) {
                Item a = todoListManager.getTodoList(userOnline.getUserName()).getTodos().get(i);
                todoListManager.checkOffTodo(userOnline.getUserName(), a);
                saveUser();
                todoListManager.save();
                Parent mainScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene main = new Scene(mainScreen);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(main);
                window.show();
            }
        }
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
        saveUser();
        todoListManager.save();
    }
}
