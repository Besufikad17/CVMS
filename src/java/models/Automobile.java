package models;


public class Automobile {
    
    private int id;
    
    private String model;
    
    private String manufacturer;
    
    private int year;
    
    private String style;
    
    private String color;
    
    private int no_seats;
    
    private String img_url;
    
    private String engine;

    public Automobile(String model, String manufacturer, int year, String style, String color, int no_seats, String img_url, String engine) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.style = style;
        this.color = color;
        this.no_seats = no_seats;
        this.img_url = img_url;
        this.engine = engine;
    }

    public Automobile(int id, String model, String manufacturer, int year, String style, String color, int no_seats, String img_url, String engine) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.style = style;
        this.color = color;
        this.no_seats = no_seats;
        this.img_url = img_url;
        this.engine = engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNo_seats() {
        return no_seats;
    }

    public void setNo_seats(int no_seats) {
        this.no_seats = no_seats;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }
    
}
