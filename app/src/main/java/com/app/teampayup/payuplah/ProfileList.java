package com.app.teampayup.payuplah;

import java.util.List;

public class ProfileList {
    List<Profile>listofProfiles;

    public List<Profile> getProfileList() {
        return listofProfiles;
    }

    public void setProfileList(List<Profile> profileList) {
        this.listofProfiles = profileList;
    }

    public void addProfile(Profile p){
        listofProfiles.add(p);
    }

}
