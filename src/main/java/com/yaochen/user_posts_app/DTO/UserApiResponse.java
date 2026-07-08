package com.yaochen.user_posts_app.DTO;

public class UserApiResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String website;
    private String phone;

    private Address address;
    private Company company;

    public static class Company {
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public void setSuite(String suite) {
            this.suite = suite;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhone() {
        return phone;
    }
    public Company getCompany() {
        return company;
    }

    public Address getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

}