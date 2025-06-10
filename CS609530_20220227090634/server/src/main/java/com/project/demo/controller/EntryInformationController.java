package com.project.demo.controller;

import com.project.demo.entity.EntryInformation;
import com.project.demo.service.EntryInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *参赛信息：(EntryInformation)表控制层
 *
 */
@RestController
@RequestMapping("/entry_information")
public class EntryInformationController extends BaseController<EntryInformation,EntryInformationService> {

    /**
     *参赛信息对象
     */
    @Autowired
    public EntryInformationController(EntryInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
