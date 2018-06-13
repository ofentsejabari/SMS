/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanagement;

/**
 *
 * @author MOILE
 */
public class BookCopies {
    
    String copyNo;
    String serialNo;
    String bookLocation;
    String edition;
    String supplier,cost;

    public BookCopies(){
        this.copyNo = "";
        this.serialNo = "";
        this.bookLocation = "";
        this.edition = "";
        this.supplier="";
        this.cost=cost;
    }
    
    public BookCopies(String copyNo, String serialNo, String bookLocation, String edition, String supplier,String cost) {
        this.copyNo = copyNo;
        this.serialNo = serialNo;
        this.bookLocation = bookLocation;
        this.edition = edition;
        this.supplier=supplier;
        this.cost=cost;
    }

    public String getCopyNo() {
        return copyNo;
    }

    public void setId(String copyNo) {
        this.copyNo= copyNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getBookLocation() {
        return bookLocation;
    }

    public void setBookLocation(String bookLocation) {
        this.bookLocation = bookLocation;
    }

    public String getPublisher() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
    
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    
      public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}
