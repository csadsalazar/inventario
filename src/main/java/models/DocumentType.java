package models;

public class DocumentType {
    private int idChargeType;
    private String name;


    // Constructor vacío
    public DocumentType() {
    }

    // Constructor
    public DocumentType(int idChargeType, String name) {
        this.idChargeType = idChargeType;
        this.name = name;
    }

    // Getters y setters
    public int getidChargeType() {
        return idChargeType;
    }

    public void setidChargeType(int idChargeType) {
        this.idChargeType = idChargeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}