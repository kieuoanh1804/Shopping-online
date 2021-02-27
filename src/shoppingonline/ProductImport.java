/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class ProductImport {
    private int id;
    private Provider provider;
    private ImportDetail importDetail;
    private Date importDate;
    private boolean status;

    public ProductImport(int id, Provider provider, ImportDetail importDetail, Date importDate, boolean status) {
        this.id = id;
        this.provider = provider;
        this.importDetail = importDetail;
        this.importDate = importDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Provider getProvider() {
        return provider;
    }

    public ImportDetail getImportDetail() {
        return importDetail;
    }

    public Date getImportDate() {
        return importDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setImportDetail(ImportDetail importDetail) {
        this.importDetail = importDetail;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
            
}
