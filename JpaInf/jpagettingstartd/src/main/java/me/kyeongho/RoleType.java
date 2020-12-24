package me.kyeongho;

public enum RoleType {
	ADMIN("admin"),
    USER("user")
    ;
    
    private final String name;

    RoleType(final String naem) {
        this.name = naem;
    }

    public String getName() {
        return this.name;
    }


}
