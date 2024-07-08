package models;

public class Charge {
    private int PK_idCharge;
    private String name;

    public Charge() {
    }

    public Charge(int pK_idCharge, String name) {
        PK_idCharge = pK_idCharge;
        this.name = name;
    }

    public int getPK_idCharge() {
        return PK_idCharge;
    }

    public void setPK_idCharge(int pK_idCharge) {
        PK_idCharge = pK_idCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Charge [PK_idCharge=" + PK_idCharge + ", name=" + name + ", getPK_idCharge()=" + getPK_idCharge()
                + ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }
}