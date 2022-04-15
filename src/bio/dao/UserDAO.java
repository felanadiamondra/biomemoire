/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.dao;
import java.sql.SQLException;
import bio.models.User;
import java.util.List;

/**
 *
 * @author Diamondra
 */
public interface UserDAO {
    public int add(User user)
            throws SQLException ;
    
    public void delete(int id)
            throws SQLException;
    public User getUser(int id)
            throws SQLException;
    public List<User> getUsers()
            throws SQLException;
    public void update(User user)
            throws SQLException;
}
