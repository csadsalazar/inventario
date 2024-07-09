package models;

public class User {
    private int PK_idUser;
    private long identification;
    private String name, user;
    private DocumentType PK_idChargeType;
    private Dependency PK_idDependencia;
    private Charge PK_idCharge;
    private Profile PK_idProfile;

    public User() {
    }

    public User(int pK_idUser, long identification, String name, String user, DocumentType pK_idChargeType,
            Dependency pK_idDependencia, Charge pK_idCharge, Profile pK_idProfile) {
        PK_idUser = pK_idUser;
        this.identification = identification;
        this.name = name;
        this.user = user;
        this.PK_idChargeType = pK_idChargeType;
        this.PK_idDependencia = pK_idDependencia;
        this.PK_idCharge = pK_idCharge;
        this.PK_idProfile = pK_idProfile;
    }

    public int getPK_idUser() {
        return PK_idUser;
    }

    public void setPK_idUser(int pK_idUser) {
        this.PK_idUser = pK_idUser;
    }

    public long getIdentification() {
        return identification;
    }

    public void setIdentification(long identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public DocumentType getPK_idChargeType() {
        return PK_idChargeType;
    }

    public void setPK_idChargeType(DocumentType pK_idChargeType) {
        this.PK_idChargeType = pK_idChargeType;
    }

    public Dependency getPK_idDependencia() {
        return PK_idDependencia;
    }

    public void setPK_idDependencia(Dependency pK_idDependencia) {
        this.PK_idDependencia = pK_idDependencia;
    }

    public Charge getPK_idCharge() {
        return PK_idCharge;
    }

    public void setPK_idCharge(Charge pK_idCharge) {
        this.PK_idCharge = pK_idCharge;
    }

    public Profile getPK_idProfile() {
        return PK_idProfile;
    }

    public void setPK_idProfile(Profile pK_idProfile) {
        this.PK_idProfile = pK_idProfile;
    }

    @Override
    public String toString() {
        return "User [PK_idUser=" + PK_idUser + ", identification=" + identification + ", name=" + name + ", user="
                + user + ", PK_idChargeType=" + PK_idChargeType + ", PK_idDependencia=" + PK_idDependencia
                + ", PK_idCharge=" + PK_idCharge + ", PK_idProfile=" + PK_idProfile + ", getPK_idUser()="
                + getPK_idUser() + ", getIdentification()=" + getIdentification() + ", getName()=" + getName()
                + ", getUser()=" + getUser() + ", getPK_idChargeType()=" + getPK_idChargeType()
                + ", getPK_idDependencia()=" + getPK_idDependencia() + ", getPK_idCharge()=" + getPK_idCharge()
                + ", getPK_idProfile()=" + getPK_idProfile() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }
}