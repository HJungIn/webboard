package com.project.webboard.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    public Post getPost(Long postid) {
        return postRepository.findPostById(postid);
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public void removePost(Post post) {
        postRepository.delete(post);
    }
}
