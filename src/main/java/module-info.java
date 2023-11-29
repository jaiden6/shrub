module com.panic.shrub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.panic.shrub to javafx.fxml;
    exports com.panic.shrub;
}
