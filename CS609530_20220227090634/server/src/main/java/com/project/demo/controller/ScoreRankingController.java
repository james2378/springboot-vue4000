package com.project.demo.controller;

import com.project.demo.entity.ScoreRanking;
import com.project.demo.service.ScoreRankingService;
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
 *成绩排名：(ScoreRanking)表控制层
 *
 */
@RestController
@RequestMapping("/score_ranking")
public class ScoreRankingController extends BaseController<ScoreRanking,ScoreRankingService> {

    /**
     *成绩排名对象
     */
    @Autowired
    public ScoreRankingController(ScoreRankingService service) {
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
