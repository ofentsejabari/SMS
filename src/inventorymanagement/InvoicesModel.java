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
public class InvoicesModel {
    
    String invoiceNo,invoiceDate,supplierName;

    public InvoicesModel(String invoiceNo, String invoiceDate, String supplierName) {
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.supplierName = supplierName;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    
    
}
