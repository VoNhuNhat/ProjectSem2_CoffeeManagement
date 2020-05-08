/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author tranq
 */
public class Customer {

    int CusId;
    String Fullname;
    String PhoneNumber;
    String Email;
    int Gender;
    Date ArriveDate;
    Time ArriveHour;
    int Status;
    Date CreatedDate;
    Date UpdatedDate;

    public Customer() {
    }

    public Customer(int CusId, String Fullname, String PhoneNumber, String Email, int Gender, Date ArriveDate, Time ArriveHour, int Status, Date CreatedDate, Date UpdatedDate) {
        this.CusId = CusId;
        this.Fullname = Fullname;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Gender = Gender;
        this.ArriveDate = ArriveDate;
        this.ArriveHour = ArriveHour;
        this.Status = Status;
        this.CreatedDate = CreatedDate;
        this.UpdatedDate = UpdatedDate;
    }

    public int getCusId() {
        return CusId;
    }

    public void setCusId(int CusId) {
        this.CusId = CusId;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
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

    public Date getArriveDate() {
        return ArriveDate;
    }

    public void setArriveDate(Date ArriveDate) {
        this.ArriveDate = ArriveDate;
    }

    public Time getArriveHour() {
        return ArriveHour;
    }

    public void setArriveHour(Time ArriveHour) {
        this.ArriveHour = ArriveHour;
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

   
    
}
