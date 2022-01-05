package com.example.gestion_image;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class HelloController {

    //creation de l'objet filechooser
    public FileChooser fc = new FileChooser();

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
    public void handleBtnOpenImgFile(ActionEvent event)
    {
    fc.setTitle("Title");
    //l3ezzi
    fc.setInitialDirectory(new File("C:\\Users\\ps42\\IdeaProjects\\Gestion_Image\\Ressources"));
    // mo7çine fc.setInitialDirectory(new File("F:\ProjetPOO\Ressources"));
    // zakie fc.setInitialDirectory(new File("C:\Users\z_aki\OneDrive\Bureau\Mes études\2020-2021 dirasa\Java\Projet_Gestion_Image\Ressources"));
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

    public void handleNoireBlanc() {

        Image imgIN = ivFiles.getImage();
        int h= (int) imgIN.getHeight();
        int w= (int) imgIN.getWidth();
        System.out.println("H : "+h+" W : "+w);

        WritableImage imgOUT = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Color colorImgIN = imgIN.getPixelReader().getColor(j,i) ;
                Color colorImgOUT = colorImgIN.grayscale();
                imgOUT.getPixelWriter().setColor(j,i,colorImgOUT);
            }
        }
        ivFiles.setImage(imgOUT);
    }

    public void handleRGBtoBRG() {

        Image imgIN1 = ivFiles.getImage();
        int h= (int) imgIN1.getHeight();
        int w= (int) imgIN1.getWidth();
        System.out.println("H : "+h+" W : "+w);

        WritableImage imgOUT1 = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Color colorImgIN1 = imgIN1.getPixelReader().getColor(j,i) ;
                Color colorImgOUT1 =new Color(colorImgIN1.getBlue(),colorImgIN1.getRed(),colorImgIN1.getGreen(),1);
                imgOUT1.getPixelWriter().setColor(j,i,colorImgOUT1);
            }
        }
        ivFiles.setImage(imgOUT1);
    }
    public void handleSepia() {

        Image imgIN2 = ivFiles.getImage();
        int h= (int) imgIN2.getHeight();
        int w= (int) imgIN2.getWidth();
        System.out.println("H : "+h+" W : "+w);

        WritableImage imgOUT2 = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++)
            {
                Color colorImgIN2 = imgIN2.getPixelReader().getColor(j,i) ;
                int r =(int)colorImgIN2.getRed();
                int g =(int)colorImgIN2.getGreen();
                int b =(int)colorImgIN2.getBlue();

                int tr =(int)(0.393*(r)+0.769*(g)+0.189*(b));
                int tg =(int)(0.349*(r)+0.686*(g)+0.168*(b));
                int tb =(int)(0.272*(r)+0.534*(g)+0.131*(b));

                //verification des conditions
                if(tr>255)
                {
                    r=255;
                }
                if(tg>255)
                {
                    g=tg;
                }
                if(tb>255)
                {
                    b=255;
                }
                else
                {
                    b=tb;
                }


//push 2.0
                Color colorImgOUT2 =new Color(tr,tg,tb,1);
                imgOUT2.getPixelWriter().setColor(j,i,colorImgOUT2);
            }
        }
        ivFiles.setImage(imgOUT2);
    }

}