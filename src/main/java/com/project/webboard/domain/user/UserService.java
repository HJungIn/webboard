package com.project.webboard.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }
}
