package models;

public class User {
    private int PK_idUser;
    private String name;
    private String user;
    private long identification;
    private DocumentType PK_idChargeType;
    private Dependency PK_idDependencia;
    private Charge PK_idCharge;


    // Constructor vacío
    public User() {
    }

    // Constructor
    public User(int PK_idUser, String name, String user, long identification, DocumentType PK_idChargeType , Dependency PK_idDependencia, Charge PK_idCharge) {
        this.PK_idUser = PK_idUser;
        this.name = name;
        this.user = user;
        this.identification = identification;
        this.PK_idChargeType = PK_idChargeType;
        this.PK_idDependencia = PK_idDependencia;
        this.PK_idCharge = PK_idCharge;
    }

    public int getPK_idUser() {
        return PK_idUser;
    }

    public void setPK_idUser(int PK_idUser) {
        this.PK_idUser = PK_idUser;
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

    public long getIdentification() {
        return identification;
    }

    public void setIdentification(long identification) {
        this.identification = identification;
    }

    public DocumentType getDocumentType() {
        return PK_idChargeType;
    } 

    public void setDocumentType(DocumentType PK_idChargeType) {
        this.PK_idChargeType = PK_idChargeType;
    }

    public Dependency getDependency() {
        return PK_idDependencia;
    } 

    public void setDependency(Dependency PK_idDependencia) {
        this.PK_idDependencia = PK_idDependencia;
    }

    public Charge getCharge() {
        return PK_idCharge;
    } 

    public void setCharge(Charge PK_idCharge) {
        this.PK_idCharge = PK_idCharge;
    }
}