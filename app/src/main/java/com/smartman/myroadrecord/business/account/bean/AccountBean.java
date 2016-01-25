package com.smartman.myroadrecord.business.account.bean;

import java.io.Serializable;

/**
 * Created by jiahui.chen on 2015/12/30.
 */
public class AccountBean implements Serializable {
    private static final long serialVersionUID = -7419721349288976885L;

    public String id;    //手机号码作ID

    public String name;

    public String password;

    public int age;

    public String imageUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}