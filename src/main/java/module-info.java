module com.example.gestion_image {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires jakarta.xml.bind;
    requires com.sun.xml.bind;

    opens com.example.gestion_image to javafx.fxml;
    exports com.example.gestion_image;
}