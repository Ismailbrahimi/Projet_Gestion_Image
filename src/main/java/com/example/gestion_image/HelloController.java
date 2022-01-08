package com.example.gestion_image;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import java.io.File;
import java.util.List;

import static java.lang.Math.round;


public class HelloController {

    //creation de l'objet filechooser
    public Parent fxml;
    public AnchorPane anchor;
    public FileChooser fc = new FileChooser();
    public File file;
    public  Image img;
    public TextField recherche;
    public TextField rechercheMain;
    public GridPane gridpane;
    public ListView listV;
    public String selected;

    int h ;
    int w ;
    boolean error=false;


    @FXML
    private Button btnOpenImgFile;
    @FXML
    public ImageView ivFiles;

    @FXML
    public ImageView img1;

    @FXML
    private Button myLeft;
    @FXML
    private Button myRight;
    @FXML
    private AnchorPane listImg;


    public void setPreloadedImage(Image pic){
        String old = pic.getUrl().toString();
        String newt = old.replace("file:","").replace("/","\\");

        file = new File(newt);
        img = pic;
        h=(int) img.getHeight();
        w= (int) img.getWidth();
        ivFiles.setImage(img);
    }

    public void setPreloadedImage2(Image pic){

    }

    @FXML
    public void rotateImageRight()
    {
        if(!error) {
            System.out.println("File from : "+file.toString());
            ivFiles.setRotate(ivFiles.getRotate() + 90);
        }
    }

    @FXML
    public void rotateImageLeft()
    {
        if(!error) {
            ivFiles.setRotate(ivFiles.getRotate() - 90);
        }
    }

    @FXML
    public void handleSymetrie() {
        if(!error) {
            if (ivFiles.getScaleX() == 1) {
                ivFiles.setScaleX(-1);
            } else {
                ivFiles.setScaleX(1);
            }
        }

    }

    @FXML
    public void handleBtnOpenImgFile(ActionEvent event)
    {
        listV.setVisible(false);
        fc.setTitle("Title");

    fc.setInitialDirectory(new File("Ressources"));
    fc.getExtensionFilters().clear();
    fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif"));
    file = fc.showOpenDialog(null);

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
        if(!error) {

            ivFiles.setImage(img);
        }
    }

    public void handleNoireBlanc() throws FileNotSelectedException{
        if(!error) {
            try {
                WritableImage imgOUT = new WritableImage(w, h);
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        Color colorImgIN = img.getPixelReader().getColor(j, i);
                        Color colorImgOUT = colorImgIN.grayscale();
                        imgOUT.getPixelWriter().setColor(j, i, colorImgOUT);
                    }
                }
                ivFiles.setImage(imgOUT);
            } catch (Exception e) {
                System.out.println("Erreur : Aucune image selectionné pour appliquer ce filtre");
            }
        }
    }

    public void handleRGBtoBRG() {
        if(!error) {
            try {
                WritableImage imgOUT1 = new WritableImage(w, h);

                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        Color colorImgIN1 = img.getPixelReader().getColor(j, i);
                        Color colorImgOUT1 = new Color(colorImgIN1.getBlue(), colorImgIN1.getRed(), colorImgIN1.getGreen(), 1);
                        imgOUT1.getPixelWriter().setColor(j, i, colorImgOUT1);
                    }
                }
                ivFiles.setImage(imgOUT1);
            } catch (Exception e) {
                System.out.println("Erreur : Aucune image selectionné pour appliquer ce filtre");
            }
        }
    }

    public void handleSepia() {
        if(!error) {
            try {
                WritableImage imgOUT2 = new WritableImage(w, h);

                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        int pixel = img.getPixelReader().getArgb(j, i);

                        //Decomposer la combinaison RGB en decomposant la suite binaire de 32bits par 8 bits chacune
                        int opacity = (pixel >> 24) & 0xff;
                        int red = (pixel >> 16) & 0xff;
                        int green = (pixel >> 8) & 0xff;
                        int blue = pixel & 0xff;

                        Color colorImgIN = img.getPixelReader().getColor(j, i);

                        //Calculer la nouvelle combinaison RGB
                        int newRed = (int) (0.393 * (red) + 0.769 * (green) + 0.189 * (blue));
                        int newGreen = (int) (0.349 * (red) + 0.686 * (green) + 0.168 * (blue));
                        int newBlue = (int) (0.272 * (red) + 0.534 * (green) + 0.131 * (blue));

                        //Conditions
                        if (newRed > 255) {
                            red = 255;
                        } else {
                            red = newRed;
                        }
                        if (newGreen > 255) {
                            green = 255;
                        } else {
                            green = newGreen;
                        }
                        if (newBlue > 255) {
                            blue = 255;
                        } else {
                            blue = newBlue;
                        }

                        //Recréer le pixel par la nouvelle combinaision RGB
                        pixel = (opacity << 24) | (red << 16) | (green << 8) | blue;
                        imgOUT2.getPixelWriter().setArgb(j, i, pixel);

                    }
                }
                ivFiles.setImage(imgOUT2);
            } catch (Exception e) {
                System.out.println("Erreur : Aucune image selectionné pour appliquer ce filtre !");
            }
        }
    }

    public  int  niveauGris(int couleur) {
        // cette methode nous retourne le niveau du Gris
        int blue = (couleur) & 0xff;
        int green = (couleur >> 8) & 0xff;
        int red = (couleur >> 16) & 0xff;
        //https://en.wikipedia.org/wiki/Grayscale
        return ((green + red + blue) / 3);
    }


    public void handlePrewitt() throws IOException {
        if(!error) {
            try {
                //lire l'image en format Buffered
                BufferedImage bImg = ImageIO.read(file);
                System.out.println("BF read");
                int grade = -1;
                int[][] PriwettColors = new int[w][h];
                double echelle = 0;

                for (int i = 1; i < w - 1; i++) {
                    for (int j = 1; j < h - 1; j++) {

                        // on cherche le niveau du Gris
                        int niv[] =
                                {
                                        niveauGris(bImg.getRGB(i - 1, j - 1)), niveauGris(bImg.getRGB(i - 1, j)), niveauGris(bImg.getRGB(i - 1, j + 1)),
                                        niveauGris(bImg.getRGB(i, j - 1)), niveauGris(bImg.getRGB(i, j)), niveauGris(bImg.getRGB(i, j + 1)),
                                        niveauGris(bImg.getRGB(i + 1, j - 1)), niveauGris(bImg.getRGB(i + 1, j)), niveauGris(bImg.getRGB(i + 1, j + 1))
                                };

                        //Les operateurs de Prewitt
                        // Prewitt Horizontal
                        int PrewittX = ((1 * niv[0]) + (0 * niv[1]) + (-1 * niv[2])) + ((1 * niv[3]) + (0 * niv[4]) + (-1 * niv[5])) + ((1 * niv[6]) + (0 * niv[7]) + (-1 * niv[8]));
                        // Prewitt Vertical
                        int PrewittY = ((1 * niv[0]) + (1 * niv[1]) + (1 * niv[2])) + ((0 * niv[3]) + (0 * niv[4]) + (0 * niv[5])) + ((-1 * niv[6]) + (-1 * niv[7]) + (-1 * niv[8]));

                        // On calcule 'Hypotenuse' c=sqrt(a*a + b*b)
                        int Hypotenuse = (int) Math.sqrt((PrewittX * PrewittX) + (PrewittY * PrewittY));

                        if (Hypotenuse > grade) {
                            grade = Hypotenuse;
                        }

                        PriwettColors[i][j] = Hypotenuse;
                    }
                }

                echelle = (double) (255.0 / grade);

                for (int i = 1; i < w - 1; i++) {
                    for (int j = 1; j < h - 1; j++) {
                        int Pcolor = (int) (PriwettColors[i][j] * echelle);
                        //On calcule la nouvelle combinaison RGB
                        Pcolor = 0xff000000 | (Pcolor << 16) | (Pcolor << 8) | Pcolor;
                        bImg.setRGB(i, j, Pcolor);
                    }
                }


                // On ne sait pas comment convertir Buffered Image to Image directement, du coup on a l'enregiste comme un fichier(jpg) apres on la lit (Ca peut etre con)
                File PfileOUT = new File("PriwettIMAGE.jpg");

                ImageIO.write(bImg, "png", PfileOUT);

                File PfileIN = new File("PriwettIMAGE.jpg");
                //
                Image PriwettIMAGE = new Image(PfileIN.toURI().toString());
                ivFiles.setImage(PriwettIMAGE);
            } catch (Exception e) {
                System.out.println("Erreur : Aucune image selectionné pour appliquer ce filtre !");
            }
        }
    }

    public void handleRecherche() {

        try{
            listV.setVisible(false);
            listV.getItems().clear();

            String critere = recherche.getText();
            System.out.println("Recherche  : "+critere);
            ObjectMapper mapper = new XmlMapper();
            InputStream inputStream = new FileInputStream(new File("Ressources\\Image.xml"));
            TypeReference<List<com.example.gestion_image.Image>> typeReference = new TypeReference<List<com.example.gestion_image.Image>>() {};
            List<com.example.gestion_image.Image> images = mapper.readValue(inputStream, typeReference);

            int i=0;
            boolean x=true;

            for(com.example.gestion_image.Image p :images) {
                if(p.getModele().equals(critere) || p.getMarque().equals(critere) || p.getCouleur().equals(critere)|| p.getAnnee().equals(critere) ){

                    System.out.println("Marque = "+p.getMarque()+" Modele = "+p.getModele()+" Couleur = "+p.getCouleur()+" Annee = "+ p.getAnnee()+"  - URL : "+p.getUrl());
                    String path = "Ressources\\"+p.getUrl();

                    listV.setCellFactory(param -> new ListCell<com.example.gestion_image.Image>() {
                        @Override
                        protected void updateItem(com.example.gestion_image.Image p, boolean empty) {
                            super.updateItem(p, empty);

                            if (empty || p == null || p.getMarque() == null) {
                                setText(null);
                            } else {
                                String choix= p.getMarque()+" - "+ p.getModele()+" - "+p.getCouleur()+" - "+p.getAnnee();
                                setText(choix);
                            }
                        }
                    });

                    listV.getItems().add(p);
                    file = new File(path);
                    ivFiles.setImage(new Image(file.toURI().toString()));

                    //on affecte les infos de l'image aux variable pour appliquer les flters
                    img = new Image(file.toURI().toString());
                    h= (int) img.getHeight();
                    w= (int) img.getWidth();
                    x=false;

                    if(i>0){
                        listV.setVisible(true);
                    }
                    i++;
                    error=false;
                }

            }
            if(x){
                listV.setVisible(false);
                File errorf = new File("Ressources\\error.png");
                error=true;
                ivFiles.setImage(new Image(errorf.toURI().toString()));


            }


            inputStream.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public void handleRechercheMain()
    {
    try {
            String critere = rechercheMain.getText();
            System.out.println("Recherche  : " + critere);
            ObjectMapper mapper = new XmlMapper();
        InputStream inputStream = new FileInputStream(new File("Ressources\\Image.xml"));
        TypeReference<List<com.example.gestion_image.Image>> typeReference = new TypeReference<List<com.example.gestion_image.Image>>() {};
        List<com.example.gestion_image.Image> images = mapper.readValue(inputStream, typeReference);
        for(com.example.gestion_image.Image p :images) {

            if (p.getModele().equals(critere) || p.getMarque().equals(critere) || p.getCouleur().equals(critere) || p.getAnnee().equals(critere)) {

                System.out.println("Marque = " + p.getMarque() + " Modele = " + p.getModele() + " Couleur = " + p.getCouleur() + " Annee = " + p.getAnnee() + "  - URL : " + p.getUrl());
                String path = "Ressources\\" + p.getUrl();
            }
        }
    }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void handleChoice() {
        Object selected =  listV.getSelectionModel().getSelectedItem();
        com.example.gestion_image.Image p = (com.example.gestion_image.Image)selected;
        System.out.println("You choosed :  " + ((com.example.gestion_image.Image) selected).getCouleur());
        String path = "Ressources\\"+p.getUrl();

        file = new File(path);

        ivFiles.setImage(new Image(file.toURI().toString()));

        //on affecte les infos de l'image aux variable pour appliquer les flters
        img = new Image(file.toURI().toString());
        h= (int) img.getHeight();
        w= (int) img.getWidth();

    }

}