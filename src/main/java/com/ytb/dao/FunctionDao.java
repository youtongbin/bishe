package com.ytb.dao;

import com.ytb.pojo.Function;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FunctionDao {

    List<Function> selectByParentId(Integer parentId);

    List<Function> selectByParentIdAndRole(@Param("parentId") Integer parentId,
                                           @Param("role") Integer role);

}
