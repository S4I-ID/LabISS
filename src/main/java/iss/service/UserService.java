package iss.service;

import iss.domain.User;
import iss.repository.UserRepository;
import iss.service.validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository repository;
    private final UserValidator validator;

    public UserService(UserRepository repository) {
        this.repository=repository;
        this.validator = new UserValidator();
    }

    public void add(User user) throws Exception {
        validator.validate(user);
        repository.add(user);
    }

    public User delete(Integer id) throws Exception {
        return repository.delete(id);
    }

    public User login(Integer id, String password) throws Exception {
        User user = repository.find(id);
        if (password.equals(user.getPassword()))
            return user;
        else
            throw new Exception("Invalid login!");
    }

    public List<User> getAllNonLibrarians() throws Exception {
        List<User> filteredList = getAll()
                .stream()
                .filter(user -> !user.getLibrarian())
                .collect(Collectors.toList());
        return filteredList;
    }

    public List<User> getAll() throws Exception {
        return repository.getAll();
    }
}
