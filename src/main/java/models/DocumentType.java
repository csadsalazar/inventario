package models;

public class DocumentType {
    private int PK_idChargeType;
    private String name;

    public DocumentType() {
    }

    public DocumentType(int pK_idChargeType, String name) {
        PK_idChargeType = pK_idChargeType;
        this.name = name;
    }

    public int getPK_idChargeType() {
        return PK_idChargeType;
    }

    public void setPK_idChargeType(int pK_idChargeType) {
        PK_idChargeType = pK_idChargeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DocumentType [PK_idChargeType=" + PK_idChargeType + ", name=" + name + ", getPK_idChargeType()="
                + getPK_idChargeType() + ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    } 
}