package com.example.gestion_image;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import com.example.gestion_image.Image;



public class App {
    public static void main(String[] args)
    {
       try{
           ObjectMapper mapper = new XmlMapper();
           InputStream inputStream = new FileInputStream(new File("Ressources\\Image.xml"));
           TypeReference<List<Image>> typeReference = new TypeReference<List<Image>>() {};
           List<Image> images = mapper.readValue(inputStream, typeReference);

           for(Image p :images) {
               System.out.println("Marque = "+p.getMarque()+" Modele = "+p.getModele()+" Couleur = "+p.getCouleur()+" Annee = "+ p.getAnnee());
           }
           /*
           Image image1 = new Image();
           Image image2 = new Image();

           image1.setMarque("Ferrari");
           image1.setModele("GTR");
           image1.setCouleur("Noire");
           image1.setAnnee(2021);

           image2.setMarque("AlphaRomeo");
           image2.setModele("Retro");
           image2.setCouleur("Rouge");
           image2.setAnnee(2022);

           mapper.writeValue(new File("Ressources\\imageOut.xml"), image1);
           mapper.writeValue(new File("Ressources\\imageOut.xml"), image2);
           */
           inputStream.close();
       }
       catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
