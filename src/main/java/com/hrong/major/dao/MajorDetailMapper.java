package com.hrong.major.dao;

import com.hrong.major.model.MajorDetail;
import com.hrong.major.model.MajorDetailWithBLOBs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author huangrong
 */
@Mapper
@Repository
public interface MajorDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MajorDetailWithBLOBs record);

    int insertSelective(MajorDetailWithBLOBs record);

    MajorDetailWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MajorDetailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MajorDetailWithBLOBs record);

    int updateByPrimaryKey(MajorDetail record);
}