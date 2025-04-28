package com.Travelbnb.paylod;

import jakarta.persistence.*;

public class Appuser_dto {

        @Column(name = "id", nullable = false)
        private Long id;

        @Column(name = "name", nullable = false, length = 60)
        private String name;

        @Column(name = "username", nullable = false, unique = true, length = 60)
        private String username;

        @Column(name = "email", nullable = false, unique = true, length = 100)
        private String email;

        @Column(name = "password", nullable = false, length = 100)
        private String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

    }

