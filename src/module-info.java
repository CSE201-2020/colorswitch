module colorswitch {
    requires javafx.fxml;
    requires javafx.controls;

    exports sample.Obstacles;
    opens sample.Obstacles to javafx.graphics;

    opens sample;
}