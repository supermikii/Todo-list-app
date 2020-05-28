package ui.loginapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.exception.TooManyItemException;
import model.todolist.TodoListManager;
import model.todolist.User;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class LoginScene implements Initializable {






    //Load all of the following fields from the FXML LoginScene.fxml
    @FXML
    public TextField textField;
    public PasswordField passwordField;
    public Button login;
    public ImageView imageFile;
    public Button scary;
    public Label label;
//    public Label hint;
    //Grab method load from User Class
    //Construct a object that is a list of Users
    //To verify if the passwordField & textField match the iteration for every User.getName + User.getPassword in
    //list users
    //Remember to saveUser deconstruct a list of Users & turn it into String and puts it in user.txt when each button
    //is clicked
    List<User> users = new ArrayList<>();
    TodoListManager todoListManager = new TodoListManager();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
////            setupAPI();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
        try {
            loadUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            todoListManager.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (TooManyItemException e) {
            e.printStackTrace();
        }
    }

    public void loadUsers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("data/users.txt"));
        for (String line : lines) {
            List<String> list = splitOnSpace(line);
            Boolean login = Boolean.parseBoolean(list.get(2));
            User user = new User(list.get(0), list.get(1), login);
            users.add(user);
        }
    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {

        for (User user : users) {
            if (textField.getText().equals(user.getUserName())
                    && passwordField.getText().equals(user.getPassword())) {
                user.setLoginTrue();
                saveUser();
//                //Make sure Load FXML LOADER actually reads the FXML URL location
//                that correspond to the Initiate function
                //In MainScreen Class make sure the getResource takes the actual location /ui/Login/MainScreen.fxml
                Parent mainScreen = FXMLLoader.load(getClass().getResource("/ui/loginapp/MainScreen.fxml"));
                Scene main = new Scene(mainScreen);
                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(main);
                window.show();


            }
        }
    }

    public void setupApi() throws IOException {
        String s;
        String theurl = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
        URL url = new URL(theurl);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        s = sb.toString();
        System.out.println(s);
        label.setText(s);
        br.close();
    }


    public void saveUser() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("data/users.txt", "UTF-8");
        for (User user : users) {
            String s1 = Boolean.toString(user.getLogin());
            String s = user.getUserName() + "-/-/-" + user.getPassword() + "-/-/-" + s1;
            writer.println(s);
        }
        writer.close();
    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split("-/-/-");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public void clickScary(ActionEvent actionEvent) throws IOException {
        Parent mainScreen = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        Scene main = new Scene(mainScreen);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(main);
        window.show();
    }


    //EFFECTS:Creates a new Username if username is not taken & set a brand new todolist assigned to it, and save
    public void createUser(String input, String password) throws IOException, TooManyItemException {
        if (!todoListManager.getTodos().keySet().contains(input)) {
            User newUser = new User(input, password, false);
            users.add(newUser);
            saveUser();
        }
    }
}
