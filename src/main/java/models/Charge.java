package models;

public class Charge {
    private int PK_idCharge;
    private String name;


    // Constructor vacío
    public Charge() {
    }

    // Constructor
    public Charge(int PK_idCharge, String name) {
        this.PK_idCharge = PK_idCharge;
        this.name = name;
    }

    // Getters y setters
    public int getidChargeType() {
        return PK_idCharge;
    }

    public void setidChargeType(int PK_idCharge) {
        this.PK_idCharge = PK_idCharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}