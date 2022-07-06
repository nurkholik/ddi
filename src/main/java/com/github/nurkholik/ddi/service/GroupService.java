package com.github.nurkholik.ddi.service;

import com.github.nurkholik.ddi.entity.GroupDTO;
import com.github.nurkholik.ddi.entity.GroupDetailDTO;
import com.github.nurkholik.ddi.entity.MyPage;
import com.github.nurkholik.ddi.exception.RecordNotFoundException;
import com.github.nurkholik.ddi.repository.GroupRepository;
import com.github.nurkholik.ddi.repository.model.Groupx;
import com.github.nurkholik.ddi.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public MyPage<GroupDTO> findByCategory(int categoryId, int page, int row) {
        Page<Groupx> group = groupRepository.findGroupByCategory(categoryId, PageUtil.toPageable(page, row));
        return new MyPage<>(group, () -> group.getContent().stream()
                .map(g -> new GroupDTO(g.getId(), g.getName(), g.getImage_link()))
                .collect(Collectors.toList()));
    }

    public GroupDetailDTO getDetail(int id) {
        return groupRepository.findById(id)
                .map(g -> new GroupDetailDTO(g.getId(), g.getName(), g.getImage_link(), g.getDetail()))
                .orElseThrow(RecordNotFoundException::new);
    }

}
