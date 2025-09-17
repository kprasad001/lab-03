package com.example.listycitylab3;

public class City {
    private String name;
    private String province;

    public City(String arg1, String arg2) {
        name = arg1;
        province = arg2;
    }

    public String getName() {
        return name;
    }

    public String getProvince() {
        return province;
    }

    public String setName() {
        this.province = name;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
