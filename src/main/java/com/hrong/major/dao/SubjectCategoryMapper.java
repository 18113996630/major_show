package com.hrong.major.dao;

import com.hrong.major.model.SubjectCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangrong
 */
@Mapper
@Repository
public interface SubjectCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SubjectCategory record);

    int insertSelective(SubjectCategory record);

    SubjectCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SubjectCategory record);

    int updateByPrimaryKey(SubjectCategory record);

    List<SubjectCategory> selectCategories();
}