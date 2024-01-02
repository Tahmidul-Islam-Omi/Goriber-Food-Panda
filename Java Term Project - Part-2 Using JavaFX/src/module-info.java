module JavaTermProjectPart2withoutJavaFX {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens Restaurant to javafx.fxml , javafx.graphics;
    exports Restaurant ;
    opens Customer to javafx.fxml , javafx.graphics;
    exports Customer;
    opens Resources to javafx.base;

}