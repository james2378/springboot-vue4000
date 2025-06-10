package com.project.demo.controller;

import com.project.demo.entity.EntryResults;
import com.project.demo.service.EntryResultsService;
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
 *参赛成绩：(EntryResults)表控制层
 *
 */
@RestController
@RequestMapping("/entry_results")
public class EntryResultsController extends BaseController<EntryResults,EntryResultsService> {

    /**
     *参赛成绩对象
     */
    @Autowired
    public EntryResultsController(EntryResultsService service) {
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
