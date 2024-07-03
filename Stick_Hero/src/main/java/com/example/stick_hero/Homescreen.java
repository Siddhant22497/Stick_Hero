package com.example.stick_hero;
import java.io.File;
import javafx.scene.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.*;


import java.io.IOException;
import java.nio.file.Paths;

public class Homescreen extends Application {
    @FXML
    static Stage stagehome=null;
    @FXML
    static Scene scenehome=null;
    @FXML
    static Parent roothome=null;

    private Text st;
    private static Homescreen h1=null;
    @FXML
    static MediaPlayer mediaPlayer;

    public static Homescreen getHomescreen()
    {
        if(h1==null)
        {
            h1=new Homescreen();
        }
        return h1;    }

    public void  start(Stage stage) throws Exception {
        ScreenController.count=0;
        ScreenController.min=0;
        try {
            if(ScreenController.button!=null)
            ScreenController.button.play();
            String path = "/homemusic.mp3";
            Media media = new Media(getClass().getResource(path).toExternalForm());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.play();
            Parent loader = FXMLLoader.load(getClass().getResource("homescreen.fxml"));
            Group root = new Group(loader);
            scenehome = new Scene(root);
            stagehome=new Stage();
            stagehome.setTitle("Stick Hero");
            stagehome.setScene(scenehome);
            stagehome.setFullScreen(true);
            stagehome.setFullScreenExitHint("");
            stagehome.show();
            stagehome.setOnCloseRequest(event -> {
                try {
                    event.consume();
                    exit(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public void exit(Stage stage) throws IOException
    {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit");
        if(alert.showAndWait().get()== ButtonType.OK){
            System.out.println("Thank for playing the game");
            ScreenController.stagegame.close();
            stage.close();
        }
    }


    public static void main(String[] args)
    {
        launch();
    }
}
