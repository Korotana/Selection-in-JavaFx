module com.example.box2021demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.box2021demo to javafx.fxml;
    exports com.example.box2021demo;
}