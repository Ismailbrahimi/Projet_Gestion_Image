package com.example.gestion_image;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class HelloController2 {

    //creation de l'objet filechooser
    public Parent root;
    public Stage stage;
    public Scene scene;
    public String selected;


    public ImageView ivFiles;

    private AnchorPane listImg;

    //redirection
       public void redirectToFilters(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
           ImageView iv = (ImageView) mouseEvent.getSource();
           System.out.println(" IMAGE VIEW : "+iv.getImage());
        selected = (String) mouseEvent.getSource().toString();
        System.out.println(selected);

        FXMLLoader loader= new FXMLLoader(getClass().getResource("Accueil.fxml"));
        root= loader.load();
        HelloController hw1 = loader.getController();
           System.out.println("Sent : "+iv.getImage().getUrl().toString());
           hw1.setPreloadedImage(iv.getImage());


           stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
           scene = new Scene(root);
           stage.setScene(scene);
           stage.show();


    }
}