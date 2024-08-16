package com.example.mergen_backend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("intern")
public class Intern {
    @Id
    private Long id;
    private String name;
    private String mail;
    private String tel_no;
    private String date;
    private String department;
    private String status;

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel_no() {
        return tel_no;
    }

    public void setTel_no(String tel_no) {
        this.tel_no = tel_no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String start_date) {
        this.date = start_date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String status) {
        this.department = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}