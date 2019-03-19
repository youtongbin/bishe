package com.ytb.dao;

import com.ytb.pojo.Paper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PaperDao {

    /**
     * 新建论文
     * @param paper
     * @return
     */
    int insert(Paper paper);

    /**
     * 根据用户id查询
     * @param userId
     * @return
     */
    List<Paper> selectByUserId(Integer userId);

    /**
     * 主键查询
     * @param userId
     * @return
     */
    Paper selectByPaperId(Integer userId);

    /**
     * 查询列表
     * @return
     */
    List<Paper> selectAll();

    /**
     * 修改
     * @param paper
     * @return
     */
    int update(Paper paper);

    /**
     * 删除
     * @param paperId
     * @return
     */
    int delete(Integer paperId);

    int deleteByKeyAndUserId(@Param("paperId") Integer paperId,
                             @Param("userId") Integer userId);

}
