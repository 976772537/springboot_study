package com.web.spider.service;

import com.web.spider.entity.Web;
import com.web.spider.service.exception.pageException;

import java.util.List;

public interface WebService {
    boolean webSave(Web web);

    List<Web> findList(int start, int size) throws pageException;
}
