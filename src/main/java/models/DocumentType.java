package models;

public class DocumentType {
    private int PK_idChargeType;
    private String name;


    // Constructor vacío
    public DocumentType() {
    }

    // Constructor
    public DocumentType(int PK_idChargeType, String name) {
        this.PK_idChargeType = PK_idChargeType;
        this.name = name;
    }

    // Getters y setters
    public int getidChargeType() {
        return PK_idChargeType;
    }

    public void setidChargeType(int PK_idChargeType) {
        this.PK_idChargeType = PK_idChargeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}