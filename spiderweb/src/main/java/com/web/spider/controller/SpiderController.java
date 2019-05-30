package com.web.spider.controller;

import com.web.spider.entity.Web;
import com.web.spider.service.SpiderService;
import com.web.spider.service.WebService;
import com.web.spider.service.exception.pageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class SpiderController {
    private final int SIZE = 1;
    private int start = 0;
    @Resource
    private SpiderService spiderService;
    @Resource
    private WebService webService;
    @PostMapping("spider_run")
    @Transactional
    public void spiderRun(String url) {
        findUrl (url);
      while (true){
          try {
              Thread.sleep (10);
              List<Web> list = webService.findList (start++, SIZE);
                list.forEach (web -> {log.info ("href-2 ：{}",web.getHref ());
                findUrl (web.getHref ());});
            }catch (pageException | InterruptedException e){
              log.info ("pageException : {}",start);
            }
      }
    }
    private void findUrl(String url){
        ArrayList<Web> webs = null;
        try {
            webs = spiderService.run (url);
            webs.forEach (w -> {log.info ("href-1 ：{}",w.getHref ());webService.webSave (w);});
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
