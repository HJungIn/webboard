package com.project.webboard.domain.post;

import com.project.webboard.domain.user.User;
import com.project.webboard.domain.group.Group;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @ElementCollection(targetClass=String.class)
    private List<String> books;

    @Builder
    public Post(String title, String content, User publisher, Group currentgroup, List<String> books){
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.currentgroup = currentgroup;
        this.books = books;
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
