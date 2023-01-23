package com.example.ecommerce.UserDto;

public class UpdateDto {
    private int id;
    private String name;
    private String address1;
    private String city;
    private String state;
    private String moblieno;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMoblieno() {
        return moblieno;
    }

    public void setMoblieno(String moblieno) {
        this.moblieno = moblieno;
    }
}