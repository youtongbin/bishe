package com.ytb.dao;

import com.ytb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    int insert(User user);

    User selectByUsername(String username);

    int checkUsername(String username);

    User selectByUsernameAndPassword(@Param("username")String username,
                                     @Param("password")String password);

    int update(User user);

    int delete(Integer userId);

    int makePower(@Param("userId")Integer userId,
                  @Param("role")Integer role);

    List<User> selectAll();

    User selectByKey(Integer userId);

}
