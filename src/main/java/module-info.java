module iss.proiectiss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens iss.proiectiss to javafx.fxml;
    opens iss.domain to javafx.base;
    exports iss.proiectiss;
    exports iss.service;
    exports iss.repository;
    exports iss.domain;
}