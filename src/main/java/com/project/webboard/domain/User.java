package com.project.webboard.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String nickname, Role role){
        this.name = name;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }
    public User update(String name, String nickname){
        this.name = name;
        this.nickname = nickname;
        return this;
    }
    public String getRoleKey(){
        return this.role.getKey();
    }

}
