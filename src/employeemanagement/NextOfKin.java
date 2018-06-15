/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagement;

import java.util.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author MOILE
 */
public class NextOfKin {

    SimpleStringProperty employeeId,firstName, surname, omang, email, telephone, cellphone, relationship, postalAddress, physicalAddress;
    
    
    public NextOfKin() 
    {
            employeeId = new SimpleStringProperty("");
            firstName = new SimpleStringProperty("");
            surname = new SimpleStringProperty("");
            omang = new SimpleStringProperty("");
            email = new SimpleStringProperty("");
            telephone = new SimpleStringProperty("");
            cellphone = new SimpleStringProperty("");
            relationship = new SimpleStringProperty("");
            postalAddress = new SimpleStringProperty("");
            physicalAddress = new SimpleStringProperty("");
    }
    
    public NextOfKin(String empId,String firstName,String surname,String omang,String email,String telephone,
            String cellphone,String relationship,String postalAddress,String physicalAddress)
    {   
            this.employeeId = new SimpleStringProperty(empId);
            this.firstName = new SimpleStringProperty(firstName);
            this.surname = new SimpleStringProperty(surname);
            this.omang = new SimpleStringProperty(omang);
            this.email = new SimpleStringProperty(email);
            this.telephone = new SimpleStringProperty(telephone);
            this.cellphone = new SimpleStringProperty(cellphone);
            this.relationship = new SimpleStringProperty(relationship);
            this.postalAddress = new SimpleStringProperty(postalAddress);
            this.physicalAddress = new SimpleStringProperty(physicalAddress);
    }
    
    public SimpleStringProperty getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(SimpleStringProperty employeeId) {
        this.firstName = employeeId;
    }

    public SimpleStringProperty getFirstName() {
        return firstName;
    }

    public void setFirstName(SimpleStringProperty firstName) {
        this.firstName = firstName;
    }

 

    public SimpleStringProperty getSurname() {
        return surname;
    }

    public void setSurname(SimpleStringProperty surname) {
        this.surname = surname;
    }

    public SimpleStringProperty getOmang() {
        return omang;
    }

    public void setOmang(SimpleStringProperty omang) {
        this.omang = omang;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }

    public void setEmail(SimpleStringProperty email) {
        this.email = email;
    }

    public SimpleStringProperty getTelephone() {
        return telephone;
    }

    public void setTelephone(SimpleStringProperty telephone) {
        this.telephone = telephone;
    }

    public SimpleStringProperty getCellphone() {
        return cellphone;
    }

    public void setCellphone(SimpleStringProperty cellphone) {
        this.cellphone = cellphone;
    }

    public SimpleStringProperty getRelationship() {
        return relationship;
    }

    public void setRelationship(SimpleStringProperty relationship) {
        this.relationship = relationship;
    }

    public SimpleStringProperty getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(SimpleStringProperty postalAddress) {
        this.postalAddress = postalAddress;
    }

    public SimpleStringProperty getPhysicalAddress() {
        return physicalAddress;
    }

    public void setPhysicalAddress(SimpleStringProperty physicalAddress) {
        this.physicalAddress = physicalAddress;
    }
    
    
}
