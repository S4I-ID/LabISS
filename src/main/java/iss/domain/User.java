package iss.domain;

import java.util.List;

public class User {
    private int id;
    private String name;
    private String password;
    private String address;
    private String phoneNumber;
    private String cnp;
    private Boolean isLibrarian;
    private List<Object> books; //TODO not implemented along with its functions

    public List<Object> getBooks() {
        return books;
    }

    public void setBooks(List<Object> books) {
        this.books = books;
    }

    public void addBook(Object book) {

    }

    public void removeBook(Object book) {

    }
    //TODO END
    public User(String name, String password, String address, String phoneNumber, String cnp) {
        this.name=name;
        this.password=password;
        this.address=address;
        this.phoneNumber=phoneNumber;
        this.cnp=cnp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Boolean getLibrarian() {
        return isLibrarian;
    }

    public void setLibrarian(Boolean librarian) {
        isLibrarian = librarian;
    }
}
