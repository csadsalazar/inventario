package models;

public class Profile {

    private int PK_idProfile;
    private String profileName, profileDescription;

    public Profile() {
    }

    public Profile(int pK_idProfile, String profileName, String profileDescription) {
        this.PK_idProfile = pK_idProfile;
        this.profileName = profileName;
        this.profileDescription = profileDescription;
    }

    public int getPK_idProfile() {
        return PK_idProfile;
    }

    public void setPK_idProfile(int pK_idProfile) {
        this.PK_idProfile = pK_idProfile;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    @Override
    public String toString() {
        return "Profile [PK_idProfile=" + PK_idProfile + ", profileName=" + profileName + ", profileDescription="
                + profileDescription + ", getPK_idProfile()=" + getPK_idProfile() + ", getProfileName()="
                + getProfileName() + ", getProfileDescription()=" + getProfileDescription() + ", getClass()="
                + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
}