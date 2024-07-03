package com.example.stick_hero;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class Stick{
    @FXML

    private Stage stagegame;
    @FXML

    private Scene scenegame;
    @FXML
    private Parent rootgame;
    @FXML
    private int height=400;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private ScreenController screenController;
    @FXML
    static Rectangle rec1;
    @FXML
    static Rectangle rec2;

    @FXML Line lineid;
    @FXML
    static ImageView playerid;
    @FXML
    ImageView gamescreenid;

    public void run(ActionEvent event) throws IOException {
        rootgame = FXMLLoader.load(getClass().getResource("gamescreen.fxml"));
        stagegame=(Stage)((Node)event.getSource()).getScene().getWindow();
        scenegame=new Scene(rootgame);
        stagegame.show();
//        Gamemechanics gamemechanics=new Gamemechanics();
//        Group newgroup=new Group();
//        scenegame=new Scene(newgroup);
//        int width1=gamemechanics.randomgenerate(100);
//        rec1.setHeight(getHeight());
//        rec1.setWidth(width1);
//        TranslateTransition translate=new TranslateTransition();
//        translate.setNode(playerid);
//        translate.setByX(width1);
//        translate.setDuration(Duration.millis(1000));
//        rec1.setX(0);
//        rec1.setY(500);
//        newgroup.getChildren().add(rec1);
//        newgroup.getChildren().add(playerid);
//        int width2=gamemechanics.randomgenerate(100);
//        rec2.setHeight(getHeight());
//        rec2.setWidth(width2);
//        int distance1=gamemechanics.randomgeneratedistance(width1);
//        rec2.setX(distance1);
//        rec2.setY(500);
//        newgroup.getChildren().add(rec2);
//        lineid.setStrokeWidth(5);
//        lineid.setStroke(Color.BLUE);
//        newgroup.getChildren().add(lineid);
//        final int y[]={500};
//        final int[] m = {0};
//        Scene finalScene = scenegame;
//        scenegame.setRoot(newgroup);
//        stagegame.setScene(scenegame);
//        scenegame.setOnKeyPressed(keyEvent -> {
//            {
//                if (keyEvent.getCode() == KeyCode.SPACE) {
//                    finalScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
//                        @Override
//                        public void handle(KeyEvent keyEvent) {
//                            if (keyEvent.getCode() == KeyCode.SPACE) {
//                                // Stop increasing y-coordinate
//                                finalScene.setOnKeyReleased(null);
//                                lineid.setEndX(width1+m[0]);
//                                lineid.setEndY(500);
//                                translate.play();
//
//                            }
//                        }
//                    });
//                    // Start increasing y-coordinate
//                    y[0] -= 5; // Decreasing y-coordinate to move upwards
//                    m[0] +=5;
//                    lineid.setEndY(y[0]);
//                }
//            }
//        });
//        stagegame.show();

    }

    public void resume(ActionEvent event)
    {
        return;

    }
    public void exittomainmenu(ActionEvent event) throws Exception {

    }
    public void restart(ActionEvent event)
    {

    }


}
