module colorswitch {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.media;

    exports sample.Obstacles;
    opens sample.Obstacles to javafx.graphics;

    opens sample;
}