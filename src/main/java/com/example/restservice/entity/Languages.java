package com.example.restservice.entity;

import java.io.Serializable;


public class Languages implements Serializable {


    private String code;
    private String name;
    private String native_;


    public Languages(String code, String name, String native_) {
        this.code = code;
        this.name = name;
        this.native_ = native_;
    }

    public String getNative_() {
        return native_;
    }

    public void setNative_(String native_) {
        this.native_ = native_;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
