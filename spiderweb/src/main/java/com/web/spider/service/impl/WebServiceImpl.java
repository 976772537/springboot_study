package com.web.spider.service.impl;

import com.web.spider.entity.Web;
import com.web.spider.mapper.WebMapper;
import com.web.spider.service.WebService;
import com.web.spider.service.exception.pageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebServiceImpl implements WebService {
    @Resource
    private WebMapper webMapper;
    @Override
    public boolean webSave(Web web) {
        try{
            webMapper.save (web);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Web> findList(int start, int size) throws pageException {
        Sort sort = new Sort (Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(start, size, sort);
        try {
            Page<Web> page = webMapper.findAll (pageable);
            return page.getContent ();
        }catch (Exception e){
            throw new pageException ();
        }
    }
}
