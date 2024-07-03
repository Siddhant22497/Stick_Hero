package com.example.stick_hero;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;



public class ScreenController implements Initializable {

    @FXML
    private Button exitid;
    @FXML
    private Button continueid;
    static int min=2;
    static Playerinfo playerinfo=null;

    private int height = 400;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @FXML
    static Stage stagegame = null;
    @FXML

    static Scene scenegame = null;
    @FXML

    static Parent rootgame = null;
    @FXML
    static Stage exitstage;
    @FXML
    static Scene exitscene;
    @FXML
    static Parent exitroot;

    private Homescreen homescreen;
    @FXML

    static ImageView playerid;
    @FXML
    static ImageView cheeryinexit;
    @FXML
    static ImageView cheeryid;
    @FXML
    static ImageView cheerytocontinue;
    @FXML
    static ImageView cheerytop;
    @FXML
    static Label label = null;
    @FXML
    static Label label2 = null;
    @FXML
    static Label label3 = null;
    @FXML

    private static Rectangle rec1;
    @FXML

    private static Rectangle rec2;
    @FXML
    private static Line lineid;
    static Group combinedGroup = null;
    static Group combinedGrouptoexit = null;
    static int distance1;
    private Group groupname;
    Pause p1 = new Pause();
    static ArrayList<Stage> collectionofstage = new ArrayList<Stage>();

    private static TranslateTransition translate;
    static boolean flag = true;
    static boolean flag2 = true;
    static boolean playeridpostion = true;
    double track;
    boolean flag4 = true;
    static float xaxis = 0;
    private static ScreenController s1 = null;

    public static ScreenController getScreenController() {
        if (s1 == null) {
            s1 = new ScreenController();

        }
        return s1;
    }

    static int count = 0;
    static int width1;
    static int width2;
    static Group newgroup = null;
    static Group newgrouptoexit = null;
    static boolean cherrybollean = false;
    static MediaPlayer rollupdown;
    static MediaPlayer running;

    static MediaPlayer playerfall;
    static MediaPlayer button;
    static MediaPlayer stickfall;
    static MediaPlayer collectedcherry;
    private String playername;
    private int totalcheries;
    private int highestscore;
    private Stage stageplayer;
    private Parent rootplayer;
    private Scene sceneplayer;
    @FXML
    private TextField detailid;
    static String name;

    public void setplayer(ActionEvent event) throws IOException {
        Homescreen.mediaPlayer.stop();
        rootplayer = FXMLLoader.load(getClass().getResource("gameplayer.fxml"));
        stageplayer = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneplayer = new Scene(rootplayer);
        stageplayer.setScene(sceneplayer);
        stageplayer.setX((Screen.getPrimary().getVisualBounds().getWidth() - stageplayer.getWidth())/2);
        stageplayer.setY((Screen.getPrimary().getVisualBounds().getHeight() - stageplayer.getHeight())/2);
        stageplayer.show();
    }
    public void submitplayer(ActionEvent event) throws IOException {
        name=detailid.getText();
        playerinfo=Playerinfo.getPlayerinfo(name);
        System.out.println("Total cheries: "+playerinfo.getTotalCheries());
        System.out.println("Highest score: "+playerinfo.getHighestpoint());
        System.out.println("Now ready to play");
        this.run(event);

    }

    public void run(ActionEvent event) throws IOException {
        Homescreen.stagehome.close();
        playeridpostion = true;
        Homescreen.mediaPlayer.stop();
        String path = "/button.mp3";
        Media media = new Media(getClass().getResource(path).toExternalForm());
        button = new MediaPlayer(media);
        button.setAutoPlay(true);
        button.play();
        String path1 = "/roll_up_down.mp3";
        Media rollup = new Media(getClass().getResource(path1).toExternalForm());
        rollupdown = new MediaPlayer(rollup);
        rollupdown.setAutoPlay(false);
        final int y[] = {500};
        final int[] m = {0};
        rootgame = FXMLLoader.load(getClass().getResource("gamescreen.fxml"));
        stagegame = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scenegame = new Scene(rootgame);
        stagegame=new Stage();
        stagegame.setScene(scenegame);
        newgroup = new Group();
        this.develop();
        final int[] k = {5};
        newgroup.getChildren().addAll(rec1, rec2, lineid, playerid, cheeryid, cheerytop, label);
        combinedGroup = new Group();
        combinedGroup.getChildren().addAll(scenegame.getRoot(), newgroup);
        scenegame = new Scene(combinedGroup);
        stagegame.setScene(scenegame);
        collectionofstage.add(stagegame);

        // i have added the code below
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), newgroup);
        transition.setFromX(scenegame.getWidth());
        transition.setToX(0);
        transition.play();
        scenegame.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && flag == true) {
                // Start increasing y-coordinate
                y[0] -= 5; // Decreasing y-coordinate to move upwards
                m[0] += 5;
                lineid.setEndY(y[0]);
            }
            if (keyEvent.getCode() == KeyCode.P) {

            }
        });

        int finalWidth = width1;
        scenegame.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                try {
                    p1.showpausedscreen();
                    button.play();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (keyEvent.getCode() == KeyCode.SPACE) {
                flag = false;
                String path3 = "/stick fall.mp3";
                Media stickmedia = new Media(getClass().getResource(path3).toExternalForm());
                stickfall = new MediaPlayer(stickmedia);
                stickfall.setAutoPlay(true);
                stickfall.play();
                String path2 = "/running.mp3";
                Media runmedia = new Media(getClass().getResource(path2).toExternalForm());
                running = new MediaPlayer(runmedia);
                running.setAutoPlay(true);
                running.play();
                lineid.setEndX(finalWidth + m[0]);
                lineid.setEndY(500);
                track = m[0] + playerid.getTranslateX();
                translate.setToX(m[0] + playerid.getTranslateX());
                translate.play();
                scenegame.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.UP && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(0);
                            playerid.setY(450);
                            playeridpostion = true;
                            rollupdown.stop();
                        } else if (keyEvent.getCode() == KeyCode.DOWN && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(180);
                            playerid.setY(500);
                            playeridpostion = false;
                            rollupdown.stop();
                        }
                    }
                });
                double temp = Math.abs(playerid.getTranslateX() - xaxis);
                double minimum = 100;
                Scene changescene = null;
                if (temp < minimum && playeridpostion == cherrybollean) {
                    count+=1;
                }
                translate.setOnFinished(e -> {
                    if ((m[0] < distance1 - width1) || (m[0] > (width2 + (distance1 - width1))) && flag4) {
                        running.stop();
                        String path4 = "/player fall.mp3";
                        Media fallmedia = new Media(getClass().getResource(path4).toExternalForm());
                        playerfall = new MediaPlayer(fallmedia);
                        playerfall.setAutoPlay(true);
                        playerfall.play();
                        TranslateTransition tt = new TranslateTransition();
                        tt.setNode(playerid);
                        tt.setFromY(translate.getFromY());
                        tt.setToY(playerid.getTranslateY() + 300);
                        tt.play();
                        k[0] = 0;
                        try {
                            this.showexit(event);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }

                    } else {
                        flag = false;
                        flag2 = false;
                        running.stop();
                        TranslateTransition tt = new TranslateTransition(Duration.millis(3000));
                        tt.setDuration(Duration.millis(2000));
                        tt.setNode(playerid);
                        int d2 = (int) ((distance1 - width2 - 40) - playerid.getTranslateX());
                        if (-10 < d2 && d2 < 10) {
                            tt.setFromX(playerid.getTranslateX());
                            tt.setToX(distance1 - 40);
                            tt.play();
                            tt.setOnFinished(e1 -> {
                                try {
                                    this.repeat(event);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            });
                        } else {
                            System.out.println("Repeat");
                            try {
                                this.repeat(event);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
            }
        });
        stagegame.setFullScreenExitHint("");
        stagegame.setFullScreen(true);
        stagegame.show();

    }


    public int position() {
        Gamemechanics gamemechanics = Gamemechanics.getGamemechanics();
        Random r = new Random();
        int y = r.nextInt(2) + 1;
        if (y == 1) {
            return 0;
        } else if (y == 1) {
            return 1;
        }
        return 1;
    }

    public void develop() {
        cheerytop = new ImageView();
        Image image3 = new Image("top.png");
        cheerytop.setImage(image3);
        cheerytop.setFitHeight(300);
        cheerytop.setFitWidth(300);
        cheerytop.setX(1100);
        cheerytop.setY(-100);
        label = new Label();
        String y2 = Integer.toString(count);
        label.setText(y2);
        label.setFont(Font.font(45));
        label.setLayoutX(1200);
        label.setLayoutY(10);
        Gamemechanics gamemechanics = Gamemechanics.getGamemechanics();
        width1 = gamemechanics.randomgenerate(100);
        rec1 = new Rectangle();
        rec2 = new Rectangle();
        lineid = new Line();
        rec1.setHeight(getHeight());
        playerid = new ImageView();
        Image image = new Image("character.png");
        playerid.setImage(image);
        playerid.setX(width1 - 40);
        playerid.setY(450);
        playerid.setFitWidth(60);
        playerid.setFitHeight(50);
        rec1.setWidth(width1);
        translate = new TranslateTransition();
        translate.setNode(playerid);
        translate.setFromX(playerid.getTranslateX());
        translate.setDuration(Duration.millis(2000));
        rec1.setX(0);
        rec1.setY(500);
        lineid.setStartX(width1);
        lineid.setStartY(500);
        lineid.setEndX(width1);
        lineid.setEndY(500);
        lineid.setStrokeWidth(5);
        lineid.setStroke(Color.GREEN);
        width2 = gamemechanics.randomgenerate(100);
        rec2.setHeight(getHeight());
        rec2.setWidth(width2);
        distance1 = gamemechanics.randomgeneratedistance(width1);
        rec2.setX(distance1);
        rec2.setY(500);
        cheeryid = new ImageView();
        Image image2 = new Image("cherry.png");
        cheeryid.setImage(image2);
        int p = this.position();
        Random r1 = new Random();
        xaxis = gamemechanics.getRandomaxis((int) (distance1 - width2 - 40) - (width1 - 70));
        cheeryid.setX(xaxis + 20);
        int k1 = 340;
        if (p == 0) {
            k1 = 340;
            cherrybollean = false;                                //for down cherry
        } else if (p == 1) {
            cherrybollean = true;                                 //for up cherry
            k1 = 380;
        }
        cheeryid.setY(k1);
        cheeryid.setFitWidth(200);
        cheeryid.setFitHeight(280);

    }

    public void showexit(ActionEvent event) throws IOException {
        exitroot = FXMLLoader.load(getClass().getResource("escape.fxml"));
        exitstage = new Stage();
        exitstage.initOwner(stagegame);
        exitstage.initModality(Modality.WINDOW_MODAL);
        this.arrange();
        newgrouptoexit=new Group();
        newgrouptoexit.getChildren().addAll(label2,label3,cheerytocontinue,cheeryinexit,continueid,exitid);
        combinedGrouptoexit = new Group();
        combinedGrouptoexit.getChildren().addAll(newgrouptoexit);
        exitscene = new Scene(combinedGrouptoexit,600,400,Color.WHITE);
        exitstage.setScene(exitscene);
        exitstage.setTitle("Stick Hero");
        Image image = new Image(("logo.png"));
        continueid.setOnKeyPressed(e1->{
            if(count>=min) {
                try {
                    for (int i = 0; i < collectionofstage.size(); ++i) {
                        Stage s10 = (Stage) collectionofstage.get(i);
                        s10.close();
                        collectionofstage.remove(i);
                    }
                    exitstage.close();
                    count=count-min;
                    min+=2;
                    this.repeat(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Not enough point");
        });
        exitid.setOnKeyPressed(e2->{
            Homescreen s1=Homescreen.getHomescreen();
            try {
                if(ScreenController.playerinfo!=null) {
                    int m = ScreenController.playerinfo.getTotalCheries() + ScreenController.count;
                    ScreenController.playerinfo.setTotal_cheries(m);
                    if (ScreenController.count > ScreenController.playerinfo.getHighestpoint()) {
                        ScreenController.playerinfo.setHighestpoint(ScreenController.count);

                    }
                    Playerinfo.settingPlayerinfo(ScreenController.name, ScreenController.playerinfo);
                }
                for(int i=0;i<collectionofstage.size();++i)
                {
                    Stage s10=(Stage)collectionofstage.get(i);
                    s10.close();
                    collectionofstage.remove(i);
                }
                exitstage.close();
                s1.start(Homescreen.stagehome);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        exitstage.getIcons().add(image);
        exitstage.show();

    }
    public void arrange()
    {
        exitid=new Button("EXIT TO HOMESCREEN");
        continueid=new Button("REVIVE");
        continueid.setLayoutX(280);
        continueid.setLayoutY(200);
        exitid.setLayoutX(250);
        exitid.setLayoutY(250);
        Image image4 = new Image("top.png");
        cheeryinexit=new ImageView();
        cheerytocontinue=new ImageView();
        cheeryinexit.setImage(image4);
        cheeryinexit.setFitHeight(400);
        cheeryinexit.setFitWidth(400);
        cheeryinexit.setX(350);
        cheeryinexit.setY(-150);
        Image image5 = new Image("top.png");
        cheerytocontinue.setImage(image4);
        cheerytocontinue.setFitHeight(400);
        cheerytocontinue.setFitWidth(400);
        cheerytocontinue.setX(350);
        cheerytocontinue.setY(-50);
        label2=new Label();
        label3=new Label();
        String y2 = Integer.toString(count);
        label2.setText("Current Cheeries "+y2);
        label2.setFont(Font.font(40));
        label2.setLayoutX(130);
        label2.setLayoutY(20);
        String y3 = Integer.toString(min);
        label3.setText("Cheeries to Revive "+y3);
        label3.setFont(Font.font(40));
        label3.setLayoutX(100);
        label3.setLayoutY(120);
    }

    public void repeat(ActionEvent event) throws IOException {
        for(int i=0;i<collectionofstage.size()-1;++i)
        {
            Stage s5=(Stage)collectionofstage.get(i);
            s5.close();
            collectionofstage.remove(i);
        }
        playeridpostion = true;
        this.develop();
        flag2 = true;
        String path = "/button.mp3";
        Media media = new Media(getClass().getResource(path).toExternalForm());
        button = new MediaPlayer(media);
        button.setAutoPlay(true);
        button.play();
        String path1 = "/roll_up_down.mp3";
        Media rollup = new Media(getClass().getResource(path1).toExternalForm());
        rollupdown = new MediaPlayer(rollup);
        rollupdown.setAutoPlay(false);
        final int y[] = {500};
        final int[] m = {0};
        rootgame = FXMLLoader.load(getClass().getResource("gamescreen.fxml"));
        stagegame = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scenegame = new Scene(rootgame);
        stagegame = new Stage();
        stagegame.setScene(scenegame);
        newgroup = new Group();
        this.develop();
        newgroup.getChildren().addAll(rec1, rec2, lineid, playerid, cheeryid, cheerytop, label);
        combinedGroup = new Group();
        combinedGroup.getChildren().addAll(scenegame.getRoot(), newgroup);
        scenegame = new Scene(combinedGroup);
        stagegame.setScene(scenegame);
        collectionofstage.add(stagegame);
        flag = true;
        final int y1[] = {500};
        final int[] m1 = {0};
        final int[] y5 = new int[1];
        scenegame.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && flag == true) {
                // Start increasing y-coordinate
                y1[0] -= 5; // Decreasing y-coordinate to move upwards
                m1[0] += 5;
                lineid.setEndY(y1[0]);
            }
            if (keyEvent.getCode() == KeyCode.P) {

            }

        });

        int finalWidth = width1;
        scenegame.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                try {
                    p1.showpausedscreen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (keyEvent.getCode() == KeyCode.SPACE) {

                flag = false;
                String path3 = "/stick fall.mp3";
                Media stickmedia = new Media(getClass().getResource(path3).toExternalForm());
                stickfall = new MediaPlayer(stickmedia);
                stickfall.setAutoPlay(true);
                stickfall.play();
                String path2 = "/running.mp3";
                Media runmedia = new Media(getClass().getResource(path2).toExternalForm());
                running = new MediaPlayer(runmedia);
                running.setAutoPlay(true);
                running.play();
                lineid.setEndX(finalWidth + m1[0]);
                lineid.setEndY(500);
                track = m1[0] + playerid.getTranslateX();
                translate.setToX(m1[0] + playerid.getTranslateX());
                translate.play();
                scenegame.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.UP && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(0);
                            playerid.setY(450);
                            playeridpostion = true;
                            rollupdown.stop();
                        } else if (keyEvent.getCode() == KeyCode.DOWN && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(180);
                            playerid.setY(500);
                            playeridpostion = false;
                            rollupdown.stop();
                        }

                    }
                });
                scenegame.setOnKeyReleased(e -> {
                    double temp = Math.abs(playerid.getTranslateX() - xaxis);
                    double minimum = 100;
                    if (temp < minimum && playeridpostion == cherrybollean) {
                        count+=1;
                    }
                    translate.setOnFinished(e2 -> {
                        if ((m1[0] < distance1 - width1) || (m1[0] > (width2 + (distance1 - width1))) && flag4) {
                            running.stop();
                            String path4 = "/player fall.mp3";
                            Media fallmedia = new Media(getClass().getResource(path4).toExternalForm());
                            playerfall = new MediaPlayer(fallmedia);
                            playerfall.setAutoPlay(true);
                            playerfall.play();
                            TranslateTransition tt = new TranslateTransition();
                            tt.setNode(playerid);
                            tt.setFromY(translate.getFromY());
                            tt.setToY(playerid.getTranslateY() + 300);
                            tt.play();
                            System.out.println("Game over");
                            flag2 = false;
                            try {
                                this.showexit(event);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            flag = false;
                            flag2 = false;
                            running.stop();
                            TranslateTransition tt = new TranslateTransition(Duration.millis(3000));
                            tt.setDuration(Duration.millis(2000));
                            tt.setNode(playerid);
                            int d2 = (int) ((distance1 - width2 - 40) - playerid.getTranslateX());
                            if (-10 < d2 && d2 < 10) {
                                tt.setFromX(playerid.getTranslateX());
                                tt.setToX(distance1 - 40);
                                tt.play();
                                tt.setOnFinished(e1 -> {
                                    try {
                                        this.repeat(event);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                });
                            } else {
                                System.out.println("Repeat");
                                try {
                                    this.repeat(event);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    });

                });
            }
        });

        stagegame.setFullScreenExitHint("");
        stagegame.setFullScreen(true);
        stagegame.show();
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), newgroup);
        transition.setFromX(scenegame.getWidth());
        transition.setToX(0);
        transition.play();
    }


    public void run2(ActionEvent event) throws IOException {
        playeridpostion = true;
        count = 0;
        flag2 = true;
        for(int i=0;i<collectionofstage.size()-1;++i)
        {
            Stage s10=(Stage)collectionofstage.get(i);
            s10.close();
            collectionofstage.remove(i);
        }
        String path = "/button.mp3";
        Media media = new Media(getClass().getResource(path).toExternalForm());
        button = new MediaPlayer(media);
        button.setAutoPlay(true);
        button.play();
        String path1 = "/roll_up_down.mp3";
        Media rollup = new Media(getClass().getResource(path1).toExternalForm());
        rollupdown = new MediaPlayer(rollup);
        rollupdown.setAutoPlay(false);
        ;
        rootgame = FXMLLoader.load(getClass().getResource("gamescreen.fxml"));
        stagegame = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stagegame = new Stage();
        scenegame = new Scene(rootgame);
        stagegame.setScene(scenegame);
        collectionofstage.add(stagegame);
        Group newgroup = new Group();
        this.develop();
        flag2 = true;
        newgroup.getChildren().addAll(rec1, rec2, lineid, playerid, cheeryid, cheerytop, label);
        Group combinedGroup = new Group();
        combinedGroup.getChildren().addAll(scenegame.getRoot(), newgroup);
        scenegame = new Scene(combinedGroup);
        stagegame.setScene(scenegame);
        flag = true;
        final int y1[] = {500};
        final int[] m = {0};
        final int[] y5 = new int[1];
        scenegame.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.SPACE && flag == true) {
                // Start increasing y-coordinate
                y1[0] -= 5; // Decreasing y-coordinate to move upwards
                m[0] += 5;
                lineid.setEndY(y1[0]);
            }
            if (keyEvent.getCode() == KeyCode.P) {

            }
        });

        int finalWidth = width1;
        scenegame.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.P) {
                try {
                    p1.showpausedscreen();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (keyEvent.getCode() == KeyCode.SPACE) {

                flag = false;
                String path3 = "/stick fall.mp3";
                Media stickmedia = new Media(getClass().getResource(path3).toExternalForm());
                stickfall = new MediaPlayer(stickmedia);
                stickfall.setAutoPlay(true);
                stickfall.play();
                String path2 = "/running.mp3";
                Media runmedia = new Media(getClass().getResource(path2).toExternalForm());
                running = new MediaPlayer(runmedia);
                running.setAutoPlay(true);
                running.play();
                lineid.setEndX(finalWidth + m[0]);
                lineid.setEndY(500);
                track = m[0] + playerid.getTranslateX();
                translate.setToX(m[0] + playerid.getTranslateX());
                translate.play();
                scenegame.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.UP && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(0);
                            playerid.setY(450);
                            playeridpostion = true;
                            rollupdown.stop();
                        } else if (keyEvent.getCode() == KeyCode.DOWN && flag2 == true) {
                            rollupdown.play();
                            playerid.setRotate(180);
                            playerid.setY(500);
                            playeridpostion = false;
                            rollupdown.stop();
                        }
                    }
                });
                scenegame.setOnKeyReleased(e -> {
                    double temp = Math.abs(playerid.getTranslateX() - xaxis);
                    double minimum = 100;
                    if (temp < minimum && playeridpostion == cherrybollean) {
                        count+=1;
                    }

                    translate.setOnFinished(e2 -> {
                        if ((m[0] < distance1 - width1) || (m[0] > (width2 + (distance1 - width1))) && flag4) {
                            String path4 = "/player fall.mp3";
                            Media fallmedia = new Media(getClass().getResource(path4).toExternalForm());
                            playerfall = new MediaPlayer(fallmedia);
                            playerfall.setAutoPlay(true);
                            playerfall.play();
                            TranslateTransition tt = new TranslateTransition();
                            tt.setNode(playerid);
                            tt.setFromY(translate.getFromY());
                            tt.setToY(playerid.getTranslateY() + 300);
                            tt.play();
                            System.out.println("Game over");
                            try {
                                this.showexit(event);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            flag2 = false;
                        } else {
                            flag = false;
                            flag2 = false;
                            TranslateTransition tt = new TranslateTransition(Duration.millis(3000));
                            tt.setDuration(Duration.millis(2000));
                            tt.setNode(playerid);
                            int d2 = (int) ((distance1 - width2 - 40) - playerid.getTranslateX());
                            try {
                                this.showexit(event);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            if (-10 < d2 && d2 < 10) {
                                System.out.println("in");
                                tt.setFromX(playerid.getTranslateX());
                                tt.setToX(distance1 - 40);
                                tt.play();
                                tt.setOnFinished(e1 -> {
                                    try {
                                        this.repeat(event);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                });
                            } else {
                                System.out.println("Repeat");
                                try {
                                    this.repeat(event);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            }
                        }
                    });
                });
            }

        });
        stagegame.setFullScreenExitHint("");
        stagegame.setScene(scenegame);
        stagegame.setFullScreen(true);
        stagegame.show();

    }


    public void closedgame() {
        stagegame.setOnCloseRequest(e -> {
            for(int i=0;i<collectionofstage.size();++i)
            {
                Stage s10=(Stage)collectionofstage.get(i);
                s10.close();
                collectionofstage.remove(i);
            }
        });
    }

    public void exit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gamescreen.fxml"));
        stagegame = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("You are about to exit");
        alert.setContentText("Do you want to exit");
        if (alert.showAndWait().get() == ButtonType.OK) {
            System.out.println("You exitted");
            if(playerinfo!=null) {
                int m = ScreenController.playerinfo.getTotalCheries() + ScreenController.count;
                ScreenController.playerinfo.setTotal_cheries(m);
                if (ScreenController.count > ScreenController.playerinfo.getHighestpoint()) {
                    ScreenController.playerinfo.setHighestpoint(ScreenController.count);

                }
                Playerinfo.settingPlayerinfo(ScreenController.name, ScreenController.playerinfo);
            }
            for(int i=0;i<collectionofstage.size();++i)
            {
                Stage s10=(Stage)collectionofstage.get(i);
                s10.close();
                collectionofstage.remove(i);
            }
            Homescreen.stagehome.close();

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void switchtohomescreen(ActionEvent event) throws Exception {
        Homescreen s1 = Homescreen.getHomescreen();
        ScreenController.stagegame.close();
        if(playerinfo!=null) {
            int m = ScreenController.playerinfo.getTotalCheries() + ScreenController.count;
            ScreenController.playerinfo.setTotal_cheries(m);
            if (ScreenController.count > ScreenController.playerinfo.getHighestpoint()) {
                ScreenController.playerinfo.setHighestpoint(ScreenController.count);

            }
            Playerinfo.settingPlayerinfo(ScreenController.name, ScreenController.playerinfo);
        }
        for(int i=0;i<collectionofstage.size();++i)
        {
            Stage s10=(Stage)collectionofstage.get(i);
            s10.close();
            collectionofstage.remove(i);
        }
        s1.start(Homescreen.stagehome);


    }
}
