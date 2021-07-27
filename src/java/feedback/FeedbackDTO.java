/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feedback;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author dell
 */
public class FeedbackDTO implements Serializable{
    private int id;
    private int productId;
    private String email;
    private String detail;
    private int rating;
    private boolean status;
    private Date date;

    public FeedbackDTO() {
    }

    public FeedbackDTO(int id, int productId, String email, String detail, int rating, boolean status, Date date) {
        this.id = id;
        this.productId = productId;
        this.email = email;
        this.detail = detail;
        this.rating = rating;
        this.status = status;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}
