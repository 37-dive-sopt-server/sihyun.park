package org.sopt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Article> articles = new ArrayList<>();

    public Member() {}

    public Member(Long id, String name, String birth, String email, Gender gender) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.email = email;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirth(){
        return birth;
    }

    public String getEmail(){
        return email;
    }

    public Gender getGender(){
        return gender;
    }
}