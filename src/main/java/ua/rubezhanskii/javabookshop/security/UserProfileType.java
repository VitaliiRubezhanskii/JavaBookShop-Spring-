package ua.rubezhanskii.javabookshop.security;

public enum UserProfileType {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    String userProfileType;

     UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType(){
        return userProfileType;
    }
}
