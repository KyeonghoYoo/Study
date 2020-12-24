package me.kyeongho;

public enum RoleType {
	ADMIN("admin"),
    USER("user")
    ;
    
    private final String name;

    RoleType(final String message) {
        this.name = message;
    }

    public String getName() {
        return this.name;
    }


}
