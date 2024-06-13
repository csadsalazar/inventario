package models;

import java.util.Date;

public class Object {
    private int PK_idObject;
    private long code;
    private String name;
    private int plate;
    private String description;
    private long value;
    private User user;
    private Dependency PK_idDependency;
    private String state;
    private String image1;
    private String image2;
    private Observation information;
    private Date date;


    // Constructor vacío
    public Object() {
    }

    // Constructor
    public Object(int PK_idObject, long code, String name, int plate, String description, long value, User user, Dependency PK_idDependency, String state, String image1, String image2, Observation information, Date date) {
        this.PK_idObject = PK_idObject;
        this.code = code;
        this.name = name;
        this.plate = plate;
        this.description = description;
        this.value = value;
        this.user = user;
        this.PK_idDependency = PK_idDependency;
        this.state = state;
        this.image1 = image1;
        this.image2 = image2;
        this.information = information;
        this.date = date;
    }

    // Getters y setters
    public int getidBien() {
        return PK_idObject;
    }

    public void setidObject(int PK_idObject) {
        this.PK_idObject = PK_idObject;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dependency getDependency() {
        return PK_idDependency;
    } 

    public void setDependency(Dependency PK_idDependency) {
        this.PK_idDependency = PK_idDependency;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public Observation getObservation() {
        return information;
    }

    public void setObservation(Observation information) {
        this.information = information;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}