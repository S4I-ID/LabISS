package iss.proiectiss;

import iss.domain.User;
import iss.service.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserController {
    private User user;
    private BookService bookService;

    public UserController(BookService bookService) {
        this.bookService=bookService;
    }

    public void setUser(User user) {
        this.user=user;
    }

    @FXML
    Label userLabel;

    @FXML
    private void initialize() {
        userLabel.setText("Logged in as: " + user.getName());
    }
}
