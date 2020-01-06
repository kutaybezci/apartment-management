package com.kutaybezci.apartmentmanagement.bl;

public enum Role {
    ;

    String description;

    private Role(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

}
