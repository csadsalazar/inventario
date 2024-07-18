package models;

import java.util.Date;

public class Object {
    private int PK_idObject, plate;
    private long code, value;
    private Date date, dateadmin;
    private String name, description, state, condition, image1, image2, image3, observation;
    private User user, admin;
    private Dependency PK_idDependency;
    private Observation information;
    
    public Object() {
    }

    public Object(int pK_idObject, int plate, long code, long value, Date date, Date dateadmin, String name,
            String description, String state, String condition, String image1, String image2, String image3,
            String observation, User user, User admin, Dependency pK_idDependency, Observation information) {
        this.PK_idObject = pK_idObject;
        this.plate = plate;
        this.code = code;
        this.value = value;
        this.date = date;
        this.dateadmin = dateadmin;
        this.name = name;
        this.description = description;
        this.state = state;
        this.condition = condition;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.observation = observation;
        this.user = user;
        this.admin = admin;
        this.PK_idDependency = pK_idDependency;
        this.information = information;
    }

    public int getPK_idObject() {
        return PK_idObject;
    }

    public void setPK_idObject(int pK_idObject) {
        this.PK_idObject = pK_idObject;
    }

    public int getPlate() {
        return plate;
    }

    public void setPlate(int plate) {
        this.plate = plate;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateadmin() {
        return dateadmin;
    }

    public void setDateadmin(Date dateadmin) {
        this.dateadmin = dateadmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
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

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Dependency getPK_idDependency() {
        return PK_idDependency;
    }

    public void setPK_idDependency(Dependency pK_idDependency) {
        this.PK_idDependency = pK_idDependency;
    }

    public Observation getInformation() {
        return information;
    }

    public void setInformation(Observation information) {
        this.information = information;
    } 
}