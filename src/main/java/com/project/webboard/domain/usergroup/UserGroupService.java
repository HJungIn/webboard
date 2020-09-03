package com.project.webboard.domain.usergroup;

import com.project.webboard.domain.group.Group;
import com.project.webboard.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupService {

    private final UserGroupRepository userGroupRepository;


    public List<Group> getUserGroup(User currentUser) {
        return userGroupRepository.findByUser(currentUser);
    }

    public void saveUserGroup(UserGroup userGroup) {
        userGroupRepository.save(userGroup);
    }

    public boolean checkUserGroupByUserAndGroup(User user, Group group) {
        UserGroup byUserAndGroup = userGroupRepository.findByUserAndGroup(user, group);
        if(byUserAndGroup==null)
            return false;
        else
            return true;
    }
}
