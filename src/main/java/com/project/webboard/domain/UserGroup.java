package com.project.webboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserGroup {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @Builder
    public UserGroup(User user, Group group){
        this.user = user;
        this.group = group;
        this.setUser(user);
        this.setGroup(group);
    }

    //==연관관계 메서드
    public void setUser(User user){
        this.user = user;
        user.getMygroups().add(this);
    }

    public void setGroup(Group group){
        this.group = group;
        group.getMembers().add(this);
    }

}
