module com.example.javachatroom {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.javachatroom to javafx.fxml;
    exports com.example.javachatroom;
}