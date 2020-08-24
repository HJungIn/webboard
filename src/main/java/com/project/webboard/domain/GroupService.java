package com.project.webboard.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void saveGroup(Group group){
        groupRepository.save(group);
    }

    public Group getGroup(Long id) { return groupRepository.findGroupById(id);  }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }
}
