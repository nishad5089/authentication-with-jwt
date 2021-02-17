package com.commonauthmodule.commonauthmodule.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Role(String role) {
        this.name  = role;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof Role ? this.name.equals(((Role)obj).name) : false;
        }
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }
}
