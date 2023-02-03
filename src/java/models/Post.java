
package models;

import java.sql.Date;

public class Post {
    
    private int id;
    
    private int automotive_id;
    
    private int quantity;
    
    private String type;
    
    private Date created_at;
    
    private double price;
    
    private int organization_id;
    
    private boolean isHidden;

    public Post(int automotive_id, int quantity, String type, Date created_at, double price, int organization_id, boolean isHidden) {
        this.automotive_id = automotive_id;
        this.quantity = quantity;
        this.type = type;
        this.created_at = created_at;
        this.price = price;
        this.organization_id = organization_id;
        this.isHidden = isHidden;
    }
    
    public Post(int id, int automotive_id, int quantity, String type, Date created_at, double price, int organization_id, boolean isHidden) {
        this.id = id;
        this.automotive_id = automotive_id;
        this.quantity = quantity;
        this.type = type;
        this.created_at = created_at;
        this.price = price;
        this.organization_id = organization_id;
        this.isHidden = isHidden;
    }

    public int getAutomotive_id() {
        return automotive_id;
    }

    public void setAutomotive_id(int automotive_id) {
        this.automotive_id = automotive_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(int organization_id) {
        this.organization_id = organization_id;
    }

    public boolean isIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
