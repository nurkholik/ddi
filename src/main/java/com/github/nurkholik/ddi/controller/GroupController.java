package com.github.nurkholik.ddi.controller;

import com.github.nurkholik.ddi.entity.MyPage;
import com.github.nurkholik.ddi.entity.GroupDTO;
import com.github.nurkholik.ddi.entity.GroupDetailDTO;
import com.github.nurkholik.ddi.service.GroupService;
import com.github.nurkholik.ddi.utils.Utils;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CommonsLog
@RestController
@RequestMapping(path = "/api/v1")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping(path = "/group")
    public MyPage<GroupDTO> findByCategory(
            @RequestParam int category_id,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "10") int row) {
        log.info("Get Groups");
        MyPage<GroupDTO> res = groupService.findByCategory(category_id, page, row);
        log.info("Response : " + Utils.toJson(res));
        return res;
    }

    @GetMapping(path = "/group/{id}")
    public GroupDetailDTO getDetail(@PathVariable int id) {
        log.info("Get Detail Group");
        GroupDetailDTO res = groupService.getDetail(id);
        log.info("Response : " + Utils.toJson(res));
        return res;
    }

}
