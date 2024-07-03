package com.example.stick_hero;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Pause {
    @FXML

    static Stage pausedstage;
    @FXML
    static Parent pausedroot;
    @FXML
    static Scene pausedscene;
    @FXML
    private Button resumeid;
    @FXML
    private Button restartid;
    @FXML
    private Button exittomainmenuid;
    static boolean resumecondition=false;
    public void showpausedscreen() throws IOException {
            ScreenController.button.play();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pause.fxml"));
            pausedscene = new Scene(loader.load());
            pausedstage = new Stage();
            pausedstage.setScene(pausedscene);
            int m=0;
            pausedstage.show();
    }
    public void resume(ActionEvent event) throws IOException {
        ScreenController.button.play();
        ScreenController s1=ScreenController.getScreenController();
        pausedstage.close();
        s1.run(event);
        resumecondition=true;

    }
    Homescreen h1;
    public void switchtohomescreen(ActionEvent event) throws IOException {
        Homescreen s1=Homescreen.getHomescreen();
        ScreenController s2=ScreenController.getScreenController();
        exittomainmenuid.setOnAction(e->{
            try {
                Homescreen.roothome = FXMLLoader.load(getClass().getResource("homescreen.fxml"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Homescreen.stagehome=(Stage)((Node)event.getSource()).getScene().getWindow();
            Homescreen.scenehome=new Scene(Homescreen.roothome);
            pausedstage.close();
            for(int i=0;i<ScreenController.collectionofstage.size();++i)
            {
                Stage s10=(Stage)ScreenController.collectionofstage.get(i);
                s10.close();
                ScreenController.collectionofstage.remove(i);

            }
            if(ScreenController.playerinfo!=null) {
                int m = ScreenController.playerinfo.getTotalCheries() + ScreenController.count;
                ScreenController.playerinfo.setTotal_cheries(m);
                if (ScreenController.count > ScreenController.playerinfo.getHighestpoint()) {
                    ScreenController.playerinfo.setHighestpoint(ScreenController.count);

                }
                Playerinfo.settingPlayerinfo(ScreenController.name, ScreenController.playerinfo);
            }
            try {
                s1.start(Homescreen.stagehome);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            };
        });

    }
    public void restart(ActionEvent event)

    {
        ScreenController.button.play();
        restartid.setOnAction(e->{
            ScreenController.stagegame.close();
            ScreenController s1=ScreenController.getScreenController();
            try {
                for(int i=0;i<ScreenController.collectionofstage.size()-1;++i)
                {
                    Stage s10=(Stage)ScreenController.collectionofstage.get(i);
                    s10.close();
                    ScreenController.collectionofstage.remove(i);
                }
                if(ScreenController.playerinfo!=null) {
                    int m = ScreenController.playerinfo.getTotalCheries() + ScreenController.count;
                    ScreenController.playerinfo.setTotal_cheries(m);
                    if (ScreenController.count > ScreenController.playerinfo.getHighestpoint()) {
                        ScreenController.playerinfo.setHighestpoint(ScreenController.count);

                    }
                    Playerinfo.settingPlayerinfo(ScreenController.name, ScreenController.playerinfo);
                }
                pausedstage.close();
                s1.run2(event);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

    }





}
