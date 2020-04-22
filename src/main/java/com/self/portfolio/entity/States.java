package com.self.portfolio.entity;

import javax.persistence.*;

@Entity
@Table(name = "md_state")
public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATE_ID")
    private Long id;

    @Column(name = "TIN")
    private String tin;

    @Column(name = "STATE_CODE", length = 5)
    private String code;

    @Column(name = "DESCRIPTION" , length = 50)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "States{" +
                "id=" + id +
                ", tin='" + tin + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
