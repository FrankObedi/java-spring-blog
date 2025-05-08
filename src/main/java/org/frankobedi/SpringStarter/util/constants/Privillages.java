package org.frankobedi.SpringStarter.util.constants;

public enum Privillages {
    RESET_ANY_USER_PASSWORD(1L, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2L, "ACCESS_ADMIN_PANEL");

    private Long Id;
    private String privilage;

    private Privillages(Long Id, String authorityString){
        this.Id = Id;
        this.privilage = authorityString;
    }

    public Long getId(){
        return Id;
    }

    public String getPrivillage(){
        return privilage;
    }
    
}
