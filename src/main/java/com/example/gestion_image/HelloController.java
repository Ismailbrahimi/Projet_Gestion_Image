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
    public  Image img;
    int h;
    int w;


    @FXML
    private Button btnOpenImgFile;

    @FXML
    private ImageView ivFiles;



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
    //fc.setInitialDirectory(new File("C:\\Users\\ps42\\IdeaProjects\\Gestion_Image\\Ressources"));
    fc.setInitialDirectory(new File("F:\\ProjetPOO\\Ressources"));
    // zakie fc.setInitialDirectory(new File("C:\Users\z_aki\OneDrive\Bureau\Mes études\2020-2021 dirasa\Java\Projet_Gestion_Image\Ressources"));
    fc.getExtensionFilters().clear();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));

    File file = fc.showOpenDialog(null);
    if(file!=null)
    {
        ivFiles.setImage(new Image(file.toURI().toString()));
        img =new Image(file.toURI().toString());
        h= (int) img.getHeight();
        w= (int) img.getWidth();
    }
    else
    {
        System.out.println("A file is invalid");
    }
    }


    public void handleOriginale() {

        Image imgIN = ivFiles.getImage();
        int h= (int) imgIN.getHeight();
        int w= (int) imgIN.getWidth();
        System.out.println("H : "+h+" W : "+w);

        WritableImage imgOUT = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Color colorImgIN = imgIN.getPixelReader().getColor(j,i) ;
                Color colorImgOUT = colorImgIN;
                imgOUT.getPixelWriter().setColor(j,i,colorImgOUT);
            }
        }
        ivFiles.setImage(imgOUT);
    }
    public void handleNoireBlanc() {

        WritableImage imgOUT = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Color colorImgIN = img.getPixelReader().getColor(j,i) ;
                Color colorImgOUT = colorImgIN.grayscale();
                imgOUT.getPixelWriter().setColor(j,i,colorImgOUT);
            }
        }
        ivFiles.setImage(imgOUT);
    }

    public void handleRGBtoBRG() {


        WritableImage imgOUT1 = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++) {
                Color colorImgIN1 = img.getPixelReader().getColor(j,i) ;
                Color colorImgOUT1 =new Color(colorImgIN1.getBlue(),colorImgIN1.getRed(),colorImgIN1.getGreen(),1);
                imgOUT1.getPixelWriter().setColor(j,i,colorImgOUT1);
            }
        }
        ivFiles.setImage(imgOUT1);
    }

    public void handleSepia() {

        WritableImage imgOUT2 = new WritableImage(w,h);

        for(int i = 0; i < h; i++) {
            for(int j = 0; j < w; j++)
            {
                int pixel =img.getPixelReader().getArgb(j,i);

                //Decomposer la combinaison RGB en decomposant la suite binaire de 32bits par 8bits chacune
                int opacity = (pixel>>24)&0xff;
                int red = (pixel>>16)&0xff;
                int green= (pixel>>8)&0xff;
                int blue = pixel&0xff;

                Color colorImgIN = img.getPixelReader().getColor(j,i) ;

                //Calculer la nouvelle combinaison RGB
                int newRed =(int)(0.393*(red)+0.769*(green)+0.189*(blue));
                int newGreen =(int)(0.349*(red)+0.686*(green)+0.168*(blue));
                int newBlue =(int)(0.272*(red)+0.534*(green)+0.131*(blue));

                //Conditions
                if(newRed>255)
                {
                    red=255;
                }else{
                    red=newRed;
                }
                if(newGreen>255)
                {
                    green=255;
                }else{
                    green=newGreen;
                }
                if(newBlue>255)
                {
                    blue=255;
                }else
                {
                    blue=newBlue;
                }

                //Recréer le pixel par la nouvelle combinaision RGB
                pixel = (opacity<<24) | (red<<16) | (green<<8) | blue;
                imgOUT2.getPixelWriter().setArgb(j,i,pixel);

            }
        }
        ivFiles.setImage(imgOUT2);
    }

}