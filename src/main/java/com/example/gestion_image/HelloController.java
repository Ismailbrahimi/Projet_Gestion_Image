package com.example.gestion_image;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {

    //creation de l'objet filechooser
    final FileChooser fc = new FileChooser();

    @FXML
    private Button btnOpenImgFile;

    @FXML
    private ImageView ivFiles;

    @FXML
    public void handleBtnOpenImgFile(ActionEvent event)
    {
    fc.setTitle("Title");
    fc.setInitialDirectory(new File("C:\\Users\\ps42\\IdeaProjects\\Gestion_Image\\Ressources"));
    fc.getExtensionFilters().clear();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));

    File file = fc.showOpenDialog(null);
    if(file!=null)
    {
        ivFiles.setImage(new Image(file.toURI().toString()));
    }
    else
    {
        System.out.println("A file is invalid");
    }
    }
}