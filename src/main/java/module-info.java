module com.example.gestion_image {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires jakarta.xml.bind;
    requires com.sun.xml.bind;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.dataformat.xml;

    opens com.example.gestion_image to javafx.fxml;
    exports com.example.gestion_image;
}






