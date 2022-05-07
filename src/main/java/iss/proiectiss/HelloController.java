package iss.proiectiss;

import iss.domain.User;
import iss.proiectiss.utils.BoxUI;
import iss.service.BookService;
import iss.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    private UserService userService;

    @FXML
    TextField usernameTextField;
    @FXML
    TextField passwordTextField;
    @FXML
    Button loginButton;

    @FXML
    private void loginClick(ActionEvent event) {
        try {
            User user = userService.login
                    ( Integer.valueOf(usernameTextField.getText()),passwordTextField.getText() );
            if (user.getLibrarian()) {
                AdminController adminController = new AdminController(userService, new BookService());
                adminController.setUser(user);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
                fxmlLoader.setController(adminController);
                Scene mainScene = new Scene(fxmlLoader.load());
                Stage mainStage = new Stage();
                mainStage.setTitle("Library administration");
                mainStage.setScene(mainScene);
                mainStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
            else {
                UserController userController = new UserController(new BookService());
                userController.setUser(user);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user.fxml"));
                fxmlLoader.setController(userController);
                Scene mainScene = new Scene(fxmlLoader.load());
                Stage mainStage = new Stage();
                mainStage.setTitle("Library client");
                mainStage.setScene(mainScene);
                mainStage.show();
                ((Node) (event.getSource())).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            BoxUI.showError(e.getMessage());
        }
    }

    public void setUserService(UserService userService) {
        this.userService=userService;
    }
}