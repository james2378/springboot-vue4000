package com.project.demo.controller;

import com.project.demo.entity.Event;
import com.project.demo.service.EventService;
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
 *比赛项目：(Event)表控制层
 *
 */
@RestController
@RequestMapping("/event")
public class EventController extends BaseController<Event,EventService> {

    /**
     *比赛项目对象
     */
    @Autowired
    public EventController(EventService service) {
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
