package com.ain.engine.repository.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import org.mybatis.cdi.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

import java.util.List;

public interface SqlEngineMapper {

    List<String> selectCodeList(@Param("cdCl") String cdCl);

}