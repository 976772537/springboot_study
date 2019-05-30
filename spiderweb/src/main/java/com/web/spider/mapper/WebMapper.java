package com.web.spider.mapper;

import com.web.spider.entity.Web;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface WebMapper extends BaseMapper<Web,Integer> {
    List<Web> findByTitle(String title);

}
