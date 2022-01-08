package com.example.gestion_image;

public class FileNotSelectedException  extends Exception{
    public FileNotSelectedException()
    {
        super("Aucun fichier selectionné pour appliquer ce filtre");
    }
}
