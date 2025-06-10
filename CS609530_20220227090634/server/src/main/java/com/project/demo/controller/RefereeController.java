package com.project.demo.controller;

import com.project.demo.entity.Referee;
import com.project.demo.service.RefereeService;
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
 *裁判：(Referee)表控制层
 *
 */
@RestController
@RequestMapping("/referee")
public class RefereeController extends BaseController<Referee,RefereeService> {

    /**
     *裁判对象
     */
    @Autowired
    public RefereeController(RefereeService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        Map<String, String> mapreferee_number = new HashMap<>();
        mapreferee_number.put("referee_number",String.valueOf(paramMap.get("referee_number")));
        List listreferee_number = service.select(mapreferee_number, new HashMap<>()).getResultList();
        if (listreferee_number.size()>0){
            return error(30000, "字段裁判编号内容不能重复");
        }
        this.addMap(paramMap);
        return success(1);
    }

}
