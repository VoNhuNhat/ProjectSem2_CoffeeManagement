/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author tranq
 */
public class UserAccount {

    int UserId;
    String Fullname;
    String Username;
    String Password;
    String PhoneNumber;
    String Email;
    int Gender;
    String Address;
    int Status;
    String ImageUser;
    Date CreatedDate;
    Date UpdatedDate;

    public UserAccount() {
    }

    public UserAccount(int UserId, String Fullname, String Username, String Password, String PhoneNumber, String Email, int Gender, String Address, int Status, String ImageUser, Date CreatedDate, Date UpdatedDate) {
        this.UserId = UserId;
        this.Fullname = Fullname;
        this.Username = Username;
        this.Password = Password;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Gender = Gender;
        this.Address = Address;
        this.Status = Status;
        this.ImageUser = ImageUser;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getGender() {
        return Gender;
    }

    public void setGender(int Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public Date getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public String getImageUser() {
        return ImageUser;
    }

    public void setImageUser(String ImageUser) {
        this.ImageUser = ImageUser;
    }
    
    
}
