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
    
    //
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
    
    public String getEmployeeId() {
        return employeeId.get();
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId.set(employeeId);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

 

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getOmang() {
        return omang.get();
    }

    public void setOmang(String omang) {
        this.omang.set(omang);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getTelephone() {
        return telephone.get();
    }

    public void setTelephone(String telephone) {
        this.telephone.set(telephone);
    }

    public String getCellphone() {
        return cellphone.get();
    }

    public void setCellphone(String cellphone) {
        this.cellphone.set(cellphone);
    }

    public String getRelationship() {
        return relationship.get();
    }

    public void setRelationship(String relationship) {
        this.relationship.set(relationship);
    }

    public String getPostalAddress() {
        return postalAddress.get();
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress.set(postalAddress);
    }

    public String getPhysicalAddress() {
        return physicalAddress.get();
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress.set(physicalAddress);
    }
    
    
}
