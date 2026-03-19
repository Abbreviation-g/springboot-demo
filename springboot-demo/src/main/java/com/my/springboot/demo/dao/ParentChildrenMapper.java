package com.my.springboot.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ParentChildrenMapper {
    List<Integer> selectRecursiveCurrentAndParentById(Integer id);

    List<Integer> selectRecursiveCurrentAndChildrenById(Integer id);
}
