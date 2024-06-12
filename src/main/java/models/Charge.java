package models;

public class Charge {
    private int idCharge;
    private String name;


    // Constructor vacío
    public Charge() {
    }

    // Constructor
    public Charge(int idCharge, String name) {
        this.idCharge = idCharge;
        this.name = name;
    }

    // Getters y setters
    public int getidChargeType() {
        return idCharge;
    }

    public void setidChargeType(int idCharge) {
        this.idCharge = idCharge;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String name) {
        this.name = name;
    }

}