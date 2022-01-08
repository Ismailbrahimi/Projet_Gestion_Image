package com.example.gestion_image;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1240, 680);
            stage.setTitle("Gestion d'image");
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        launch();
    }//
}


