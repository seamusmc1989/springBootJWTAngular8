package com.angularBootRef.springBootPortfolio.domain;

public enum UserRoleEnum {

    ADMIN("Full admin permissions"),
    USER("user permissions");

    private String title;

    UserRoleEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }  
    
    public static UserRoleEnum get(String title) {
    	for (UserRoleEnum ur : UserRoleEnum.values()) {
    		if (ur.getTitle().equals(title)) {
    			return ur;
    		}
    	}
    	return null;
    }
    
}
