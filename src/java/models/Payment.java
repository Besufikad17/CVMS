package models;

import java.sql.Date;

public class Payment {
    
    private int id;
    
    private int customer_id;
    
    private int organization_id;
    
    private int post_id;
    
    private double amount;
    
    private Date created_at;

    public Payment(int customer_id, int organization_id, int post_id, double amount, Date created_at) {
        this.customer_id = customer_id;
        this.organization_id = organization_id;
        this.post_id = post_id;
        this.amount = amount;
        this.created_at = created_at;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
