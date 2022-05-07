package iss.repository;

import iss.domain.User;
import iss.repository.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository<User> {
    private DbUtils dbUtils;
    public UserRepository() {
        dbUtils = new DbUtils("repo.properties");
    }

    @Override
    public User find(Integer id) throws Exception {
        Connection con = dbUtils.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement
                    ("SELECT * FROM Users WHERE id = ?");
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            result.next();

            User user = new User(result.getString("name"),result.getString("password"),
                    result.getString("address"),result.getString("phoneNumber"),
                    result.getString("cnp"));
            user.setId(id);
            user.setLibrarian(result.getBoolean("isLibrarian"));
            result.close();
            return user;
        } catch (SQLException e) {
            throw new Exception("User not found\n");
        }
    }

    @Override
    public void add(User user) throws Exception {
        Connection con = dbUtils.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement
                    ("INSERT INTO Users(id,password,name,address,phoneNumber,cnp,isLibrarian) VALUES(?,?,?,?,?,?,?)");
            statement.setInt(1,user.getId());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getName());
            statement.setString(4, user.getAddress());
            statement.setString(5,user.getPhoneNumber());
            statement.setString(6,user.getCnp());
            statement.setBoolean(7,user.getLibrarian());
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Adding user failed!");
        }
    }

    @Override
    public User delete(Integer id) throws Exception {
        Connection con = dbUtils.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement
                    ("DELETE FROM Users WHERE id = ?");
            statement.setInt(1,id);
            User deleted = find(id);
            int result = statement.executeUpdate();
            return deleted;
        }
        catch (Exception ex) {
            throw new Exception("No such ID exists!");
        }
    }

    @Override
    public List<User> getAll() throws Exception {
        Connection con = dbUtils.getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement
                    ("SELECT * FROM Users");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                User user = new User(result.getString("name"), result.getString("password"),
                        result.getString("address"), result.getString("phoneNumber"),
                        result.getString("cnp"));
                user.setId(result.getInt("id"));
                user.setLibrarian(result.getBoolean("isLibrarian"));
                users.add(user);
            }
            result.close();
        }
        catch (SQLException e) {
            throw new Exception("User not found\n");
        }
        return users;
    }
}
