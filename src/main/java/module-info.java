module com.example.gestion_image {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestion_image to javafx.fxml;
    exports com.example.gestion_image;
}