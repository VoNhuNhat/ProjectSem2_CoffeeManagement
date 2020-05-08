/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import data.UserAccount;
import java.util.List;

/**
 *
 * @author tranq
 */
public interface UserDAO {

    public UserAccount findUserByUsername(String username);

    public List<UserAccount> checkExistUsername(String oldUsername, String newUsername);

    public List<UserAccount> checkExistPhoneNumber(String oldPhoneNumber, String newPhoneNumber);

    public List<UserAccount> checkExistEmail(String oldEmail, String newEmail);

    public boolean createNewUser(UserAccount ua);

    public boolean findPhoneNumber(String phoneNumber);

    public boolean findEmail(String email);

    public List<UserAccount> getAllUsers();

    public boolean updateUser(UserAccount ua);

    public boolean deleteUser(String username);

    public boolean updatePasswordUser(int userId, String newPassword);

    public UserAccount getUserByUserId(int userId);

}
