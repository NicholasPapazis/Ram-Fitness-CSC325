module com.example.ramfitnesscsc325 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jdk.jsobject;
    requires java.logging;
    requires javafx.web;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.api.apicommon;

    requires google.cloud.core;
    requires com.google.auth;
    requires java.desktop;
    requires java.mail;
    requires jdk.compiler;


    /*opens com.example.ramfitnesscsc325 to javafx.fxml;
    exports com.example.ramfitnesscsc325;
    exports viewmodel;
    opens viewmodel to javafx.fxml;*/
    opens viewmodel;
    exports viewmodel;
    opens dao;
    exports dao;
    opens model;
    exports model;

}