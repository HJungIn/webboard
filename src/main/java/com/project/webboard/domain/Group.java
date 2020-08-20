package com.project.webboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserGroup> members = new ArrayList<>();

    @OneToMany(mappedBy = "currentgroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Group(String name, User owner){
        this.name = name;
        this.owner = owner;
        this.setUserOwner(owner);
    }

    //==연관관계 메서드
    public void setUserOwner(User userOwner){
        this.owner = userOwner;
        userOwner.getMyownergroups().add(this);
    }

}
