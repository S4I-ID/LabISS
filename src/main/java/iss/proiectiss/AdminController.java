package iss.proiectiss;

import iss.domain.User;
import iss.proiectiss.utils.BoxUI;
import iss.service.BookService;
import iss.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class AdminController {
    private User user;
    private BookService bookService;
    private UserService userService;
    private ObservableList<User> userList = FXCollections.observableArrayList();
    private ObservableList<Object> bookList = FXCollections.observableArrayList();

    public AdminController(UserService userService, BookService bookService) {
        this.userService=userService;
        this.bookService=bookService;
    }

    public void setUser(User user) {
        this.user=user;
    }
    @FXML
    TableView<User> userTableView;
    @FXML
    TableColumn<User,Integer> userIdTC;
    @FXML
    TableColumn<User,String> userNameTC;
    @FXML
    TableColumn<User,String> userAddressTC;
    @FXML
    TableColumn<User,String> userPhoneNumberTC;
    @FXML
    TableColumn<User,String> userCnpTC;
    @FXML
    Label userLabel;
    @FXML
    Button addUserButton;
    @FXML
    Button deleteUserButton;
    @FXML
    TextField userNameTextField;
    @FXML
    TextField userAddressTextField;
    @FXML
    TextField userCnpTextField;
    @FXML
    TextField userPhoneNumberTextField;
    @FXML
    TextField userPasswordTextField;

    @FXML
    private void initialize() {
        userLabel.setText("Logged in as librarian: " + user.getName());
        initializeTables();
    }

    private void initializeTables() {
        userIdTC.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        userNameTC.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        userAddressTC.setCellValueFactory(new PropertyValueFactory<User,String>("address"));
        userPhoneNumberTC.setCellValueFactory(new PropertyValueFactory<User,String>("phoneNumber"));
        userCnpTC.setCellValueFactory(new PropertyValueFactory<User,String>("cnp"));
        updateUserTable();
    }

    private void updateUserTable() {
        try {
            updateUserList(userService.getAllNonLibrarians());
        } catch (Exception e) {
            BoxUI.showError(e.getMessage());
        }
    }

    private void updateUserList(List<User> users) {
        userList.setAll(users);
        userTableView.setItems(userList);
    }

    @FXML
    private void addUserClick(ActionEvent event) {
        User user = new User(userNameTextField.getText(), userPasswordTextField.getText(),
                userAddressTextField.getText(), userPhoneNumberTextField.getText(),
                userCnpTextField.getText());
        user.setLibrarian(false);
        try {
            userService.add(user);
            updateUserTable();
            BoxUI.showInfo("User successfully added!");
        }
        catch (Exception e) {
            BoxUI.showError(e.getMessage());
        }
    }

    @FXML
    private void deleteUserClick (ActionEvent event) {
        User user = userTableView.getSelectionModel().getSelectedItem();
        if (user==null) {
            BoxUI.showWarning("No user selected!");
        }
        else {
            try {
                User deletedUser = userService.delete(user.getId());
                updateUserTable();
                BoxUI.showInfo("Deleted " + user.getName() + " with ID " + user.getId());
            }
            catch (Exception e) {
                BoxUI.showError(e.getMessage());
            }
        }

    }

}
