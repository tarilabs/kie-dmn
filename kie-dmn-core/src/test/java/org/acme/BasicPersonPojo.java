package org.acme;

import org.kie.dmn.feel.lang.FEELType;

@FEELType
public class BasicPersonPojo {
    private String name;
    private String surname;
    
    public BasicPersonPojo() { }

    public BasicPersonPojo(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
