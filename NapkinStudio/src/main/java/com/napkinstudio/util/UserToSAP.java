package com.napkinstudio.util;


import com.napkinstudio.entity.*;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REMOVE;

@XStreamAlias("user")
public class UserToSAP extends AbstractEntity {
    private BCryptPasswordEncoder encoder;
        private String login;
        private String firstName;
        private String lastName;
//        private String password;
        private String email;
        private Boolean enabled;
        private Date lastSession;
        private Integer roleId;


        public Integer getRole() {
            return roleId;
        }
        public void setRole(Integer roleId) {
            this.roleId = roleId;
        }

//        public String getPassword() {
//            return password;
//        }
//        public void setPassword(String password) {
////            encoder = new BCryptPasswordEncoder();
////            this.password = encoder.encode(password);
//            this.password = password;
//        }

        public Boolean getEnabled() {
            return enabled;
        }
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }


        public String getLogin() {
            return login;
        }
        public void setLogin(String login) {
            this.login = login;
        }

        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;    }
        public void setEmail(String email) {
            this.email = email;    }

        public Date getDateTime() {
            return this.lastSession;	}
        public void setDateTime(Date lastSession) {
            this.lastSession = lastSession;	}

    }
