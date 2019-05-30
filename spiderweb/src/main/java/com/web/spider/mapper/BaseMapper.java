package com.web.spider.mapper;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


@NoRepositoryBean
public interface BaseMapper<T,Integer> extends PagingAndSortingRepository<T,Integer> {

}
