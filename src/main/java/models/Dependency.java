package models;

public class Dependency {
    private int PK_idDependency;
    private String costcenter, dependencyname;

    public Dependency() {
    }

    public Dependency(int pK_idDependency, String costcenter, String dependencyname) {
        PK_idDependency = pK_idDependency;
        this.costcenter = costcenter;
        this.dependencyname = dependencyname;
    }

    public int getPK_idDependency() {
        return PK_idDependency;
    }

    public void setPK_idDependency(int pK_idDependency) {
        PK_idDependency = pK_idDependency;
    }

    public String getCostcenter() {
        return costcenter;
    }

    public void setCostcenter(String costcenter) {
        this.costcenter = costcenter;
    }

    public String getDependencyname() {
        return dependencyname;
    }

    public void setDependencyname(String dependencyname) {
        this.dependencyname = dependencyname;
    }

    @Override
    public String toString() {
        return "Dependency [PK_idDependency=" + PK_idDependency + ", costcenter=" + costcenter + ", dependencyname="
                + dependencyname + ", getPK_idDependency()=" + getPK_idDependency() + ", getCostcenter()="
                + getCostcenter() + ", getDependencyname()=" + getDependencyname() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}