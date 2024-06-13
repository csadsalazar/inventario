package models;

public class Dependency {
    private int PK_idDependency;
    private String costcenter;
    private String dependencyname;

    // Constructor vacio
    public Dependency() {
    }

    // Constructor 
    public Dependency(int PK_idDependency, String dependencyname, String costcenter) {
        this.PK_idDependency = PK_idDependency;
        this.dependencyname = dependencyname;
        this.costcenter = costcenter;
    }

    // Getters y Setters
    public int getPK_idDependency() {
        return PK_idDependency;
    }

    public void setPK_idDependency(int PK_idDependency) {
        this.PK_idDependency = PK_idDependency;
    }

    public String getDependencyName() {
        return dependencyname;
    }

    public void setDependencyName(String dependencyname) {
        this.dependencyname = dependencyname;
    }

    public String getCostCenter() {
        return costcenter;
    }

    public void setCostCenter(String costcenter) {
        this.costcenter = costcenter;
    }
}
