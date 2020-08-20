package com.project.webboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User publisher;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group currentgroup;

    @Builder
    public Post(String title, String content, User publisher, Group currentgroup){
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.currentgroup = currentgroup;
        this.setPublisher(publisher);
        this.setCurrentgroup(currentgroup);
    }

    //==연관관계 메서드
    public void setPublisher(User publisher){
        this.publisher = publisher;
        publisher.getPosts().add(this);
    }

    public void setCurrentgroup(Group currentgroup){
        this.currentgroup = currentgroup;
        currentgroup.getPosts().add(this);
    }

}
