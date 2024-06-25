package models;

public class Profile {

    private int PK_idProfile;
    private String profileName;
    private String profileDescription;
 
    // Constructor vacío
    public Profile() {
    }

    // Constructor
    public Profile(int PK_idProfile, String profileName, String profileDescription) {
        this.PK_idProfile = PK_idProfile;
        this.profileName = profileName;
        this.profileDescription = profileDescription;
    }

    public int getPK_idProfile() {
        return PK_idProfile;
    }

    public void setPK_idProfile(int PK_idProfile) {
        this.PK_idProfile = PK_idProfile;
    }

    public String getprofileName() {
        return profileName;
    }

    public void setprofileName(String profileName) {
        this.profileName = profileName;
    }

    public String getprofileDescription() {
        return profileDescription;
    }

    public void setprofileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }
}