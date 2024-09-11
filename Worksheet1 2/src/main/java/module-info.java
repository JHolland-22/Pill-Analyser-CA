module com.example.worksheet1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires jdk.accessibility;
    requires junit;

    opens com.example.worksheet1 to javafx.fxml;
    exports com.example.worksheet1;
}