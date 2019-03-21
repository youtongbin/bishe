package com.ytb.service.impl;

import com.ytb.common.Const;
import com.ytb.common.ServerResponse;
import com.ytb.dao.ApplyDao;
import com.ytb.dao.UserDao;
import com.ytb.pojo.Apply;
import com.ytb.pojo.User;
import com.ytb.service.IUserManageService;
import com.ytb.vo.UserVO;
import com.ytb.vo.tvo.TransVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserManageServiceImpl implements IUserManageService {

    @Resource
    private UserDao userDao;

    @Resource
    private ApplyDao applyDao;

    @Override
    public ServerResponse addUser(User user) {

        if (user.getUsername() == null || user.getUsername().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("用户名不能为空");
        }
        if (user.getPassword() == null || user.getPassword().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("密码不能为空");
        }
        if (user.getPhone() == null || user.getPhone().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("手机号不能为空");
        }
        if (user.getEmail() == null || user.getEmail().matches("[ ]*")){
            return ServerResponse.serverResponseByFail("邮箱不能为空");
        }

        if (userDao.checkUsername(user.getUsername()) > 0){
            return ServerResponse.serverResponseByFail("该用户名已存在");
        }

        int result = userDao.insert(user);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess(user,"添加成功");
        }

        return ServerResponse.serverResponseByFail(1,"添加失败");
    }

    @Override
    public ServerResponse deleteUser(Integer userId) {

        if (userId == null){
            return ServerResponse.serverResponseByFail(Const.CommonEnum.INPUT_NULL.getMsg());
        }

        User user = userDao.selectByKey(userId);
        if (user == null){
            return ServerResponse.serverResponseByFail("用户不存在");
        }
        if (user.getRole() == Const.PowerEnum.MANAGER.getCode()){
            return ServerResponse.serverResponseByFail("不能删除管理员账户");
        }

        int result = userDao.delete(userId);

        if (result > 0){
            return ServerResponse.serverResponseBySuccess(null,"删除成功");
        }

        return ServerResponse.serverResponseByFail("删除失败");
    }

    @Override
    @Transactional
    public ServerResponse makePower(Integer applyId, Integer role) {

        Apply apply = applyDao.selectByKey(applyId);
        if (apply == null){
            return ServerResponse.serverResponseByFail("该申请不存在");
        }

        if (apply.getApplyStatus() != Const.ApplyStatusEnum.SUCCESS.getCode()){
            int result = userDao.makePower(apply.getUserId(),role);
            if (result > 0){
                apply.setApplyStatus(Const.ApplyStatusEnum.SUCCESS.getCode());
                applyDao.updateStatus(apply);
                return ServerResponse.serverResponseBySuccess(Const.PowerEnum.codeOf(role).getMsg(),"授权成功");
            }
            apply.setApplyStatus(Const.ApplyStatusEnum.FAIL.getCode());
            applyDao.updateStatus(apply);
            return ServerResponse.serverResponseByFail(1,"授权失败");
        }
        return ServerResponse.serverResponseByFail("已通过，无需修改");
    }

    @Override
    public ServerResponse selectAll() {

        List<User> userList = userDao.selectAll();
        if (userList == null || userList.size()==0){
            return ServerResponse.serverResponseByFail("没有用户");
        }

        List<UserVO> userVOList = new ArrayList<>();
        for (User user:userList
             ) {
            userVOList.add(TransVO.transUserVO(user));
        }

        return ServerResponse.serverResponseBySuccess(userVOList);
    }
}
