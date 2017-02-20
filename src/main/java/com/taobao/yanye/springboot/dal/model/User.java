package com.taobao.yanye.springboot.dal.model;


/**
 * Created by yanye on 17-2-18.
 */
public class User implements java.io.Serializable {
    private static final long serialVersionUID = 5464735891546087248L;
    private Long id;
    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
