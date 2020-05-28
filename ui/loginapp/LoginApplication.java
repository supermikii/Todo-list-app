package ui.loginapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//There is a bug in my logout button if I don't click display and press logout then it automatically doesn't save
//Because there is no constructor, I don't know which method will be invoked when the screen is up

public class LoginApplication extends Application {


    Stage window;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LoginScene.fxml"));
        primaryStage.setTitle("Todo");
        primaryStage.setScene(new Scene(root, 600, 450));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stop();
                } catch (Exception e) {
                    System.out.print("Close");
                }
            }
        });

    }





    public static void main(String[] args)  {

        launch(args);

    //launch is a method in Application that calls start
    //So all code should go from Start
    //Stage is the window such as primary stage
    //content inside the window or Stage is the scene

    }
}
