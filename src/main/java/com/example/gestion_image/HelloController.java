package com.example.gestion_image;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.Writer;

public class HelloController {

    //creation de l'objet filechooser
    final FileChooser fc = new FileChooser();

    @FXML
    private Button btnOpenImgFile;

    @FXML
    private ImageView ivFiles;
    @FXML
    private ImageView ivFiles2;


    @FXML
    private Button myLeft;

    @FXML
    private Button myRight;

    @FXML
    public void rotateImageRight()
    {
            ivFiles.setRotate(ivFiles.getRotate()+90);
    }

    @FXML
    public void rotateImageLeft()
    {
        ivFiles.setRotate(ivFiles.getRotate()-90);
    }

    @FXML
    public void handleSymetrie() {
        if(ivFiles.getScaleX()==1){
            ivFiles.setScaleX(-1);
        }else {
            ivFiles.setScaleX(1);
        }


    }

    @FXML
    public void handleBtnOpenImgFile()
    {
    fc.setTitle("Title");
    fc.setInitialDirectory(new File("F:\\ProjetPOO\\Ressources"));
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

    public void handleEffet() {
        System.out.println("--------1");

        Image imgIN = ivFiles.getImage();
        int h= (int) imgIN.getHeight();
        int w= (int) imgIN.getWidth();
        System.out.println("H : "+h+" W : "+w);

        WritableImage imgOUT = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {

                Color colorImgIN = imgIN.getPixelReader().getColor(j,i) ;
                Color colorImgOUT = colorImgIN.invert();

                imgOUT.getPixelWriter().setColor(j,i,colorImgOUT);

            }
        }
        ivFiles2.setImage(imgOUT);
    }
}