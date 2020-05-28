package ui.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.exception.TooManyItemException;
import model.items.Item;
import model.todolist.TodoList;
import model.todolist.TodoListManager;
import model.todolist.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class MainScreen implements Initializable, Observer {

    @FXML
    public Button logoutButton;
    public TextArea todoTextField;
    public Button displayRegularItem;
    public Button additem;
    public AnchorPane rootAnchor;
    public Button removebutton;
    public Circle insertTasks;
    public Circle removeTasks;
    public Circle notCompleteTasks;
    public Circle viewTasks;
    public StackPane stackPaneFlorCircle;
    public Button viewNotCompleted;
    public DatePicker schedule;


    //    public MenuItem menu1 = new MenuItem("action1");
//    public MenuItem menu2 = new MenuItem("action2");
//    MenuItem menuItem3 = new MenuItem("Option 3");
    protected User userOnline;


    TodoListManager todoListManager = new TodoListManager();
    List<User> users = new ArrayList<>();
    TodoList todoList = new TodoList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadTodoListManager();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TooManyItemException e) {
            e.printStackTrace();
        }

//        Text text = new Text();
//        text.setText(Integer.toString(todoListManager.getTodoList(userOnline.getUserName()).getTodos().size()));
//
//        stackPaneFlorCircle.getChildren().addAll(notCompleteTasks, text);
//        stackPaneFlorCircle.getChildren().addAll(viewTasks, text);
//


    }

    @Override
    public void update(Observable todoList, Object args) {
        System.out.println("A new item had been added!");
    }


    //EFFECTS:Load the Todolist Manager & as well list of users with corresponding login user.getLogin as true
    public void loadTodoListManager() throws IOException, ParseException, TooManyItemException {
        List<String> lines = Files.readAllLines(Paths.get("data/users.txt"));
        for (String line : lines) {
            List<String> list = splitOnSpace(line);
            Boolean login = Boolean.parseBoolean(list.get(2));
            User user = new User(list.get(0), list.get(1), login);
            users.add(user);
        }
        for (User user : users) {
            if (user.getLogin() == true) {
                userOnline = user;
            }
        }
        todoListManager.load();
    }

    public void loadTodoList() {
        todoList = todoListManager.getTodoList(userOnline.getUserName());
    }


    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("-/-/-");
        return new ArrayList<>(Arrays.asList(splits));
    }


    public void logout(ActionEvent actionEvent) throws IOException, TooManyItemException, ParseException {
        saveUserWhenLogout();
        todoListManager.save();
        Parent mainScreen = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }

    //EFFECTS:Save users into the user.txt whenever moves to a different screen
    public void saveUser() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/users.txt", "UTF-8");
        for (User user : users) {
            String s1 = Boolean.toString(user.getLogin());
            String s = user.getUserName() + "-/-/-" + user.getPassword() + "-/-/-" + s1;
            writer.println(s);
        }
        writer.close();
    }

    //EFFECTS:Does the same as saveUser but toggle user.setLogin to False
    public void saveUserWhenLogout() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/users.txt", "UTF-8");
        for (User user : users) {
            user.setLoginFalse();
            String s1 = Boolean.toString(user.getLogin());
            String s = user.getUserName() + "-/-/-" + user.getPassword() + "-/-/-" + s1;
            writer.println(s);
        }
        writer.close();
    }


    public void displayUrgent(ActionEvent actionEvent) {
    }

    public void disPlayRegularItem(ActionEvent actionEvent) {
        List<String> string1 = new ArrayList<>();
        TodoList todoList = todoListManager.getTodoList(userOnline.getUserName());
//        if (schedule.toString().equals("")) {
//            Alert box = new Alert(Alert.AlertType.INFORMATION);
//            box.setHeaderText("Please choose a date");
//            box.show();
//        } else {
            LocalDate ld = schedule.getValue();
            Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String todayasString = df.format(date);
            String finalString = "";
            for (Item i : todoList.getTodos()) {
//            if (i.getName().contains("...")) {
//                String spilt = i.getName() + " is due at " + i.getDueDateInString();
//                List<String> spiltstring = new ArrayList<String>(Arrays.asList(spilt.split("...")));
//                String togetherString = "";
//                for (String a : spiltstring)
//                {
//                    togetherString = togetherString + a;
//                }
//                string1.add(togetherString);
//            } else {
                if (todayasString.equals(i.getDueDateInString())) {
                    String a = i.getName() + " is due at " + i.getDueDateInString();
                    string1.add(a);
                }
            }
            for (String s : string1) {
                finalString = finalString + s + "\n";
//            String finalString = "";
//            for (String s : string1) {
//                finalString = finalString + s + "\n";
            }
            todoTextField.setText(finalString);
            String a = "hi";
        }


    public void addItem(ActionEvent actionEvent) throws IOException {
        todoListManager.save();
        saveUser();
        Parent mainScreen = FXMLLoader.load(getClass().getResource("AddItemScreen.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }

    public void displayDaily(ActionEvent actionEvent) {
    }

    public void removeTask(ActionEvent actionEvent) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("RemoveItemScreen.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }

    public void viewNotCompleted(ActionEvent actionEvent) {
        ArrayList<Item> pastDueItem = todoListManager.getOverDueItems(userOnline.getUserName());
        for (Item i : pastDueItem) {
            todoTextField.setText(i.getName() + " is due at " + i.getDueDateInString());
        }
    }


}

//
//
//        rootAnchor.getChildren().clear();
//        Circle circle = new Circle();
//        VBox vbox = new VBox();
//        AnchorPane.setTopAnchor(vbox, 10.0); // obviously provide your own constraints
//        rootAnchor.getChildren().add(vbox);
//        rootAnchor.getChildren().addAll(circle);
//
//
//
//            Label text = new Label();
//
//            text.setText("Hi");
//            text.setAlignment(Pos.CENTER);
//            AnchorPane.setLeftAnchor(text, circle.getCenterX());
//            AnchorPane.setTopAnchor(text, circle.getCenterY());
//            rootAnchor.getChildren().add(text);
//
//    }




