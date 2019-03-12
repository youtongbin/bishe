package com.ytb.dao;

import com.ytb.pojo.Apply;

import java.util.List;

public interface ApplyDao {

    /**
     * 主键查询
     * @param applyId
     * @return
     */
    Apply selectByKey(Integer applyId);

    /**
     * 添加申请
     * @param apply
     * @return
     */
    int insert(Apply apply);

    /**
     * 管理员处理申请，修改状态
     * @return
     */
    int updateStatus(Apply apply);

    /**
     * 用户查看个人申请列表
     * @param userId
     * @return
     */
    List<Apply> selectAllByUserId(Integer userId);

    /**
     * 管理员查看申请列表
     * @return
     */
    List<Apply> selectAll();

}
